import validatorMixin from 'src/infrastructure/Validator/validatorMixin';
import UserConstraints from './UserConstraints';

export default validatorMixin('user', UserConstraints);
