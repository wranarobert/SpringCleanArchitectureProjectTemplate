import Validator from 'src/infrastructure/Validator/Validator';

function propertyValidation(property, value, ModelConstraints) {
  const validation = Validator.validateSingle(property, value, ModelConstraints);
  return validation.valid || validation.firstMessage;
}

function modelValidation(model, ModelConstraints) {
  return Validator.validate(model, ModelConstraints);
}

function createPropetyValidations(ModelConstraints) {
  return Object.keys(ModelConstraints).reduce((methods, property) => {
    methods[`${property}Validation`] = (value) => propertyValidation(property, value, ModelConstraints);
    return methods;
  }, {});
}

function createModelValidation(modelName, ModelConstraints) {
  return {
    [`${modelName}Validation`]: (model) => modelValidation(model, ModelConstraints),
  };
}

export default function (modelName, ModelConstraints) {
  return {
    methods: {
      ...createModelValidation(modelName, ModelConstraints),
      ...createPropetyValidations(ModelConstraints),
    },
  };
}
