package ca.project.domain.dto.models.identity;

import ca.project.domain.entities.identity.UserPermission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionDTO {
	private UserPermission permission;
	private String name;
	private String description;
}
