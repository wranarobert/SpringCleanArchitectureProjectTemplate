import Authentication from 'src/infrastructure/Authentication/Authentication';
import authentication from 'src/store/authentication';
import useRefreshTokenInterceptor from 'src/infrastructure/Authentication/Interceptors/useRefreshTokenInterceptor';
import ReloginDialogComponent from 'components/authentication/ReloginDialogComponent';

export default ({ Vue, router, store }) => {
  Vue.prototype.$q.loading.show({
    message: 'Authenticating...',
  });

  Vue.use(Authentication, {
    type: 'refresh',
    store: {
      instance: store,
      authModule: authentication,
    },
    router: {
      instance: router,
      nextOnError: false,
    },
    /*
    sessionPersistence: {
      // vuex-persist options
    },
    */
    reloginDialog: ReloginDialogComponent,
  });

  return useRefreshTokenInterceptor(store).finally(() => {
    Vue.prototype.$q.loading.hide();
  });
};
