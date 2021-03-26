package ca.project.service.identity.roles.queries;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Named;

import an.awesome.pipelinr.Command;
import ca.project.domain.entities.identity.UserRole;
import ca.project.domain.external.infrastructure.security.IIdentityManager;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GetUserRolesQuery implements Command<CompletableFuture<List<UserRole>>> {
	
	@Named
	@AllArgsConstructor
    public static class Handler implements Command.Handler<GetUserRolesQuery, CompletableFuture<List<UserRole>>> {
		private final IIdentityManager identityManager;

		@Override
		public CompletableFuture<List<UserRole>> handle(GetUserRolesQuery command) {
			return identityManager.getUserRoles();
		}
    }
}

