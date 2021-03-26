package ca.project.service.common.middlewares;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;

import javax.inject.Named;

import an.awesome.pipelinr.Command;
import ca.project.domain.exceptions.ServiceErrorException;
import ca.project.domain.external.infrastructure.logger.ILogger;

@Named
public class LoggingMiddleware implements Command.Middleware {
	private final ILogger log;
	
	public LoggingMiddleware(ILogger log) {
		this.log = log;
	}
	
	@Override
	public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
		String commandName = command.getClass().getSimpleName();
		
		Instant startTime = Instant.now();
		
		log.info(logMessage("Request", commandName));
		
		try {
	        R response = next.invoke();
	        
	        if(response instanceof CompletableFuture) {
				((CompletableFuture<?>) response).join();
	        }
	
	        Instant endTime = Instant.now();
	        
	        long elapsedMiliseconds = Duration.between(startTime, endTime).toMillis();
	        
	        log.info(logMessage("Response", commandName, elapsedMiliseconds));
	        
	        if(elapsedMiliseconds > 500) {
	        	log.warn(logMessage("Long Response", commandName, elapsedMiliseconds));
	        }
	        
	        return response;
		} catch(ServiceErrorException e) {
			log.warn(logMessage("Response Error", commandName, e.getMessage(), command.toString()));
			
			throw e;
		}
	}
	
	private String logMessage(String type, String commandName) {
		return "[" + type + "] - " + "[" + commandName + "]";
	}
	
	private String logMessage(String type, String commandName, long elapsedMiliseconds) {
		return logMessage(type, commandName) + " - [" + elapsedMiliseconds + " ms]";
	}
	
	private String logMessage(String type, String commandName, String errorMessage, String errorValues) {
		return logMessage(type, commandName) + " - [" + errorMessage + "]" + " - [" + errorValues + "]";
	}
}
