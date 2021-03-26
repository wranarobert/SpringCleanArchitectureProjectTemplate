import useStatus from 'src/infrastructure/Status/useStatus';
import getters from './getters';
import mutations from './mutations';
import actions from './actions';

export default useStatus({
  getters,
  mutations,
  actions,
});
