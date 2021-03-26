import StatusModel from '../Models/StatusModel';

export default function (storeModule) {
  let additionalMutations = {};

  if (storeModule.mutations) {
    additionalMutations = storeModule.mutations;
  }

  return {
    statusIdle: (state) => {
      state.status = new StatusModel({ type: 'Idle' });
    },
    statusLoading: (state) => {
      state.status = new StatusModel({ type: 'Loading' });
    },
    statusSuccess: (state, info = null) => {
      state.status = new StatusModel({ type: 'Success', info });
    },
    statusError: (state, error) => {
      state.status = new StatusModel({
        type: 'Error',
        info: error && error.response && error.response.data,
      });
    },
    ...additionalMutations,
  };
}
