export default class {
  constructor(settings = {}) {
    this.type = 'dateTimePicker';
    this.controlField = settings.controlField;
    this.qAttrs = settings.qAttrs || null;
  }
}
