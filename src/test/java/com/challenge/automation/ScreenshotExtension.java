package com.challenge.automation;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/** Ante un fallo copia las capturas de Cypress a target/screenshots y deja la ruta en el XML (system-out). */
public class ScreenshotExtension implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object instance = context.getRequiredTestInstance();
        if (!(instance instanceof BaseCypressTest base)) return;
        try {
            // Carpeta final donde dejo las capturas del fallo
            Path out = Paths.get("target/screenshots").toAbsolutePath();
            Files.createDirectories(out);
            // Tomo las capturas que Cypress guardó cuando falló el caso
            List<File> shots = CypressRunner.screenshotsFor(base.spec());
            for (File shot : shots) {
                // Las copio con un nombre limpio y dejo la ruta anotada en el XML
                Path dest = out.resolve(shot.getName().replaceAll("[^A-Za-z0-9._()-]", "_"));
                Files.copy(shot.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("[SCREENSHOT] " + dest);
            }
        } catch (Exception e) {
            System.out.println("[SCREENSHOT] No se pudo copiar la captura: " + e.getMessage());
        }
    }
}
