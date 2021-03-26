export default class {
  constructor(data = {}) {
    this.title = data.title;
    this.message = data.message ? data.message : data.statusText;
  }
}
