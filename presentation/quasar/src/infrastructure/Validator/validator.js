import { validate as validator } from 'validate.js';
import ValidationResult from './modules/ValidationResult';

export function validateSingle(property, value, constraints) {
  return new ValidationResult(
    validator.single(value, constraints[property]),
  );
}

export function validate(entity, constraints) {
  return new ValidationResult(
    validator.validate(entity, constraints),
  );
}

export default {
  validate,
  validateSingle,
};

/*
  Example

  const validationResult = Validator.validate(user, UserConstraints);
  const validationResult = Validator.validateSingle('username', user.username, UserConstraints);

  {
    valid: boolean,
    validationResult: validate.js/constraints
  }
*/
