import validatorMixin from 'src/infrastructure/Validator/validatorMixin';
import LoginConstraints from './LoginConstraints';

export default validatorMixin('login', LoginConstraints);
