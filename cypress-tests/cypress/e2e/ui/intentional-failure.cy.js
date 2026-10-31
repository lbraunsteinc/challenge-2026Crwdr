const inventoryPage = require('../../pages/InventoryPage');

// Punto 4 del challenge: este caso falla a propósito para poder ver
// cómo queda el reporte y la captura de pantalla cuando algo sale mal.
describe('Falla intencional (demostración de captura de pantalla)', () => {
  it('TC-FAIL-01: [FALLA INTENCIONAL] espera 2 productos tras agregar solo 1', () => {
    // Entro con el usuario estándar y abro el listado
    cy.login('standard_user');
    inventoryPage.visit();
    // Agrego un solo producto
    inventoryPage.addProduct('sauce-labs-backpack');
    // Aquí está el error buscado: pido que el contador diga 2 cuando solo hay 1
    inventoryPage.elements.cartBadge().should('have.text', '2');
  });
});
