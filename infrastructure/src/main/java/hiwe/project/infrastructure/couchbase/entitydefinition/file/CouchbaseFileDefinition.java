package ca.project.infrastructure.couchbase.entitydefinition.file;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import com.couchbase.client.java.repository.annotation.Id;

import ca.project.infrastructure.storage.filecouchbase.models.CouchbaseFile;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document
public class CouchbaseFileDefinition extends CouchbaseFile {
	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String id;
}
