package ca.project.domain.dto.error;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationErrorDTO extends ErrorDTO {
	private List<FieldViolation> violations;
	
	public ValidationErrorDTO(String error, String message, List<FieldViolation> violations) {
		super(error, message);
		
		this.violations = violations;
	}
}
