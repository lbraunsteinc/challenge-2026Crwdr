// Comandos propios que se reutilizan en varios specs.

// Inicia sesión una sola vez y guarda la sesión; los siguientes tests la reutilizan.
Cypress.Commands.add('login', (username, password = 'secret_sauce') => {
  cy.session([username, password], () => {
    // Abro la página de login
    cy.visit('/');
    // Escribo usuario y contraseña (la clave no se muestra en el log)
    cy.get('[data-test="username"]').type(username);
    cy.get('[data-test="password"]').type(password, { log: false });
    cy.get('[data-test="login-button"]').click();
    // Compruebo que realmente entró antes de guardar la sesión
    cy.url().should('include', '/inventory.html');
  });
});
