package ca.project.domain.dto.response.identity;

import java.util.List;

import ca.project.domain.entities.identity.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
	private AuthenticationUserResponse authenticatedUser;
	private AuthenticationTokenResponse authenticationToken;
}
