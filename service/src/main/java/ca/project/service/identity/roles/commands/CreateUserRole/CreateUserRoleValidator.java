package ca.project.service.identity.roles.commands.CreateUserRole;

import br.com.fluentvalidator.AbstractValidator;
import static br.com.fluentvalidator.predicate.LogicalPredicate.*;
import static br.com.fluentvalidator.predicate.StringPredicate.*;

import javax.inject.Named;

@Named
public class CreateUserRoleValidator extends AbstractValidator<CreateUserRoleCommand> {

	@Override
	public void rules() {
		ruleFor(CreateUserRoleCommand::getName)
			.must(not(stringEmptyOrNull()))
				.withFieldName("name")
		        .withMessage("Name must not be empty");
		        
	}
}
