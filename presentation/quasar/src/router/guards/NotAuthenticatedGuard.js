import store from 'src/store';

export default function (to, from, next) {
  if (!store.getters['authentication/isAuthenticated']) {
    next();
    return;
  }
  next('/');
}
