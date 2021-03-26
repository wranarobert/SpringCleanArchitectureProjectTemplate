package ca.project.domain.external.infrastructure.security;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import ca.project.domain.dto.models.identity.ApplicationUserDTO;
import ca.project.domain.dto.models.identity.UserPermissionDTO;
import ca.project.domain.entities.identity.ApplicationUser;
import ca.project.domain.entities.identity.UserPermission;
import ca.project.domain.entities.identity.UserRole;

public interface IIdentityManager {
	CompletableFuture<ApplicationUserDTO> getUserById(String id);

	CompletableFuture<ApplicationUserDTO> getUserByUsername(String username);
	
	CompletableFuture<ApplicationUser> createUser(ApplicationUser user);
	
	CompletableFuture<ApplicationUser> editUser(String id, ApplicationUser user);
	
	CompletableFuture<ApplicationUser> editUserByUsername(String username, ApplicationUser user);
	
	CompletableFuture<Void> deleteUserById(String id);

	CompletableFuture<Void> deleteUserByUsername(String username);
	
	CompletableFuture<List<UserRole>> getUserRoles();
	
	CompletableFuture<UserRole> createUserRole(UserRole newRole);
	
	CompletableFuture<UserRole> editUserRole(String id, UserRole newRole);
	
	CompletableFuture<Void> deleteUserRoleById(String id);
	
	CompletableFuture<List<UserPermissionDTO>> getUserPermissions();
}
