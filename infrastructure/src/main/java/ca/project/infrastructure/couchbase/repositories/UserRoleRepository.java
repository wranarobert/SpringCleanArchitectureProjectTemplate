package ca.project.infrastructure.couchbase.repositories;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import ca.project.domain.entities.identity.UserRole;
import ca.project.domain.external.infrastructure.couchbase.IUserRoleRepository;
import ca.project.infrastructure.couchbase.contexts.UserRoleContext;
import ca.project.infrastructure.couchbase.entitydefinition.UserRoleDefinition;

@Repository
public class UserRoleRepository implements IUserRoleRepository {
	Logger logger = LoggerFactory.getLogger(UserRoleRepository.class);

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRoleContext userRoleContext;

	@Async
	public CompletableFuture<List<UserRole>> findAll() {
		return CompletableFuture.completedFuture(
			userRoleContext.getAll()
		);
	}

	@Async
	public CompletableFuture<UserRole> save(UserRole newEntity) {
		UserRoleDefinition entity = modelMapper.map(newEntity, UserRoleDefinition.class);
		
		userRoleContext.save(entity);
		
		return CompletableFuture.completedFuture(entity);
	}

	@Async
	public CompletableFuture<Void> deleteById(String id) {
		userRoleContext.deleteById(id);
		return CompletableFuture.completedFuture(null);
	}
	
	@Async
	public CompletableFuture<UserRole> getUserRoleById(String id) {
		return CompletableFuture.completedFuture(
			userRoleContext.findById(id).get()
		);
	}
	
	@Async
	public CompletableFuture<UserRole> getUserRoleByName(String name) {
		return CompletableFuture.completedFuture(
			userRoleContext.findFirstByName(name)
		);
	}
	
}
