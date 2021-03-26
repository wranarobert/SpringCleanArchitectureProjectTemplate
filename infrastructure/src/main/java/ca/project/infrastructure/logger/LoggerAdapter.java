package ca.project.infrastructure.logger;

import org.springframework.stereotype.Component;

import ca.project.domain.external.infrastructure.logger.ILogger;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class LoggerAdapter implements ILogger {
	@Override
	public void info(String message) {
		log.info(message);
	}

	@Override
	public void warn(String message) {
		log.warn(message);
	}

	@Override
	public void error(String message) {
		log.error(message);
	}
}
