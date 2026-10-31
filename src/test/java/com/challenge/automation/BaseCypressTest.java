package com.challenge.automation;

import org.junit.jupiter.api.extension.ExtendWith;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ScreenshotExtension.class)
public abstract class BaseCypressTest {
    /** Spec Cypress (relativo a cypress-tests/) que valida esta clase. */
    protected abstract String spec();

    protected void ejecutarSpec() throws Exception {
        // Borro capturas viejas de este spec para que el reporte solo muestre las de esta corrida
        CypressRunner.screenshotsFor(spec()).forEach(f -> f.delete());
        // Lanzo Cypress con el spec y espero a que termine
        int exit = CypressRunner.run(spec());
        // Código 0 = todo pasó; cualquier otro valor significa que algún caso falló
        assertEquals(0, exit, "Cypress reportó " + exit + " prueba(s) fallida(s) en " + spec());
    }
}
