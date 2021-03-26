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
import ca.project.domain.entities.identity.UserRole;
import ca.project.service.identity.roles.commands.DeleteUserRoleCommand;
import ca.project.service.identity.roles.commands.EditUserRoleCommand;
import ca.project.service.identity.roles.commands.CreateUserRole.CreateUserRoleCommand;
import ca.project.service.identity.roles.queries.GetUserRolesQuery;

@RestController
@RequestMapping("/api/roles")
public class UserRoleController {
	@Autowired
	private Pipeline pipeline;
	
	@Async
	@GetMapping
	@PreAuthorize("hasAuthority('IDENTITY_MODULE')")
	public CompletableFuture<List<UserRole>> getUserRoles() {
		return new GetUserRolesQuery().execute(pipeline);
	}
	
	@Async
	@PostMapping
	@PreAuthorize("hasAuthority('IDENTITY_MODULE')")
	public CompletableFuture<UserRole> createUserRole(@RequestBody CreateUserRoleCommand createUserRoleCommand) {
		return createUserRoleCommand.execute(pipeline);
	}
	
	@Async
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('IDENTITY_MODULE')")
	public CompletableFuture<UserRole> editUserRole(@PathVariable("id") String id, @RequestBody UserRole newRole) {
		return new EditUserRoleCommand(id, newRole).execute(pipeline);
	}
	
	@Async
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('IDENTITY_MODULE')")
	public CompletableFuture<Void> deleteUserRoleById(@PathVariable("id") String id) {
		return new DeleteUserRoleCommand(id).execute(pipeline);
	}
}
