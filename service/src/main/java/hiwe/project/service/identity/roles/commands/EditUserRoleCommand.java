package ca.project.service.identity.roles.commands;

import java.util.concurrent.CompletableFuture;

import javax.inject.Named;

import an.awesome.pipelinr.Command;
import ca.project.domain.entities.identity.UserRole;
import ca.project.domain.external.infrastructure.security.IIdentityManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EditUserRoleCommand implements Command<CompletableFuture<UserRole>> {
	private String id;
	private UserRole editedUserRole;
	
	@Named
	@AllArgsConstructor
    public static class Handler implements Command.Handler<EditUserRoleCommand, CompletableFuture<UserRole>> {
		private final IIdentityManager identityManager;

		@Override
		public CompletableFuture<UserRole> handle(EditUserRoleCommand command) {
			return identityManager.editUserRole(command.getId(), command.getEditedUserRole());
		}
    }
}

