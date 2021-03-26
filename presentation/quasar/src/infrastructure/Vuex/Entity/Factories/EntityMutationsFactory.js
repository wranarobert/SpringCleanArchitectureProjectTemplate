import Vue from 'vue';

export default function(storeModuleSettings) {
  const { primaryKey } = storeModuleSettings;

  let additionalMutations = {};

  if (storeModuleSettings.mutations) {
    additionalMutations = storeModuleSettings.mutations;
  }

  return {
    saveEntityList: (state, entityList) => {
      if (Array.isArray(entityList)) {
        state.entityList = entityList.reduce((current, next) => {
          const entityKey = next[primaryKey];

          return {
            ...current,
            [entityKey]: next,
          };
        }, {});
        return;
      }

      state.entityList = entityList;
    },
    addEntity: (state, entity) => {
      const entityKey = entity[primaryKey];
      if (state.entityList[entityKey]) return;

      Vue.set(state.entityList, entityKey, entity);
    },
    editEntity: (state, entity) => {
      const entityKey = entity[primaryKey];
      if (!state.entityList[entityKey]) return;

      state.entityList[entityKey] = entity;
    },
    deleteEntity: (state, entityKey) => {
      if (!state.entityList[entityKey]) return;

      Vue.delete(state.entityList, entityKey);
    },
    ...additionalMutations,
  };
}
