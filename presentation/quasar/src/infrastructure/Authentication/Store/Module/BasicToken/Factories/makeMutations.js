export default function (storeModule) {
  let additionalMutations = {};

  if (storeModule.mutations) {
    additionalMutations = storeModule.mutations;
  }

  return {
    saveAuthenticationTokens: (state, authenticationTokens) => {
      state.authenticationTokens = {
        accessToken: authenticationTokens.access_token,
      };
    },
    saveAuthenticatedUser: (state, authenticatedUser) => {
      state.user = {
        id: authenticatedUser.id,
        username: authenticatedUser.username,
        roles: authenticatedUser.roles,
        permissions: authenticatedUser.permissions,
      };
    },
    ...additionalMutations,
    clearAuthentication: (state) => {
      state.user = null;
      state.authenticationTokens = null;
    },
  };
}
