export default class {
  constructor(data = {}) {
    this.type = data.type || 'Idle';
    this.info = data.info || null;
  }
}
