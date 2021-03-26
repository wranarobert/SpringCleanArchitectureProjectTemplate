package ca.project.service.identity.users.commands;

import java.util.concurrent.CompletableFuture;

import javax.inject.Named;

import an.awesome.pipelinr.Command;
import ca.project.domain.entities.identity.ApplicationUser;
import ca.project.domain.external.infrastructure.security.IIdentityManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EditUserCommand implements Command<CompletableFuture<ApplicationUser>>{
	private String id;
	private ApplicationUser editedUser;
	
	@Named
	@AllArgsConstructor
    public static class Handler implements Command.Handler<EditUserCommand, CompletableFuture<ApplicationUser>> {
		private final IIdentityManager identityManager;

		@Override
		public CompletableFuture<ApplicationUser> handle(EditUserCommand command) {
			return identityManager.editUser(command.getId(), command.getEditedUser());
		}
    }
}

