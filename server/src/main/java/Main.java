import java.time.LocalDateTime;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

import static spark.Spark.*;
import static spark.Spark.get;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        BlockingQueue<Runnable> staticBlockingQueue = new LinkedBlockingQueue<>();
        BlockingThreadPoolExecutor staticExecutor = new BlockingThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, staticBlockingQueue);
        staticExecutor.prestartAllCoreThreads();

        configSpark();

        // Routes

        get("/StaticWindow", "application/json", (req, res) -> {
            try {
                String timestamp = timestampFormat.format(LocalDateTime.now());
                String clientId = req.queryParams("clientId");
                Long currentTimestamp = System.currentTimeMillis() / 1000;
                System.out.println(timestamp + " - Static Window - Client ID: '"  + clientId +  "' - Received static window request");

                staticExecutor.execute(new StaticWindowTask(clientId, currentTimestamp));

                return "Client ID: '" + clientId + "' Successfully served static window request";

            } catch(RejectedExecutionException e) {
                String errorMessage = e.getMessage();
                System.out.println(errorMessage);
                res.status(503);
                return errorMessage;
            }
        });

        get("/DynamicWindow", (req, res) -> {
            System.out.println(new Date() + " - Received dynamic window request");
            String clientId = req.queryParams("clientId");

            return "Client ID: '" + clientId + "' Successfully served dynamic window request";
        });

        get("/exit", "application/json", (req, res) -> {
            staticExecutor.shutdown();
            try {
                boolean result = staticExecutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
                System.out.println("Termination result = " + result);
            } catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }

            return "Successfully served exit request";
            // System.exit(0);
        });
    }

    private static void configSpark() {
        port(8080);

        after((request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://localhost:3000");
            response.header("Access-Control-Allow-Methods", "GET");
        });
    }
}