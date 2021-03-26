package ca.project.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.project.domain.exceptions.NotFoundException;
import ca.project.domain.external.infrastructure.security.IIdentityManager;
import ca.project.infrastructure.security.factories.UserDetailsFactory;

@Service
public class GetUserByUsernameService implements UserDetailsService {
	@Autowired
	IIdentityManager identityManager;
	
	@Autowired
	UserDetailsFactory userDetailsFactory;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			var user = identityManager.getUserByUsername(username).join();
		
			var userDetails = userDetailsFactory.create(user);
			
			return userDetails;
		} catch(NotFoundException e) {
			throw new UsernameNotFoundException("Username not found.");
		}
	}

}
