package ca.project.domain.dto.response.identity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationUserResponse {
	private String userId;
	private String username;
	private List<String> roles;
}
