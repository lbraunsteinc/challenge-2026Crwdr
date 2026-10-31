package com.challenge.automation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CartTest extends BaseCypressTest {
    @Override
    protected String spec() { return "cypress/e2e/ui/cart.cy.js"; }

    @Test
    @DisplayName("Carrito: 3 casos (TC-CART-01..03)")
    // Ejecuta el spec de Cypress asociado y deja que JUnit registre el resultado
    void agregadoAlCarrito() throws Exception { ejecutarSpec(); }
}
