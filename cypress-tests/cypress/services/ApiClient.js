// Capa mínima para interactuar con servicios web (REST) desde el framework.
class ApiClient {
  get(path, options = {}) {
    return cy.request({
      method: 'GET',
      url: `${Cypress.expose('apiBaseUrl')}${path}`,
      headers: { Accept: 'application/json', 'User-Agent': 'Mozilla/5.0' },
      ...options,
    });
  }
}

module.exports = new ApiClient();
