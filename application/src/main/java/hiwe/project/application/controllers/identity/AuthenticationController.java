package ca.project.application.controllers.identity;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import an.awesome.pipelinr.Pipeline;
import ca.project.domain.dto.request.LoginRequest;
import ca.project.domain.dto.response.identity.ApplicationUserResponse;
import ca.project.domain.dto.response.identity.AuthenticationTokenResponse;
import ca.project.domain.external.infrastructure.security.IAuthenticationManager;
import ca.project.service.identity.users.queries.GetUserByUsernameQuery;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
	@Autowired
    Pipeline pipeline;
	
	@Autowired
	IAuthenticationManager authenticationManager;
	
	@PostMapping("token")
	public AuthenticationTokenResponse token() {
		return authenticationManager.token(new LoginRequest("user", "enable"));
	}
	
	@Async
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public CompletableFuture<ApplicationUserResponse> getAuthenticationInfo(OAuth2Authentication authentication) {
		return new GetUserByUsernameQuery(authentication.getName()).execute(pipeline);
	}
}
