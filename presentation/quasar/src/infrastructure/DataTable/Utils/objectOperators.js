export function removeObjectKey(key, object) {
  const newObject = { ...object };

  delete newObject[key];

  return newObject;
}

export function patchObjectValue(object, key, value) {
  return {
    ...object,
    [key]: value,
  };
}

export function hasObjectKey(object, key) {
  return Object.keys(object).includes(key);
}
