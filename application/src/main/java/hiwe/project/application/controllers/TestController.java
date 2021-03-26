package ca.project.application.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.project.infrastructure.processdata.EnableQuery;
import ca.project.infrastructure.processdata.Model;

@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("sync")
	@EnableQuery
	public List<Model> loginUser() {
		var list = Arrays.asList(
				new Model("1", "Name1"),
				new Model("2", "Name2")
			);
		
		return list;
	}
	
	@Async
	@GetMapping("async")
	@EnableQuery
	public CompletableFuture<List<Model>> loginUserAsync() {
		var list = Arrays.asList(
				new Model("1", "Name1"),
				new Model("2", "Name2")
			);
		
		return CompletableFuture.completedFuture(list);
	}
}
