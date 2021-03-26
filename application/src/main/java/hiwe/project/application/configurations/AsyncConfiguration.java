package ca.project.application.configurations;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import lombok.Data;

@EnableAsync
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.task.execution.pool")
public class AsyncConfiguration implements AsyncConfigurer {
	private int coreSize = 8;
	private int maxSize = Integer.MAX_VALUE;
	private int queueCapacity = Integer.MAX_VALUE;
	
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		executor.setCorePoolSize(coreSize);
		executor.setMaxPoolSize(maxSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("async-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		
		executor.initialize();
		
		return new DelegatingSecurityContextAsyncTaskExecutor(executor);
	}
}