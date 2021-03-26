package ca.project.domain.external.infrastructure.couchbase;

import java.util.concurrent.CompletableFuture;

import ca.project.domain.dto.models.identity.ApplicationUserDTO;
import ca.project.domain.dto.response.identity.ApplicationUserResponse;
import ca.project.domain.entities.identity.ApplicationUser;
import ca.project.domain.external.infrastructure.ICrudRepository;

public interface IUserRepository extends ICrudRepository<ApplicationUser, String> {
	CompletableFuture<ApplicationUser> getUserById(String id);
	CompletableFuture<ApplicationUser> getUserByUsername(String username);
	CompletableFuture<ApplicationUserDTO> getUserWithRolesByUsername(String username);
}
