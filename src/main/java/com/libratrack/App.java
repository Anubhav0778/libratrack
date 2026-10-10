package com.libratrack;

import com.libratrack.service.LibraryService;
import com.libratrack.service.SimClock;
import com.libratrack.ui.ConsoleUI;
import com.libratrack.web.WebServer;

import java.io.IOException;

/**
 * Entry point.
 *
 * <pre>
 *   java -jar libratrack-1.0.0.jar            -> web UI on http://localhost:8080
 *   java -jar libratrack-1.0.0.jar web 9090   -> web UI on the given port
 *   java -jar libratrack-1.0.0.jar console    -> console UI
 * </pre>
 */
public final class App {
    private App() {
    }

    public static void main(String[] args) throws IOException {
        LibraryService library = new LibraryService(new SimClock());
        library.seed();

        String mode = args.length > 0 ? args[0] : "web";
        if ("console".equalsIgnoreCase(mode)) {
            new ConsoleUI(library, System.in, System.out).run();
            return;
        }

        int port = args.length > 1 ? Integer.parseInt(args[1]) : 8080;
        WebServer server = new WebServer(library, port);
        server.start();
        System.out.println("LibraTrack web UI running at http://localhost:" + server.port());
    }
}
