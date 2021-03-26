import axios from 'axios';

export default function(storeModuleSettings) {
  const { primaryKey } = storeModuleSettings;

  let additionalActions = {};

  if (storeModuleSettings.actions) {
    additionalActions = storeModuleSettings.actions;
  }

  return {
    '[API] Get Entities': ({ commit }) => {
      commit('statusLoading');
      return axios
        .get(storeModuleSettings.getEntitiesAPI, {
          params: {
            groupBy: primaryKey,
          },
        })
        .then(({ data }) => data)
        .then(entityList => {
          commit('saveEntityList', entityList);
          commit('statusSuccess');
          return entityList;
        })
        .catch(error => {
          commit('statusError', error);
          throw error;
        });
    },
    '[API] Add Entity': ({ commit }, entityData) => {
      if (!storeModuleSettings.addEntityAPI) throw new Error('API for POST method is missing.');

      commit('statusLoading');
      return axios
        .post(storeModuleSettings.addEntityAPI, entityData)
        .then(({ data }) => data)
        .then(entityResponse => {
          commit('addEntity', entityResponse);
          commit('statusSuccess', entityResponse);

          return entityResponse;
        })
        .catch(error => {
          commit('statusError', error);
          throw error;
        });
    },
    '[API] Edit Entity': ({ commit }, entityData) => {
      if (!storeModuleSettings.editEntityAPI) throw new Error('API for PUT method is missing.');

      commit('statusLoading');
      return axios
        .put(`${storeModuleSettings.editEntityAPI}/${entityData[primaryKey]}`, entityData)
        .then(({ data }) => data)
        .then(entityResponse => {
          commit('editEntity', entityResponse);
          commit('statusSuccess', entityResponse);

          return entityResponse;
        })
        .catch(error => {
          commit('statusError', error);
          throw error;
        });
    },
    '[API] Delete Entity': ({ commit }, key) => {
      if (!storeModuleSettings.deleteEntityAPI)
        throw new Error('API for DELETE method is missing.');

      commit('statusLoading');
      return axios
        .delete(`${storeModuleSettings.deleteEntityAPI}/${key}`)
        .then(({ data }) => data)
        .then(entityResponse => {
          commit('deleteEntity', key);
          commit('statusSuccess', entityResponse);

          return entityResponse;
        })
        .catch(error => {
          commit('statusError', error);
          throw error;
        });
    },
    ...additionalActions,
  };
}
