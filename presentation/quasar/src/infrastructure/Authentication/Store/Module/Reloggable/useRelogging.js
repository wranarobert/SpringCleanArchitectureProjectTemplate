import makeActions from './Factories/makeActions';
import makeGetters from './Factories/makeGetters';

export default function (storeModule, settings = {}) {
  if (!storeModule) throw new Error('Store module is not defined.');

  if (!settings.dialogComponent) throw new Error('Provide dialogComponent');

  return {
    namespaced: 'namespaced' in storeModule ? storeModule.namespaced : true,
    state: storeModule.state,
    mutations: storeModule.mutations,
    getters: makeGetters(storeModule),
    actions: makeActions(storeModule, settings),
  };
}
