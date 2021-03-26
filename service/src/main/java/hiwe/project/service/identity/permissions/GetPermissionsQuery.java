package ca.project.service.identity.permissions;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Named;

import an.awesome.pipelinr.Command;
import ca.project.domain.dto.models.identity.UserPermissionDTO;
import ca.project.domain.entities.identity.UserPermission;
import ca.project.domain.external.infrastructure.security.IIdentityManager;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GetPermissionsQuery implements Command<CompletableFuture<List<UserPermissionDTO>>> {
	
	@Named
	@AllArgsConstructor
    public static class Handler implements Command.Handler<GetPermissionsQuery, CompletableFuture<List<UserPermissionDTO>>> {
		private final IIdentityManager identityManager;

		@Override
		public CompletableFuture<List<UserPermissionDTO>> handle(GetPermissionsQuery command) {
			return identityManager.getUserPermissions();
		}
    }
}

