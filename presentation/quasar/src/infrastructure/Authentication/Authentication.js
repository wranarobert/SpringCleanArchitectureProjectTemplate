import registerStoreModule from './Store/registerStoreModule';
import registerAccessControl from './AccessControl/registerAccessControl';

import useRequestInterceptor from './Interceptors/useRequestInterceptor';
import useResponseInterceptor from './Interceptors/useResponseInterceptor';

export default {
  install(Vue, options) {
    if (!options.store && !options.store.instance) throw new Error('Provide store instance');
    if (!options.router && !options.router.instance) throw new Error('Provide router instance');

    registerStoreModule(options);

    registerAccessControl(Vue, options);

    useRequestInterceptor(options.store.instance);
    useResponseInterceptor(options.store.instance, options.router.instance);
  },
};
