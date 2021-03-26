package ca.project.infrastructure.couchbase.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.config.BeanNames;
import org.springframework.data.couchbase.core.convert.MappingCouchbaseConverter;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.support.IndexManager;

@Configuration
@EnableCouchbaseRepositories("ca.project.infrastructure.couchbase.contexts")
public class PersistenceConfiguration extends AbstractCouchbaseConfiguration {
	@Autowired
	CouchbaseProperties couchbaseProperties;

	@Override
	protected List<String> getBootstrapHosts() {
		return couchbaseProperties.getBootstrapHosts();
	}

	@Override
	protected String getBucketName() {
		return couchbaseProperties.getBucket().getName();
	}

	@Override
	protected String getBucketPassword() {
		return couchbaseProperties.getBucket().getPassword();
	}

	@Override
	@ConditionalOnMissingBean(name = BeanNames.COUCHBASE_INDEX_MANAGER)
	@Bean(name = BeanNames.COUCHBASE_INDEX_MANAGER)
	public IndexManager indexManager() {
		return new IndexManager(true, true, true);
	}

	@Override
	public String typeKey() {
		return MappingCouchbaseConverter.TYPEKEY_SYNCGATEWAY_COMPATIBLE;
	}
}
