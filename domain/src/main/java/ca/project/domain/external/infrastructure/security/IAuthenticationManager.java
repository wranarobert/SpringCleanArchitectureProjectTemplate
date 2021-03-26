package ca.project.domain.external.infrastructure.security;

import java.util.concurrent.CompletableFuture;

import ca.project.domain.dto.request.LoginRequest;
import ca.project.domain.dto.response.identity.AuthenticationTokenResponse;

public interface IAuthenticationManager {
	AuthenticationTokenResponse token(LoginRequest loginRequest);
}
