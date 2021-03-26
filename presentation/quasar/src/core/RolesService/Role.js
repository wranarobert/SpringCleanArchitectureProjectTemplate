export default class {
  constructor(roleData = {}) {
    this.id = roleData.id || '';
    this.name = roleData.name || '';
    this.description = roleData.description || '';
  }
}
