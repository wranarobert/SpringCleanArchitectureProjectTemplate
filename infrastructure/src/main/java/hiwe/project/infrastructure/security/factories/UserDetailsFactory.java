package ca.project.infrastructure.security.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ca.project.domain.dto.models.identity.ApplicationUserDTO;
import ca.project.domain.dto.response.identity.ApplicationUserResponse;
import ca.project.domain.entities.identity.ApplicationUser;

@Component
public class UserDetailsFactory {
	@Autowired
	AuthoritiesFactory authoritiesFactory;
	
	public UserDetails create(ApplicationUserDTO user) {
		var authorities = authoritiesFactory.create(user.getRoles());
		
        return User
			.withUsername(user.getUsername())
	        .password(user.getPassword())
	        .authorities(authorities)
        .build();
	}
}
