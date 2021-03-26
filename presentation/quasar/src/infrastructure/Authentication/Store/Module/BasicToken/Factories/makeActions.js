export default function (storeModule) {
  let additionalActions = {};

  if (storeModule.actions) {
    additionalActions = storeModule.actions;
  }

  if (!('Login' in additionalActions)) {
    throw new Error('Provide "Login" action that returns Promise');
  }

  if (!('Logout' in additionalActions)) {
    throw new Error('Provide "Logout" action that returns Promise');
  }

  return {
    ...additionalActions,
  };
}
