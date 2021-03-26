export default class {
  constructor(data = {}) {
    this.id = data.id || '';
    this.username = data.username || '';
    this.password = data.password || '';
    this.roleIds = data.roleIds || [];
  }
}
