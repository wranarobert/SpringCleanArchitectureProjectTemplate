import useBasicToken from './BasicToken/useBasicToken';
import useRefreshToken from './RefreshToken/useRefreshToken';
import useRelogging from './Reloggable/useRelogging';

export default function (options) {
  if (!options.store.authModule) throw new Error('Provide additional store module');

  let authModule = useBasicToken(options.store.authModule, {
    tokenPrefix: options.tokenPrefix ? options.tokenPrefix : 'Bearer',
  });

  if (options.type === 'refresh') {
    authModule = useRefreshToken(authModule);
  }

  if (options.reloginDialog) {
    authModule = useRelogging(authModule, {
      dialogComponent: options.reloginDialog,
    });
  }

  authModule.namespaced = true;

  return authModule;
}
