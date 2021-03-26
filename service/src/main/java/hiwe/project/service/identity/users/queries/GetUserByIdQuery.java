package ca.project.service.identity.users.queries;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.modelmapper.ModelMapper;

import an.awesome.pipelinr.Command;
import ca.project.domain.dto.models.identity.ApplicationUserDTO;
import ca.project.domain.dto.response.identity.ApplicationUserResponse;
import ca.project.domain.entities.identity.ApplicationUser;
import ca.project.domain.external.infrastructure.security.IIdentityManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class GetUserByIdQuery implements Command<CompletableFuture<ApplicationUserResponse>>{
	private String id;
	
	@Named
	@AllArgsConstructor
    public static class Handler implements Command.Handler<GetUserByIdQuery, CompletableFuture<ApplicationUserResponse>> {
		private final IIdentityManager identityManager;
		private final ModelMapper modelMapper;

		@Override
		public CompletableFuture<ApplicationUserResponse> handle(GetUserByIdQuery command) {
			var userById = identityManager.getUserById(command.getId()).join();
			
			var user = modelMapper.map(userById, ApplicationUserResponse.class);
			
			var roleNames = userById.getRoles().stream()
				.map(role -> role.getName())
				.collect(Collectors.toSet());
			
			user.setRoles(roleNames);
			
			return  CompletableFuture.completedFuture(user);
		}
    }
}

