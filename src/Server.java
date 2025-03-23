import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.InetSocketAddress;
import java.net.URI;

public class Server {
    private HttpServer server;
    private int port;   // Port on which the server will listen

    public Server(int port){
        this.port = port;

        // Create the HTTP server on the specified port.
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            // Define the routes
            server.createContext("/", new DefaultRoute()); // Serves index.html
            server.createContext("/static/", new StaticFileHandler()); // Serves static files like JS
        } catch (IOException e) {
            throw new RuntimeException("Failed to start HTTP server on port " + port, e);
        }
    }

    // Main route where the index.html is served
    static class DefaultRoute implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            byte[] res = Files.readAllBytes(Paths.get("resources/index.html"));
            exchange.sendResponseHeaders(200, res.length);  // Send 200 OK status
            OutputStream os = exchange.getResponseBody();
            os.write(res);
            os.close();
        }
    }

    // Handler to serve static files like JS and CSS
    static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Determine the requested file path
            String path = exchange.getRequestURI().getPath();
            String filePath = "resources" + path.substring("/static".length());

            // Get the file content
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));

            // Set the appropriate content type for JS files
            if (path.endsWith(".js")) {
                exchange.getResponseHeaders().add("Content-Type", "application/javascript");
            } else if (path.endsWith(".css")) {
                exchange.getResponseHeaders().add("Content-Type", "text/css");
            }

            exchange.sendResponseHeaders(200, fileContent.length);  // Send 200 OK status
            OutputStream os = exchange.getResponseBody();
            os.write(fileContent);
            os.close();
        }
    }

    // Starts the server and opens the default URL in a browser
    public void run() {
        server.setExecutor(null);
        server.start();

        System.out.println("Server is running on port " + this.port);
        openURL("http://localhost:" + this.port + "/");
    }

    private void openURL(String url) {
        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI(url);
            desktop.browse(uri);
        } catch (Exception e) {
            System.err.println("Failed to open URL: " + url + " - " + e.getClass().getSimpleName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8000); // Initialize server on port 8000.
        server.run(); // Start the server.
    }
}
