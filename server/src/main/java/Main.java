import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;

import static spark.Spark.*;
import static spark.Spark.get;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        BlockingQueue<Runnable> staticBlockingQueue = new LinkedBlockingQueue<>();
        BlockingThreadPoolExecutor staticExecutor = new BlockingThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, staticBlockingQueue);
        staticExecutor.prestartAllCoreThreads();

        configSpark();

        // Routes

        get("/StaticWindow", "application/json", (req, res) -> {
            try {
                String clientId = req.queryParams("clientId");
                Long currentTimestamp = System.currentTimeMillis() / 1000;
                logger.info("Static Window - Client ID: '"  + clientId +  "' - Received static window request");

                staticExecutor.execute(new StaticWindowTask(clientId, currentTimestamp));

                return "Client ID: '" + clientId + "' Successfully served static window request";

            } catch(RejectedExecutionException e) {
                String errorMessage = e.getMessage();
                logger.error(errorMessage);
                res.status(503);
                return errorMessage;
            }
        });

        get("/DynamicWindow", (req, res) -> {
            String clientId = req.queryParams("clientId");
            logger.info("Dynamic Window - Client ID: '"  + clientId +  "' - Received static window request");

            return "Client ID: '" + clientId + "' Successfully served dynamic window request";
        });

        get("/exit", "application/json", (req, res) -> {
            logger.info("Received exit request");

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