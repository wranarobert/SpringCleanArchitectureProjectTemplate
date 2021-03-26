package ca.project.domain.exceptions;

public class ServiceErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceErrorException() {
        super("An error has occurred");
    }
	
	public ServiceErrorException(String errorMessage) {
        super(errorMessage);
    }
}
