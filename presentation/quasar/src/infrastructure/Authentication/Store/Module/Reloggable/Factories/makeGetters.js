export default function (storeModule) {
  let additionalGetters = {};

  if (storeModule.getters) {
    additionalGetters = storeModule.getters;
  }

  return {
    isReloggable: () => true,
    ...additionalGetters,
  };
}
