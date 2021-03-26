export default class {
  constructor(settings = {}) {
    this.type = 'select';
    this.controlField = settings.controlField;
    this.options = settings.options;
    this.optionValue = settings.optionValue;
    this.optionLabel = settings.optionLabel;
    this.qAttrs = settings.qAttrs || null;
  }
}
