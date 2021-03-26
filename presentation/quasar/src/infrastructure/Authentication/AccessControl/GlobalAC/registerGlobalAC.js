import isPermitted from '../Common/isPermitted';

export default function (Vue, store) {
  Vue.prototype.$getPermissions = function () {
    return store.getters['authentication/permissions'];
  };

  Vue.prototype.$permitAll = function (permissions) {
    return isPermitted(store.getters['authentication/permissions'], permissions, true);
  };

  Vue.prototype.$permitAny = function (permissions) {
    return isPermitted(store.getters['authentication/permissions'], permissions, false);
  };
}
