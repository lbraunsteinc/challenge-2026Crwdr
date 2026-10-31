class InventoryPage {
  elements = {
    cartBadge: () => cy.get('[data-test="shopping-cart-badge"]'),
    cartLink: () => cy.get('[data-test="shopping-cart-link"]'),
    addButton: (slug) => cy.get(`[data-test="add-to-cart-${slug}"]`),
    removeButton: (slug) => cy.get(`[data-test="remove-${slug}"]`),
    cartItems: () => cy.get('[data-test="inventory-item"]'),
  };

  visit() {
    // El host responde 404 en deep links, pero la SPA carga con normalidad.
    cy.visit('/inventory.html', { failOnStatusCode: false });
    return this;
  }

  addProduct(slug) {
    this.elements.addButton(slug).click();
    return this;
  }

  removeProduct(slug) {
    this.elements.removeButton(slug).click();
    return this;
  }

  openCart() {
    this.elements.cartLink().click();
    return this;
  }
}

module.exports = new InventoryPage();
