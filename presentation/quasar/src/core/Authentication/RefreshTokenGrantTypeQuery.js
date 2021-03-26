export default class {
  constructor(refreshToken) {
    this.grant_type = 'refresh_token';
    this.refresh_token = refreshToken;
  }
}
