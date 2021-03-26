package ca.project.domain.exceptions;

public class NotFoundException extends ServiceErrorException {
	private static final long serialVersionUID = 1L;

	public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
	
	public NotFoundException(Class<?> entityClass) {
        super("Entity " + entityClass.getSimpleName() + " not found.");
    }
}
