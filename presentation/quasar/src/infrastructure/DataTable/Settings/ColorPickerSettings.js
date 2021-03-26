export default class {
  constructor(settings = {}) {
    this.type = 'colorPicker';
    this.controlField = settings.controlField;
    this.qAttrs = settings.qAttrs || null;
  }
}
