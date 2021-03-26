export default class {
  constructor(settings = {}) {
    this.type = 'input';
    this.controlField = settings.controlField;
    this.qAttrs = settings.qAttrs || null;
  }
}
