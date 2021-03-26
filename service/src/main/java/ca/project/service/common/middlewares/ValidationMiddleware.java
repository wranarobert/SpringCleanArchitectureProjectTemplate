package ca.project.service.common.middlewares;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Named;

import an.awesome.pipelinr.Command;
import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;
import ca.project.domain.dto.error.FieldViolation;
import ca.project.domain.exceptions.ValidationException;

@Named
public class ValidationMiddleware implements Command.Middleware {
	private final Set<Validator<?>> validators;
	
	public ValidationMiddleware(Set<Validator<?>> validators) {
		this.validators = validators;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
		
		String commandTypeName = command.getClass().getTypeName();
		
		Optional<Validator<?>> possibleValidator = getValidator(commandTypeName);
		
		if(possibleValidator.isPresent()) {
			Validator<C> validator = (AbstractValidator<C>) possibleValidator.get();
			
			ValidationResult result = validator.validate(command);
			
			if(!result.isValid()) {
				throw new ValidationException(
					result.getErrors().stream()
						.map(error -> 
							new FieldViolation(
								error.getField(),
								error.getMessage()
							)
						)
						.collect(Collectors.toList())
				);
			}
		}
		
        R response = next.invoke();
        
        return response;
	}
	
	private Optional<Validator<?>> getValidator(String commandTypeName){
		return validators.stream()
			.filter(v -> 
				((ParameterizedType) v.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName()
				.equals(commandTypeName)
			).findAny();
	}
	
}
