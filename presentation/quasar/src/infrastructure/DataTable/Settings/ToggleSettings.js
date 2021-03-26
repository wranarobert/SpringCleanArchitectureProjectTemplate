export default class {
  constructor(settings = {}) {
    this.type = 'toggle';
    this.controlField = settings.controlField;
    this.qAttrs = settings.qAttrs || null;
  }
}
