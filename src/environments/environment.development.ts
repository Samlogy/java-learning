export const environmentDev = {
  production: false,
  serverUrl: '/api',
  keycloak: {
    issuer: 'http://localhost:8080/auth/', // Url of the Identity Provider
    realm: 'sam-realm', // Realm
    clientId: 'sam-clientId', // clientId
  },
};
