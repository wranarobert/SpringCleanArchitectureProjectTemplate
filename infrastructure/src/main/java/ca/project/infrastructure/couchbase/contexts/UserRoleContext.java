package ca.project.infrastructure.couchbase.contexts;

import java.util.List;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

import com.couchbase.client.java.document.json.JsonArray;

import ca.project.domain.entities.identity.ApplicationUser;
import ca.project.domain.entities.identity.UserRole;
import ca.project.infrastructure.couchbase.entitydefinition.UserDefinition;
import ca.project.infrastructure.couchbase.entitydefinition.UserRoleDefinition;

@ViewIndexed(designDoc = "UserRoleEntity")
@N1qlPrimaryIndexed
public interface UserRoleContext extends CrudRepository<UserRoleDefinition, String> {
	@Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
	List<UserRole> getAll();
	
	@Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND META(#{#n1ql.bucket}).id IN $1")
	List<UserRole> getByIds(JsonArray ids);
	
	UserRoleDefinition findFirstByName(String name);
}
