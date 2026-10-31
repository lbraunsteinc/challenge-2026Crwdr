package com.challenge.automation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MercadoLibreApiTest extends BaseCypressTest {
    @Override
    protected String spec() { return "cypress/e2e/api/mercadolibre.cy.js"; }

    @Test
    @DisplayName("API Mercado Libre: departamentos (TC-API-01)")
    // Ejecuta el spec de Cypress asociado y deja que JUnit registre el resultado
    void departamentos() throws Exception { ejecutarSpec(); }
}
