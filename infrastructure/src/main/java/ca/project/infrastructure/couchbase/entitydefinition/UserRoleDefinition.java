package ca.project.infrastructure.couchbase.entitydefinition;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import com.couchbase.client.java.repository.annotation.Id;

import ca.project.domain.entities.identity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document
public class UserRoleDefinition extends UserRole {
	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String id;
}
