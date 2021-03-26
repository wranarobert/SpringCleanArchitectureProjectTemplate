import makeAuthStoreModule from './Module/makeAuthStoreModule';
import registerSessionStorage from './Plugin/registerSessionStorage';

export default function (options) {
  const store = options.store.instance;

  store.registerModule('authentication', makeAuthStoreModule(options));

  registerSessionStorage(store, options.sessionPersistence);
}
