import registerGlobalAC from './GlobalAC/registerGlobalAC';
import registerRouterAuthGuards from './RouterGuards/registerRouterAuthGuards';

import VGuard from './Components/VGuard';

export default function (Vue, options) {
  const store = options.store.instance;
  const routerOptions = options.router;

  registerGlobalAC(Vue, store);
  registerRouterAuthGuards(store, routerOptions);

  Vue.component('v-guard', VGuard);
}
