package com.challenge.automation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseCypressTest {
    @Override
    protected String spec() { return "cypress/e2e/ui/login.cy.js"; }

    @Test
    @DisplayName("Inicio de sesion: 5 casos (TC-LOGIN-01..05)")
    // Ejecuta el spec de Cypress asociado y deja que JUnit registre el resultado
    void inicioDeSesion() throws Exception { ejecutarSpec(); }
}
