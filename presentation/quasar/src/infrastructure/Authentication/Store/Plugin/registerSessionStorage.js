import VuexPersist from 'vuex-persist';

export default function (store, options = {}) {
  const vuexSessionStorage = new VuexPersist({
    key: 'session',
    storage: window.sessionStorage,
    reducer: (state) => ({
      authentication: {
        authenticationTokens: state.authentication.authenticationTokens,
        user: state.authentication.user,
      },
    }),
    ...options,
  });

  vuexSessionStorage.plugin(store);
}
