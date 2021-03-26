package ca.project.domain.external.infrastructure.couchbase;

import java.util.concurrent.CompletableFuture;

import ca.project.domain.entities.identity.UserRole;
import ca.project.domain.external.infrastructure.ICrudRepository;

public interface IUserRoleRepository extends ICrudRepository<UserRole, String> {
	CompletableFuture<UserRole> getUserRoleById(String id);
	CompletableFuture<UserRole> getUserRoleByName(String name);
}
