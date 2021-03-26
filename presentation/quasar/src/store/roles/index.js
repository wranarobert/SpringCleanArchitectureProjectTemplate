import useEntity from 'src/infrastructure/Vuex/Entity/useEntity';
import state from './state';
import getters from './getters';
import mutations from './mutations';
import actions from './actions';

export default useEntity({
  primaryKey: 'id',
  getEntitiesAPI: 'api/roles',
  state,
  getters,
  mutations,
  actions,
});
