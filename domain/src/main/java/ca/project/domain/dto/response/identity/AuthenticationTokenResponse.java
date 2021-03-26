package ca.project.domain.dto.response.identity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationTokenResponse {
	private String accessToken;
	private String accessTokenExpiresIn;
	private String refreshToken;
	private String refreshTokenExpiresIn;
}
