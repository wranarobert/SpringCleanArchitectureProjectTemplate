export default function (storeModuleSettings) {
  let additionalState = {};

  if (storeModuleSettings.state) {
    additionalState = storeModuleSettings.state;
  }

  return {
    entityList: {},
    ...additionalState,
  };
}
