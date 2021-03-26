export default class {
  constructor(model = {}) {
    this.username = model.username || '';
    this.password = model.password || '';
  }
}
