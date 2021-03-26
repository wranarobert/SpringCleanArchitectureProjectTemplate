export default class {
  constructor(settings = {}) {
    this.type = 'checkbox';
    this.controlField = settings.controlField;
    this.qAttrs = settings.qAttrs || null;
  }
}
