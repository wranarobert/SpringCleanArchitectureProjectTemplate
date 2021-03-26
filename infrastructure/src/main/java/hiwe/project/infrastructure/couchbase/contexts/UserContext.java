package ca.project.infrastructure.couchbase.contexts;

import java.util.List;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.N1qlSecondaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

import ca.project.domain.dto.response.identity.ApplicationUserResponse;
import ca.project.domain.entities.identity.ApplicationUser;
import ca.project.infrastructure.couchbase.entitydefinition.UserDefinition;

@ViewIndexed(designDoc = "UserEntity")
@N1qlPrimaryIndexed
public interface UserContext extends CrudRepository<UserDefinition, String> {
	@Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
	List<ApplicationUser> getAll();
	
	UserDefinition findFirstByUsername(String username);
}
