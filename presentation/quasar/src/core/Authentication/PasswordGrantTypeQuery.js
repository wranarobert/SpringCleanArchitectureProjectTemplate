export default class {
  constructor(username, password) {
    this.username = username;
    this.password = password;
    this.grant_type = 'password';
    this.scope = 'all';
  }
}
