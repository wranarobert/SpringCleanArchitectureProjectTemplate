package ca.project.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import ca.project.domain.dto.request.LoginRequest;
import ca.project.domain.dto.response.identity.AuthenticationTokenResponse;
import ca.project.domain.exceptions.InvalidCredentialsException;
import ca.project.domain.external.infrastructure.security.IAuthenticationManager;

@Component
public class AuthenticationManagerAdapter implements IAuthenticationManager {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Override
	public AuthenticationTokenResponse token(LoginRequest loginRequest) {
		try {
			final Authentication loginAuthentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					loginRequest.getUsername(),
					loginRequest.getPassword()
				)
			);
			
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(loginAuthentication);
			
			OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
			
		    //reference
		    final OAuth2AccessToken accessToken = tokenStore.getAccessToken(authentication);
		    
			return AuthenticationTokenResponse.builder()
					.accessToken(accessToken.getValue())
				.build();
		} catch (AuthenticationException e) {
			throw new InvalidCredentialsException();
		}
	}

}
