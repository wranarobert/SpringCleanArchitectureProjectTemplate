package ca.project.service.identity.roles.commands.CreateUserRole;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import javax.inject.Named;

import org.modelmapper.ModelMapper;

import an.awesome.pipelinr.Command;
import ca.project.domain.entities.identity.UserPermission;
import ca.project.domain.entities.identity.UserRole;
import ca.project.domain.external.infrastructure.security.IIdentityManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserRoleCommand implements Command<CompletableFuture<UserRole>> {
	private String name;
	private String description;

	private Set<UserPermission> permissions = new HashSet<UserPermission>();
	
	@Named
	@AllArgsConstructor
    public static class Handler implements Command.Handler<CreateUserRoleCommand, CompletableFuture<UserRole>> {
		private final IIdentityManager identityManager;
		private final ModelMapper modelMapper;

		@Override
		public CompletableFuture<UserRole> handle(CreateUserRoleCommand command) {
			UserRole userRole = modelMapper.map(command, UserRole.class);
			
			return identityManager.createUserRole(userRole);
		}
    }
}

