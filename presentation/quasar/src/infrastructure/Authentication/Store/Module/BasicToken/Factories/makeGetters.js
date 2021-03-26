export default function (storeModule, settings) {
  let additionalGetters = {};

  if (storeModule.getters) {
    additionalGetters = storeModule.getters;
  }

  return {
    ...additionalGetters,
    isAuthenticated: ({ authenticationTokens }) => !!authenticationTokens,
    user: ({ user }) => user,
    permissions: ({ user }) => user && user.permissions,
    accessToken: ({ authenticationTokens }) => authenticationTokens && authenticationTokens.accessToken,
    accessTokenHeader: ({ authenticationTokens }) => authenticationTokens && `${settings.tokenPrefix} ${authenticationTokens.accessToken}`,
  };
}
