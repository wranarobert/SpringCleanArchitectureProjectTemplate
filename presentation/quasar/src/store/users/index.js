import useStatus from 'src/infrastructure/Status/useStatus';
import state from './state';
import getters from './getters';
import mutations from './mutations';
import actions from './actions';

export default useStatus({
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
});
