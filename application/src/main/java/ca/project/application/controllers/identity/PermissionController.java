package ca.project.application.controllers.identity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import an.awesome.pipelinr.Pipeline;
import ca.project.domain.dto.models.identity.UserPermissionDTO;
import ca.project.domain.entities.identity.UserPermission;
import ca.project.service.identity.permissions.GetPermissionsQuery;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
	@Autowired
	private Pipeline pipeline;
	
	@Async
	@GetMapping
	@PreAuthorize("hasAuthority('IDENTITY_MODULE')")
	public CompletableFuture<List<UserPermissionDTO>> getUserRoles() {
		return new GetPermissionsQuery().execute(pipeline);
	}
}
