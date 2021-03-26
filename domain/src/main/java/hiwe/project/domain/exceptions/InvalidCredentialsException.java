package ca.project.domain.exceptions;

public class InvalidCredentialsException extends ServiceErrorException {
	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException() {
        super("Invalid user credentials");
    }
	
	public InvalidCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
