import Vue from 'vue';

export default function (storeModule, settings) {
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
    '[Boot] Start Relogging Sequence': ({ dispatch }) => new Promise((resolve) => {
      dispatch('Logout')
        .then(() => {
          Vue.prototype.$q
            .dialog({
              component: settings.dialogComponent,
            })
            .onOk(({ loginData, hide }) => {
              dispatch('Login', loginData)
                .then(() => {
                  hide();
                  resolve();
                })
                .catch((error) => error);
            });
        })
        .catch((error) => error);
    }),
  };
}
