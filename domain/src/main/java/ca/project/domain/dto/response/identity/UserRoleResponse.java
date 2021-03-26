package ca.project.domain.dto.response.identity;

import java.util.Set;

import ca.project.domain.entities.identity.UserPermission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class UserRoleResponse {
	private String name;
	private Set<UserPermission> rolePermissions;
}
