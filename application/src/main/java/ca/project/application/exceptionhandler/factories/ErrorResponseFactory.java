package ca.project.application.exceptionhandler.factories;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import ca.project.domain.dto.error.ErrorDTO;
import ca.project.domain.dto.error.ValidationErrorDTO;
import ca.project.domain.exceptions.NotFoundException;
import ca.project.domain.exceptions.ServiceErrorException;
import ca.project.domain.exceptions.ValidationException;

@Component
public class ErrorResponseFactory {
	
	public ResponseEntity<Object> create(ServiceErrorException exception) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		if(exception instanceof ValidationException) {
			return new ResponseEntity<Object>(
				new ValidationErrorDTO(
					"Validation Error", 
					exception.getMessage(),
					((ValidationException)exception).getFailures()
				),
	    		new HttpHeaders(), 
	    		httpStatus
	        );
		} else if(exception instanceof NotFoundException) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<Object>(
			new ErrorDTO(
				httpStatus.getReasonPhrase(), 
				exception.getMessage()
			),
    		new HttpHeaders(), 
    		httpStatus
        );
	}
}
