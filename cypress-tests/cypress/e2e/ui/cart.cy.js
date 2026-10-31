const inventoryPage = require('../../pages/InventoryPage');

// Productos que uso en los casos (el slug es el que aparece en el data-test del botón)
const BACKPACK = 'sauce-labs-backpack';
const BIKE_LIGHT = 'sauce-labs-bike-light';

describe('Agregado de productos al carrito', () => {
  beforeEach(() => {
    // Inicio sesión reutilizando la sesión guardada para no repetir el login en cada caso
    cy.login('standard_user');
    // Y voy directo al listado de productos
    inventoryPage.visit();
  });

  it('TC-CART-01: agregar un producto actualiza el contador', () => {
    // Agrego la mochila al carrito
    inventoryPage.addProduct(BACKPACK);
    // El globito del carrito debe marcar 1
    inventoryPage.elements.cartBadge().should('have.text', '1');
    // Y el botón de ese producto cambia a "Remove"
    inventoryPage.elements.removeButton(BACKPACK).should('be.visible');
  });

  it('TC-CART-02: agregar varios productos se refleja en el carrito', () => {
    // Agrego dos productos distintos
    inventoryPage.addProduct(BACKPACK).addProduct(BIKE_LIGHT);
    // El contador debe marcar 2
    inventoryPage.elements.cartBadge().should('have.text', '2');
    // Abro el carrito para ver el detalle
    inventoryPage.openCart();
    cy.url().should('include', '/cart.html');
    // Tienen que aparecer los dos productos que agregué
    inventoryPage.elements.cartItems().should('have.length', 2);
  });

  it('TC-CART-03: quitar un producto vacía el contador', () => {
    // Agrego la mochila y enseguida la quito
    inventoryPage.addProduct(BACKPACK).removeProduct(BACKPACK);
    // Sin productos, el globito del carrito desaparece
    inventoryPage.elements.cartBadge().should('not.exist');
    // Y el botón vuelve a ofrecer "Add to cart"
    inventoryPage.elements.addButton(BACKPACK).should('be.visible');
  });
});
