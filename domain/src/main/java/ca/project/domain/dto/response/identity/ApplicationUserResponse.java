package ca.project.domain.dto.response.identity;

import java.util.Set;

import ca.project.domain.entities.identity.UserPermission;
import ca.project.domain.entities.identity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserResponse {
	private String id;
	private String username;
	private String password;
	private Set<String> roles;
	private Set<UserPermission> permissions;
}
