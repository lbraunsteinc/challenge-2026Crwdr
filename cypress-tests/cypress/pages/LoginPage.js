class LoginPage {
  elements = {
    username: () => cy.get('[data-test="username"]'),
    password: () => cy.get('[data-test="password"]'),
    loginButton: () => cy.get('[data-test="login-button"]'),
    error: () => cy.get('[data-test="error"]'),
  };

  visit() {
    cy.visit('/');
    return this;
  }

  login(username, password) {
    if (username) this.elements.username().type(username);
    if (password) this.elements.password().type(password, { log: false });
    this.elements.loginButton().click();
    return this;
  }
}

module.exports = new LoginPage();
