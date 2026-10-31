package com.challenge.automation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntentionalFailureTest extends BaseCypressTest {
    @Override
    protected String spec() { return "cypress/e2e/ui/intentional-failure.cy.js"; }

    @Test
    @DisplayName("[FALLA INTENCIONAL] TC-FAIL-01")
    // Ejecuta el spec de Cypress asociado y deja que JUnit registre el resultado
    void fallaIntencional() throws Exception { ejecutarSpec(); }
}
