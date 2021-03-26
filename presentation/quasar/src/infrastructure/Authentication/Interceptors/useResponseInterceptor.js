import axios from 'axios';
import createAuthRefreshInterceptor from 'axios-auth-refresh';

export default function (store, router) {
  // Replay all unauthorized request after relog
  function replayUnauthorizedRequests(failedRequestError) {
    if (!store.getters['authentication/isReloggable']) {
      return store
        .dispatch('authentication/Logout')
        .then(() => Promise.reject(failedRequestError))
        .finally(() => {
          router.push('login');
        });
    }

    return store.dispatch('authentication/[Boot] Start Relogging Sequence');
  }

  createAuthRefreshInterceptor(axios, replayUnauthorizedRequests, {
    skipWhileRefreshing: false,
  });

  // Navigate back if the request is forbidden
  axios.interceptors.response.use(
    (response) => response,
    (error) => {
      if (!error.response) {
        return Promise.reject(error);
      }

      if (error.response.status === 403) {
        router.go(-1);
      }

      return Promise.reject(error);
    },
  );
}
