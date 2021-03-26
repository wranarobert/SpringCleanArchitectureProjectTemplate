import StatusModel from '../Models/StatusModel';

export default function (storeModule) {
  let additionalState = {};

  if (storeModule.state) {
    additionalState = storeModule.state;
  }

  return {
    status: new StatusModel(),
    ...additionalState,
  };
}
