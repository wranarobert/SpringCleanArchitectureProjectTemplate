import axios from 'axios';

export default function (store) {
  // Add token to every request if authenticated
  axios.interceptors.request.use((config) => {
    if (store.getters['authentication/isAuthenticated']) {
      config.headers.Authorization = store.getters['authentication/accessTokenHeader'];
    }

    return config;
  });
}
