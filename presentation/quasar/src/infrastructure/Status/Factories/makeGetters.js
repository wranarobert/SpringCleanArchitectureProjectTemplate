export default function (storeModule) {
  let additionalGetters = {};

  if (storeModule.getters) {
    additionalGetters = storeModule.getters;
  }

  return {
    isStatusLoading: (state) => state.status.type === 'Loading',
    ...additionalGetters,
  };
}
