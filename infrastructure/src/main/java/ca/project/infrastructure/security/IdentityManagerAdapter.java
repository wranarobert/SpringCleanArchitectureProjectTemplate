package ca.project.infrastructure.security;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ca.project.domain.dto.models.identity.ApplicationUserDTO;
import ca.project.domain.dto.models.identity.UserPermissionDTO;
import ca.project.domain.entities.identity.ApplicationUser;
import ca.project.domain.entities.identity.UserPermission;
import ca.project.domain.entities.identity.UserRole;
import ca.project.domain.exceptions.EntityAlreadyExistsException;
import ca.project.domain.exceptions.NotFoundException;
import ca.project.domain.external.infrastructure.couchbase.IUserRepository;
import ca.project.domain.external.infrastructure.couchbase.IUserRoleRepository;
import ca.project.domain.external.infrastructure.security.IIdentityManager;
import ca.project.infrastructure.security.factories.ApplicationUserFactory;

@Component
public class IdentityManagerAdapter implements IIdentityManager {
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IUserRoleRepository userRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	ApplicationUserFactory applicationUserFactory;
	
	@Autowired
	ModelMapper modelMapper;
	
	public CompletableFuture<ApplicationUserDTO> getUserById(String id) {
		var user = userRepository.getUserById(id).join();
		
		if(user == null) {
			throw new NotFoundException(ApplicationUser.class);
		}
		
		var userResponse = modelMapper.map(user, ApplicationUserDTO.class);
		
		return CompletableFuture.completedFuture(
			applicationUserFactory.create(userResponse)
		);
	}
	
	public CompletableFuture<ApplicationUserDTO> getUserByUsername(String username) {
		var user = userRepository.getUserWithRolesByUsername(username).join();
		
		if(user == null) {
			throw new NotFoundException(ApplicationUser.class);
		}
		
		return CompletableFuture.completedFuture(
			applicationUserFactory.create(user)
		);
	}
	
	public CompletableFuture<ApplicationUser> createUser(ApplicationUser user) {
		ApplicationUser existingUser = userRepository.getUserByUsername(user.getUsername()).join();
		
		if(existingUser != null) {
			throw new EntityAlreadyExistsException(ApplicationUser.class);
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}
	
	public CompletableFuture<ApplicationUser> editUser(String id, ApplicationUser user) {
		ApplicationUser userById = userRepository.getUserById(id).join();
		
		if(userById == null) {
			throw new NotFoundException(ApplicationUser.class);
		}
		
		if(!userById.getUsername().equals(user.getUsername())) {
			ApplicationUser userByUsername = userRepository.getUserByUsername(user.getUsername()).join();
			
			if(userByUsername != null) {
				throw new EntityAlreadyExistsException("Username is already taken");
			}
		}
		
		user.setId(id);
		
		if(!userById.getPassword().equals(user.getPassword())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}

		return userRepository.save(user);
	}
	
	// TODO: testirati
	@Override
	public CompletableFuture<ApplicationUser> editUserByUsername(String username, ApplicationUser newUser) {
		ApplicationUser user = userRepository.getUserByUsername(username).join();
		
		if(user == null) {
			throw new NotFoundException(ApplicationUser.class);
		}
		
		if(!user.getUsername().equals(newUser.getUsername())) {
			ApplicationUser userByUsername = userRepository.getUserByUsername(newUser.getUsername()).join();
			
			if(userByUsername != null) {
				throw new EntityAlreadyExistsException("Username is already taken");
			}
		}
		
		if(!user.getPassword().equals(newUser.getPassword())) {
			newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		}
		
		return userRepository.save(newUser);
	}
	
	public CompletableFuture<Void> deleteUserById(String id) {
		var user = userRepository.getUserById(id).join();
		
		if(user == null) {
			throw new NotFoundException(ApplicationUser.class);
		}
		
		userRepository.deleteById(user.getId()).join();
		
		return CompletableFuture.completedFuture(null);
	}
	
	public CompletableFuture<Void> deleteUserByUsername(String username) {
		var user = userRepository.getUserByUsername(username).join();
		
		if(user == null) {
			throw new NotFoundException(ApplicationUser.class);
		}
		
		userRepository.deleteById(user.getId()).join();
		
		return CompletableFuture.completedFuture(null);
	}
	
	public CompletableFuture<List<UserRole>> getUserRoles() {
		return userRoleRepository.findAll();
	}

	public CompletableFuture<UserRole> createUserRole(UserRole newRole) {
		UserRole existingRoleByName = userRoleRepository.getUserRoleByName(newRole.getName()).join();
		
		if(existingRoleByName != null) {
			throw new EntityAlreadyExistsException(UserRole.class);
		}
		
		newRole.setId(null);
		
		return userRoleRepository.save(newRole);
	}
	
	public CompletableFuture<UserRole> editUserRole(String id, UserRole newRole) {
		UserRole existingRoleById = userRoleRepository.getUserRoleById(id).join();
		
		if(existingRoleById == null) {
			throw new NotFoundException(ApplicationUser.class);
		}
		
		if(!existingRoleById.getName().equals(newRole.getName())) {
			UserRole userByUsername = userRoleRepository.getUserRoleByName(newRole.getName()).join();
			
			if(userByUsername != null) {
				throw new EntityAlreadyExistsException("Name is already taken");
			}
		}
		
		newRole.setId(id);

		return userRoleRepository.save(newRole);
	}
	
	public CompletableFuture<Void> deleteUserRoleById(String id) {
		UserRole existingUser = userRoleRepository.getUserRoleById(id).join();
		
		if(existingUser == null) {
			throw new NotFoundException(ApplicationUser.class);
		}
		
		userRoleRepository.deleteById(id).join();
		
		return CompletableFuture.completedFuture(null);
	}
	
	public CompletableFuture<List<UserPermissionDTO>> getUserPermissions() {
		return CompletableFuture.completedFuture(
			Stream.of(UserPermission.values())
				.map(permission -> new UserPermissionDTO(
						permission,
						permission.getName(),
						permission.getDescription())
				)
                .collect(Collectors.toList())
        );
	}
}
