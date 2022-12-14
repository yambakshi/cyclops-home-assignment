import java.util.Date;
import java.util.concurrent.*;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        // Get ExecutorService from Executors utility class, thread pool size is 10
        // ExecutorService executor = Executors.newFixedThreadPool(5);
        ThreadPoolExecutor executor
                = new ThreadPoolExecutor(50, 50, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        executor.allowCoreThreadTimeOut(true);
        StaticWindowHandler staticWindowHandler = new StaticWindowHandler();
        DynamicWindowHandler dynamicWindowHandler = new DynamicWindowHandler();

        port(8080);

        after((request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://localhost:3000");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        get("/StaticWindow", "application/json", (req, res) -> {
            System.out.println(new Date() + " - Received static window request");
            String clientId = req.queryParams("clientId");
            Long currentTimestamp = System.currentTimeMillis() / 1000;
            boolean shouldExecute = staticWindowHandler.verifyClientRequests(clientId, currentTimestamp);
            if (shouldExecute) {
                executor.submit(staticWindowHandler);
            } else {
                res.status(503);
                return "Service Unavailable";
            }

            return "Successful static window request " + clientId;
        });

        get("/DynamicWindow", (req, res) -> {
            System.out.println(new Date() + " - Received dynamic window request");
            String clientId = req.queryParams("clientId");
            Long currentTimestamp = System.currentTimeMillis() / 1000;
            boolean shouldExecute = dynamicWindowHandler.verifyClientRequests(clientId, currentTimestamp);
            if (shouldExecute) {
                executor.submit(dynamicWindowHandler);
            } else {
                res.status(503);
                return "Service Unavailable";
            }

            return "Successful dynamic window request " + clientId;
        });
    }
}