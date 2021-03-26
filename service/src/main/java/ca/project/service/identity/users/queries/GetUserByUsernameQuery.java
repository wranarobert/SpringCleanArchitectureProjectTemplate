package ca.project.service.identity.users.queries;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.modelmapper.ModelMapper;

import an.awesome.pipelinr.Command;
import ca.project.domain.dto.response.identity.ApplicationUserResponse;
import ca.project.domain.external.infrastructure.security.IIdentityManager;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserByUsernameQuery implements Command<CompletableFuture<ApplicationUserResponse>>{
	private String username;
	
	@Named
	@AllArgsConstructor
    public static class Handler implements Command.Handler<GetUserByUsernameQuery, CompletableFuture<ApplicationUserResponse>> {
		private final IIdentityManager identityManager;
		private final ModelMapper modelMapper;

		@Override
		public CompletableFuture<ApplicationUserResponse> handle(GetUserByUsernameQuery command) {
			var userByUsername = identityManager.getUserByUsername(command.getUsername()).join();
			
			var user = modelMapper.map(userByUsername, ApplicationUserResponse.class);
			
			var roleNames = userByUsername.getRoles().stream()
				.map(role -> role.getName())
				.collect(Collectors.toSet());
			
			user.setRoles(roleNames);
			
			return  CompletableFuture.completedFuture(user);
		}
    }
}

