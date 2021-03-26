import makeState from './Factories/makeState';
import makeMutations from './Factories/makeMutations';
import makeGetters from './Factories/makeGetters';
import makeActions from './Factories/makeActions';

export default function (storeModule) {
  if (!storeModule) throw new Error('Store module is not defined.');

  return {
    namespaced: 'namespaced' in storeModule ? storeModule.namespaced : true,
    state: makeState(storeModule),
    mutations: makeMutations(storeModule),
    getters: makeGetters(storeModule),
    actions: makeActions(storeModule),
  };
}
