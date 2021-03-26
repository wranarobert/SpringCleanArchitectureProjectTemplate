package ca.project.infrastructure.storage.filesystem.models;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import ca.project.infrastructure.storage.filesystem.interfaces.ISystemFileStorageProperties;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "storage.system")
@Getter @Setter
public class SystemFileStorageProperties implements ISystemFileStorageProperties {
	private String location = "";
}
