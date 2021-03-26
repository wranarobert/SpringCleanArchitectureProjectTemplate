package ca.project.application.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ca.project.application.exceptionhandler.factories.ErrorResponseFactory;
import ca.project.domain.dto.error.ErrorDTO;
import ca.project.domain.dto.error.ValidationErrorDTO;
import ca.project.domain.exceptions.NotFoundException;
import ca.project.domain.exceptions.ServiceErrorException;
import ca.project.domain.exceptions.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	ErrorResponseFactory errorResponseFactory;

	@ExceptionHandler({ ServiceErrorException.class })
    public ResponseEntity<Object> handle(ServiceErrorException exception, WebRequest request) {
		return errorResponseFactory.create(exception);
    }
}
