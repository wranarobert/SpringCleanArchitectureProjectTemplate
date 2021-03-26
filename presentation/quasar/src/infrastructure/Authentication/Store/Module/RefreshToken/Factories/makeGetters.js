export default function (storeModule) {
  let additionalGetters = {};

  if (storeModule.getters) {
    additionalGetters = storeModule.getters;
  }

  return {
    ...additionalGetters,
    refreshToken: ({ authenticationTokens }) => authenticationTokens && authenticationTokens.refreshToken,
    validTokenDurationMS: ({ authenticationTokens }) => authenticationTokens.accessTokenExpiresIn * 1000 - 500,
  };
}
