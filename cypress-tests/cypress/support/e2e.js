import './commands';

// Dejo la ventana abierta unos segundos al terminar cada spec, así se alcanza a ver el resultado.
// Solo actúa si se pasa holdMs (el runner lo hace cuando se ejecuta con ventana visible).
after(() => {
  const holdMs = Number(Cypress.expose('holdMs') || 0);
  if (holdMs > 0) cy.wait(holdMs, { log: false });
});
