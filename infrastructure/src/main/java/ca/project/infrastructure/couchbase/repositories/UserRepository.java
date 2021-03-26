package ca.project.infrastructure.couchbase.repositories;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.couchbase.client.java.document.json.JsonArray;

import ca.project.domain.dto.models.identity.ApplicationUserDTO;
import ca.project.domain.entities.identity.ApplicationUser;
import ca.project.domain.entities.identity.UserRole;
import ca.project.domain.external.infrastructure.couchbase.IUserRepository;
import ca.project.infrastructure.couchbase.contexts.UserContext;
import ca.project.infrastructure.couchbase.contexts.UserRoleContext;
import ca.project.infrastructure.couchbase.entitydefinition.UserDefinition;

@Repository
public class UserRepository implements IUserRepository {
	Logger logger = LoggerFactory.getLogger(UserRepository.class);

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserContext userContext;
	
	@Autowired
	private UserRoleContext userRoleContext;
	
	@Async
	@Override
	public CompletableFuture<ApplicationUser> save(ApplicationUser entity) {
		UserDefinition user = modelMapper.map(entity, UserDefinition.class);
		
		userContext.save(user);
		
		return CompletableFuture.completedFuture(user);
	}

	@Async
	@Override
	public CompletableFuture<List<ApplicationUser>> findAll() {
		return CompletableFuture.completedFuture(userContext.getAll());
	}

	@Async
	@Override
	public CompletableFuture<Void> deleteById(String id) {
		userContext.deleteById(id);
		return CompletableFuture.completedFuture(null);
	}
	
	@Async
	public CompletableFuture<ApplicationUser> getUserByUsername(String username) {
		return CompletableFuture.completedFuture(
			userContext.findFirstByUsername(username)
		);
	}
	
	@Async
	@Override
	public CompletableFuture<ApplicationUser> getUserById(String id) {
		return CompletableFuture.completedFuture(userContext.findById(id).get());
	}

	@Async
	public CompletableFuture<ApplicationUserDTO> getUserWithRolesByUsername(String username) {
		ApplicationUser user = userContext.findFirstByUsername(username);
		
		var iterableRoles = userRoleContext.getByIds(JsonArray.from((user.getRoleIds().toArray())));
		
		Set<UserRole> userRoles = StreamSupport
				  .stream(iterableRoles.spliterator(), false)
				  .collect(Collectors.toSet());
		
		var userDTO = modelMapper.map(user, ApplicationUserDTO.class);
		
		userDTO.setRoles(userRoles);
		
		return CompletableFuture.completedFuture(userDTO);
	}
	
	// TODO: dovrsiti
	@Async
	public CompletableFuture<ApplicationUserDTO> getUserWithRolesById(String id) {
		ApplicationUser user = userContext.findFirstByUsername(id);
		
		var iterableRoles = userRoleContext.getByIds(JsonArray.from((user.getRoleIds().toArray())));
		
		Set<UserRole> userRoles = StreamSupport
				  .stream(iterableRoles.spliterator(), false)
				  .collect(Collectors.toSet());
		
		var userDTO = modelMapper.map(user, ApplicationUserDTO.class);
		
		userDTO.setRoles(userRoles);
		
		return CompletableFuture.completedFuture(userDTO);
	}
}
