package ca.project.infrastructure.security.factories;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import ca.project.domain.entities.identity.UserPermission;
import ca.project.domain.entities.identity.UserRole;

@Component
public class PermissionsFactory {
	public Set<UserPermission> create(Set<UserRole> roles) {
		var rolePermissions = roles.stream()
				.map(role -> role.getPermissions())
				.flatMap(Set::stream)
				.collect(Collectors.toSet());
		
        return rolePermissions;
	}
}
