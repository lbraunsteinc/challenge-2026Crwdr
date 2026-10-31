package com.challenge.automation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/** Ejecuta un spec de Cypress mediante su CLI y devuelve el código de salida. */
public final class CypressRunner {
    static final Path CYPRESS_DIR = Paths.get("cypress-tests").toAbsolutePath();
    static final Path SCREENSHOTS = CYPRESS_DIR.resolve("cypress/screenshots");

    private CypressRunner() {}

    public static int run(String spec) throws IOException, InterruptedException {
        String browser = System.getProperty("browser", "chrome");
        boolean headed = Boolean.parseBoolean(System.getProperty("headed", "false"));
        String safeName = spec.replaceAll("[^A-Za-z0-9]", "_");
        Path junit = Paths.get("target/cypress-junit").toAbsolutePath().resolve(safeName + ".xml");
        Files.createDirectories(junit.getParent());

        List<String> cmd = new ArrayList<>(List.of("npx", "cypress", "run",
                "--browser", browser, "--spec", spec,
                "--reporter", "junit",
                "--reporter-options", "mochaFile=" + junit + ",toConsole=false"));
        if (headed) {
            cmd.add("--headed");
            // Tiempo (ms) que cada ventana permanece abierta al terminar su spec.
            cmd.addAll(List.of("--expose", "holdMs=" + System.getProperty("holdMs", "4000")));
        }

        Process p = new ProcessBuilder(cmd)
                .directory(CYPRESS_DIR.toFile())
                .redirectErrorStream(true)
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .start();
        return p.waitFor();
    }

    /** Capturas generadas por Cypress para un spec (carpeta cypress/screenshots/<spec>/). */
    public static List<File> screenshotsFor(String spec) throws IOException {
        Path dir = SCREENSHOTS.resolve(Paths.get(spec).getFileName());
        if (!Files.isDirectory(dir)) return List.of();
        try (Stream<Path> s = Files.walk(dir)) {
            return s.filter(f -> f.toString().endsWith(".png")).sorted(Comparator.naturalOrder())
                    .map(Path::toFile).toList();
        }
    }
}
