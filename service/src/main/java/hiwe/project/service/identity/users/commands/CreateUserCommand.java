package ca.project.service.identity.users.commands;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import javax.inject.Named;

import org.modelmapper.ModelMapper;

import an.awesome.pipelinr.Command;
import ca.project.domain.entities.identity.ApplicationUser;
import ca.project.domain.external.infrastructure.security.IIdentityManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserCommand implements Command<CompletableFuture<ApplicationUser>> {

	private String username;
	private String password;
	Set<String> roleIds;
	
	@Named
	@AllArgsConstructor
    public static class Handler implements Command.Handler<CreateUserCommand, CompletableFuture<ApplicationUser>> {
		private final IIdentityManager identityManager;
		private final ModelMapper modelMapper;

		@Override
		public CompletableFuture<ApplicationUser> handle(CreateUserCommand command) {
			ApplicationUser user = modelMapper.map(command, ApplicationUser.class);
			return identityManager.createUser(user);
		}
    }
}

