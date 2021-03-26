package ca.project.service.identity.roles.commands;

import java.util.concurrent.CompletableFuture;

import javax.inject.Named;

import an.awesome.pipelinr.Command;
import ca.project.domain.external.infrastructure.security.IIdentityManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserRoleCommand implements Command<CompletableFuture<Void>> {
	private String id;
	
	@Named
	@AllArgsConstructor
    public static class Handler implements Command.Handler<DeleteUserRoleCommand, CompletableFuture<Void>> {
		private final IIdentityManager identityManager;
		
		@Override
		public CompletableFuture<Void> handle(DeleteUserRoleCommand command) {
			return identityManager.deleteUserRoleById(command.getId());
		}
    }
}

