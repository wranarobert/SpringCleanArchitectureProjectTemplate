import Vue from 'vue';
import AuthenticationService from 'src/core/Authentication/AuthenticationService';

export default {
  Login({ getters, commit }, loginData) {
    commit('statusLoading');
    Vue.prototype.$q.loading.show({
      message: 'Authenticating...',
    });
    return AuthenticationService.token(loginData)
      .then(authTokens => {
        commit('saveAuthenticationTokens', authTokens);
      })
      .then(() =>
        Vue.prototype.$q.loading.show({
          message: 'Fetching user information...',
        }),
      )
      .then(() => AuthenticationService.authenticationInfo(getters.accessTokenHeader))
      .then(authUser => {
        commit('saveAuthenticatedUser', authUser);
        commit('statusSuccess');
      })
      .catch(error => {
        commit('clearAuthentication');
        commit('statusError', error);
        throw error;
      })
      .finally(() => {
        Vue.prototype.$q.loading.hide();
      });
  },
  Logout({ commit, getters }) {
    return AuthenticationService.logout(getters.refreshToken).finally(() => {
      commit('clearAuthentication');
    });
  },
  RefreshToken({ commit, getters }) {
    return AuthenticationService.refreshToken(getters.refreshToken).then(authenticationTokens => {
      commit('saveRefreshedToken', authenticationTokens);
    });
  },
};
