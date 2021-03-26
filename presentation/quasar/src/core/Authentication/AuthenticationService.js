import axios from 'axios';

const authAxios = axios.create();

import PasswordGrantTypeQuery from './PasswordGrantTypeQuery';
import RefreshTokenGrantTypeQuery from './RefreshTokenGrantTypeQuery';

const authBasic = Object.freeze({
  username: 'quasar-app',
  password: 'secret1',
});

const apiPrefix = 'oauth';

export function token(loginData) {
  return authAxios
    .post(`${apiPrefix}/token`, null, {
      auth: authBasic,
      params: new PasswordGrantTypeQuery(loginData.username, loginData.password),
    })
    .then(({ data }) => data);
}

export function authenticationInfo(accessTokenHeader) {
  return authAxios
    .get('authentication', {
      headers: {
        Authorization: accessTokenHeader,
      },
    })
    .then(({ data }) => data);
}

export function refreshToken(refreshToken) {
  return axios
    .post(`${apiPrefix}/token`, null, {
      auth: authBasic,
      params: new RefreshTokenGrantTypeQuery(refreshToken),
    })
    .then(({ data }) => data);
}

export function logout() {
  return Promise.resolve(); // return authAxios.post(`${apiPrefix}/logout`, refreshToken);
}

export default {
  token,
  authenticationInfo,
  refreshToken,
  logout,
};
