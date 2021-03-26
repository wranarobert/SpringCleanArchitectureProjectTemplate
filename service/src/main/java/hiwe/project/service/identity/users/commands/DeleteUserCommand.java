package ca.project.service.identity.users.commands;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Named;

import org.modelmapper.ModelMapper;

import an.awesome.pipelinr.Command;
import ca.project.domain.dto.common.IdDTO;
import ca.project.domain.entities.identity.ApplicationUser;
import ca.project.domain.external.infrastructure.couchbase.IUserRepository;
import ca.project.domain.external.infrastructure.security.IIdentityManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserCommand implements Command<CompletableFuture<Void>> {
	private String id;
	
	@Named
	@AllArgsConstructor
    public static class Handler implements Command.Handler<DeleteUserCommand, CompletableFuture<Void>> {
		private final IIdentityManager identityManager;
		
		@Override
		public CompletableFuture<Void> handle(DeleteUserCommand command) {
			return identityManager.deleteUserById(command.getId());
		}
    }
}

