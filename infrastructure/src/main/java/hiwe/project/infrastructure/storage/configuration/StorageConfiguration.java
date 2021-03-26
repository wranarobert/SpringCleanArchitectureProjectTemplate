package ca.project.infrastructure.storage.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.project.domain.external.infrastructure.storage.IFileStorage;
import ca.project.infrastructure.base64.IBase64;
import ca.project.infrastructure.storage.filecouchbase.CouchbaseFileManager;
import ca.project.infrastructure.storage.filecouchbase.CouchbaseFileStorageAdapter;
import ca.project.infrastructure.storage.filecouchbase.interfaces.ICouchbaseFileManager;
import ca.project.infrastructure.storage.filecouchbase.interfaces.ICouchbaseFileRepository;
import ca.project.infrastructure.storage.filecouchbase.interfaces.ICouchbaseFileStorageProperties;

@Configuration
public class StorageConfiguration {
	@Bean
	public ICouchbaseFileManager couchbaseChunkManager(
			IBase64 base64, 
			ICouchbaseFileStorageProperties properties
		) {
		return new CouchbaseFileManager(base64, properties);
	}
	
	@Bean
	public IFileStorage couchbaseFileStorage(
		ICouchbaseFileManager couchbaseChunkManager,
		ICouchbaseFileRepository couchbaseFileRepository
	) {
		return new CouchbaseFileStorageAdapter(couchbaseChunkManager, couchbaseFileRepository);
	}
}
