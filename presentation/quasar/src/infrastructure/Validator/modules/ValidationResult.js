export default class {
  constructor(validationResult) {
    this.valid = validationResult === undefined;
    this.result = validationResult;
  }

  get firstMessage() {
    if (this.valid) {
      return null;
    }

    return this.result[0];
  }

  get resultMessages() {
    if (this.valid) {
      return null;
    }

    return [].concat.apply([], Object.values(this.result));
  }
}
