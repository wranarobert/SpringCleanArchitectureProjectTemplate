package ca.project.domain.exceptions;

public class EntityAlreadyExistsException extends ServiceErrorException {
	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
	
	public EntityAlreadyExistsException(Class<?> entityClass) {
        super("Entity " + entityClass.getSimpleName() + " already exists.");
    }
}
