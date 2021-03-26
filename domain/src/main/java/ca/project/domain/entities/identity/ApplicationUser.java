package ca.project.domain.entities.identity;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationUser {
	private String id;
	private String username;
	private String password;
	
	@Builder.Default
	private Set<String> roleIds = new HashSet<String>();
	
	@Builder.Default
	private boolean isEnabled = true;
}
