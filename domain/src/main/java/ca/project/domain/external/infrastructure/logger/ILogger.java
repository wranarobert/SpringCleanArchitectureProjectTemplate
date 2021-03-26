package ca.project.domain.external.infrastructure.logger;

public interface ILogger {
	void info(String message);
	void warn(String message);
	void error(String message);
}
