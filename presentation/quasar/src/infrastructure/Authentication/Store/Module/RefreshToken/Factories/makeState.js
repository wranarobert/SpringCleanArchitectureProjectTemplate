export default function (storeModule) {
  let additionalState = {};

  if (storeModule.state) {
    additionalState = storeModule.state;
  }

  return {
    ...additionalState,
    tokenExpirationTimerId: null,
  };
}
