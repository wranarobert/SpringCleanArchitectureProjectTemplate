export default function (storeModuleSettings) {
  let additionalGetters = {};

  if (storeModuleSettings.getters) {
    additionalGetters = storeModuleSettings.getters;
  }

  return {
    getList: (state) => Object.values(state.entityList),
    getEntityByKey: (state) => (primaryKey) => state.entityList[primaryKey],
    ...additionalGetters,
  };
}
