package ca.project.infrastructure.storage.filecouchbase.models;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import ca.project.infrastructure.storage.filecouchbase.interfaces.ICouchbaseFileStorageProperties;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "storage.couchbase")
@Getter @Setter
public class CouchbaseFileStorageProperties implements ICouchbaseFileStorageProperties {
	private int chunkSize = 10;
}
