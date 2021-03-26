package ca.project.infrastructure.pdfgenerator;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "report")
@Getter @Setter
public class ReportProperties {
	private String basePath = "/report";
}
