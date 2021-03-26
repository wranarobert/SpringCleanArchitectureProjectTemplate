export default {
  isAdmin: ({ user }) => !!user && user.roles.includes('ADMIN'),
};
