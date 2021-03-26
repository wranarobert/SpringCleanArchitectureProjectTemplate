import store from 'src/store';

export default function (to, from, next) {
  if (store.getters['authentication/isAdmin']) {
    next();
    return;
  }
  next('/login');
}
