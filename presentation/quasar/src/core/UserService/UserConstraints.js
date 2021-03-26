export default {
  username: {
    presence: true,
    length: {
      minimum: 4,
      message: 'must be at least 4 characters',
    },
  },
  password: {
    presence: true,
    length: {
      minimum: 4,
      message: 'must be at least 4 characters',
    },
  },
  /*
  roleIds: {
    type: 'array',
    presence: {
      allowEmpty: false,
      message: 'must have at least one role',
    },
  },
  */
};
