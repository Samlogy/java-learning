import { KeycloakService } from 'keycloak-angular';
import { environmentDev } from '../environments/environment.development';

export function initializer(keycloak: KeycloakService): () => Promise<any> {
  return (): Promise<any> => {
    return new Promise(async (resolve, reject) => {
      try {
        await keycloak.init({
          config: {
            url: environmentDev.keycloak.issuer,
            realm: environmentDev.keycloak.realm,
            clientId: environmentDev.keycloak.clientId,
          },
          loadUserProfileAtStartUp: true,
          initOptions: {
            onLoad: 'login-required',
            checkLoginIframe: true,
          },
          bearerExcludedUrls: ['/assets'],
        });
        resolve(resolve);
      } catch (err) {
        reject(err);
      }
    });
  };
}

// keycloak
// sam / Pass0.
