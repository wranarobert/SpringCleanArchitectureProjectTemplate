import isPermitted from '../Common/isPermitted';

export default function (store, routerOptions) {
  routerOptions.instance.beforeEach((to, from, next) => {
    const guard = to.meta && to.meta.guard;

    if (!guard) {
      return next();
    }

    if (!store.getters['authentication/permissions']) {
      return next(routerOptions.nextOnError);
    }

    const usersPermissions = store.getters['authentication/permissions'];
    const { permissions, all = true } = guard;

    if (!isPermitted(usersPermissions, permissions, all)) {
      return next(routerOptions.nextOnError);
    }
    return next();
  });
}
