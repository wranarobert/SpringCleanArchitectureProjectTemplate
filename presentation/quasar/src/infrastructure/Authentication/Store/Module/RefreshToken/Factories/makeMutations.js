export default function (storeModule) {
  let additionalMutations = {};

  if (storeModule.mutations) {
    additionalMutations = storeModule.mutations;
  }

  return {
    ...additionalMutations,
    saveAuthenticationTokens: (state, authenticationTokens) => {
      state.authenticationTokens = {
        accessToken: authenticationTokens.access_token,
        accessTokenExpiresIn: authenticationTokens.expires_in,
        refreshToken: authenticationTokens.refresh_token,
      };
    },
    saveRefreshedToken: (state, { access_token, expires_in }) => {
      state.authenticationTokens.accessToken = access_token;
      state.authenticationTokens.accessTokenExpiresIn = expires_in;
    },
    saveTokenExpirationTimerId: (state, timerId) => {
      state.tokenExpirationTimerId = timerId;
    },
    clearTokenExpirationTimerId: (state) => {
      state.tokenExpirationTimerId = null;
    },
  };
}
