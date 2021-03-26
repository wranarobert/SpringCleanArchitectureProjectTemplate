package ca.project.domain.external.infrastructure;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ICrudRepository<T, ID> {
	CompletableFuture<List<T>> findAll();
	CompletableFuture<T> save(T entity);
	CompletableFuture<Void> deleteById(ID id);
}
