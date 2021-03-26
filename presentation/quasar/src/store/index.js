import Vue from 'vue';
import Vuex from 'vuex';

import ErrorDialogMiddleware from 'src/infrastructure/Status/Middlewares/ErrorDialogMiddleware';

import users from './users';
import roles from './roles';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    users,
    roles,
  },
  plugins: [ErrorDialogMiddleware],
  // enable strict mode (adds overhead!)
  // for dev mode only
  strict: process.env.DEV,
});
