package ca.project.domain.exceptions;

public class FileStorageException extends ServiceErrorException {
	private static final long serialVersionUID = 1L;

	public FileStorageException(String errorMessage) {
        super(errorMessage);
    }
}
