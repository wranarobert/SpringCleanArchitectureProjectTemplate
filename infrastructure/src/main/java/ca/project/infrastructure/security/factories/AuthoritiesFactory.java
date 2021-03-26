package ca.project.infrastructure.security.factories;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import ca.project.domain.entities.identity.UserRole;

@Component
public class AuthoritiesFactory {
	@Autowired
	PermissionsFactory permissionsFactory;
	
	public Set<SimpleGrantedAuthority> create(Set<UserRole> roles) {
		
		var roleGrantAuthoritiesStream = roles.stream()
			.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()));
		
		var permissionGrantAuthoritiesStream = permissionsFactory.create(roles).stream()
			.map(permission -> new SimpleGrantedAuthority(permission.name()));
		
		return Stream
    		.concat(roleGrantAuthoritiesStream, permissionGrantAuthoritiesStream)
    		.collect(Collectors.toSet());
	}
}
