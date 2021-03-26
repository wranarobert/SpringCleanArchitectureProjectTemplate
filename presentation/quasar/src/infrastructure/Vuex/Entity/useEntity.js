import useStatus from 'src/infrastructure/Status/useStatus';

import EntityStateFactory from './Factories/EntityStateFactory';
import EntityMutationsFactory from './Factories/EntityMutationsFactory';
import EntityGettersFactory from './Factories/EntityGettersFactory';
import EntityActionsFactory from './Factories/EntityActionsFactory';

export default function(storeModule) {
  if (!storeModule) throw new Error('Store module is not defined.');
  if (!storeModule.primaryKey) throw new Error('Primary key is missing in the settings.');
  if (!storeModule.getEntitiesAPI) throw new Error('API for GET method is missing.');

  return useStatus({
    namespaced: 'namespaced' in storeModule ? storeModule.namespaced : true,
    state: EntityStateFactory(storeModule),
    mutations: EntityMutationsFactory(storeModule),
    getters: EntityGettersFactory(storeModule),
    actions: EntityActionsFactory(storeModule),
  });
}
