export default function (storeModule) {
  let additionalActions = {};

  if (storeModule.actions) {
    additionalActions = storeModule.actions;
  }

  if (!('Login' in additionalActions)) {
    throw new Error('Provide "Login" action that returns Promise');
  }

  if (!('Logout' in additionalActions)) {
    throw new Error('Provide "Logout" action that returns Promise');
  }

  return {
    ...additionalActions,
    Login(context, loginData) {
      return additionalActions.Login(context, loginData).then(() => {
        context.dispatch('[Process] Set Token Expiration Timer');
      });
    },
    Logout(context) {
      return additionalActions.Logout(context).finally(() => {
        context.dispatch('[Process] Clear Token Expiration Timer');
      });
    },
    RefreshToken(context) {
      return additionalActions.RefreshToken(context).then(() => {
        context.dispatch('[Process] Set Token Expiration Timer');
      });
    },
    '[Process] Set Token Expiration Timer': ({ dispatch, commit, getters }) => {
      const timerId = setTimeout(() => {
        dispatch('[Process] Clear Token Expiration Timer');

        dispatch('RefreshToken');
      }, getters.validTokenDurationMS);

      commit('saveTokenExpirationTimerId', timerId);
    },
    '[Process] Clear Token Expiration Timer': ({ commit, state }) => {
      clearTimeout(state.tokenExpirationTimerId);

      commit('clearTokenExpirationTimerId');
    },
  };
}
