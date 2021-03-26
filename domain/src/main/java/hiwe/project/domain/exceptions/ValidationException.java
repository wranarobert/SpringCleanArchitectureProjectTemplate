package ca.project.domain.exceptions;

import java.util.List;

import ca.project.domain.dto.error.FieldViolation;

public class ValidationException extends ServiceErrorException {
	private static final long serialVersionUID = 1L;
	
	private List<FieldViolation> failures;
	
	public ValidationException(List<FieldViolation> failures) {
		super("One or more validation failures have occurred.");
		
		this.failures = failures;
	}
	
	public List<FieldViolation> getFailures(){
		return failures;
	}
}
