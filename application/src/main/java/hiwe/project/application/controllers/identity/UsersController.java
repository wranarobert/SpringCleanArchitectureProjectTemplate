package ca.project.application.controllers.identity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import an.awesome.pipelinr.Pipeline;
import ca.project.domain.dto.response.identity.ApplicationUserResponse;
import ca.project.domain.entities.identity.ApplicationUser;
import ca.project.domain.external.infrastructure.couchbase.IUserRepository;
import ca.project.service.identity.users.commands.CreateUserCommand;
import ca.project.service.identity.users.commands.DeleteUserCommand;
import ca.project.service.identity.users.commands.EditUserCommand;
import ca.project.service.identity.users.queries.GetUserByIdQuery;
import ca.project.service.identity.users.queries.GetUserByUsernameQuery;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
    Pipeline pipeline;
	
	@Autowired
	IUserRepository userRepository;
	
	@Async
	@GetMapping
	@PreAuthorize("hasAuthority('IDENTITY_MODULE')")
	public CompletableFuture<List<ApplicationUser>> getUsers() {
		return userRepository.findAll();
	}
	
	@Async
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('IDENTITY_MODULE')")
	public CompletableFuture<ApplicationUserResponse> getUserById(@PathVariable("id") String id) {
		return new GetUserByIdQuery(id).execute(pipeline);
	}
	
	@Async
	@GetMapping("username/{username}")
	@PreAuthorize("hasAuthority('IDENTITY_MODULE')")
	public CompletableFuture<ApplicationUserResponse> getUserByUsername(@PathVariable("username") String username) {
		return new GetUserByUsernameQuery(username).execute(pipeline);
	}
	
	@Async
	@PostMapping
	@PreAuthorize("hasAuthority('IDENTITY_MODULE')")
	public CompletableFuture<ApplicationUser> createUser(@RequestBody CreateUserCommand createUserCommand) {
		return createUserCommand.execute(pipeline);
	}
	
	@Async
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('IDENTITY_MODULE')")
	public CompletableFuture<ApplicationUser> editUser(@PathVariable("id") String id, @RequestBody ApplicationUser user) {
		return new EditUserCommand(id, user).execute(pipeline);
	}
	
	@Async
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('IDENTITY_MODULE')")
	public CompletableFuture<Void> deleteUserById(@PathVariable("id") String id) {
		return new DeleteUserCommand(id).execute(pipeline);
	}
}
