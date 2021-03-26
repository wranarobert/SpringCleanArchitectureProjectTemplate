package ca.project.application.configurations;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;

@Configuration
public class PipelinrConfiguration {
	
	@SuppressWarnings("rawtypes")
	@Bean
	Pipeline pipeline(ObjectProvider<Command.Handler> commandHandlers, ObjectProvider<Command.Middleware> middlewares) {
	    return new Pipelinr()
	      .with(commandHandlers::stream)
	      .with(middlewares::orderedStream);
	}
	
}
