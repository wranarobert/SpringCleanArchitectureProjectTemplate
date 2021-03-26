package ca.project.application.configurations.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter @Setter
public class EnvironmentConfiguration {
    @Value("${environment}")
	private String environment;
}
