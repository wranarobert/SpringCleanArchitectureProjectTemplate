export default function(store) {
  // Refresh token on refresh
  if (!store.getters['authentication/refreshToken']) {
    return Promise.resolve();
  }

  return store.dispatch('authentication/RefreshToken').catch(() => Promise.resolve());
}
