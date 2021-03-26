package ca.project.presentation.android;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import ca.project.domain.entities.User;
import ca.project.domain.external.infrastructure.couchbase.IUserRepository;

public class UserRepository implements IUserRepository {

    /*
    inject couchbase mobile
     */

    @Override
    public CompletableFuture<List<User>> findAll() {
        return null;
    }

    @Override
    public CompletableFuture<User> save(User entity) {
        return CompletableFuture.completedFuture(new User("dsf", entity.getUsername(), "dsfasdf", "dsafdaf"));
    }

    @Override
    public CompletableFuture<Void> deleteById(String s) {
        return null;
    }
}
