import java.time.LocalDateTime;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
//        ThreadPoolExecutor executor
//                = new ThreadPoolExecutor(50, 50, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
//        executor.allowCoreThreadTimeOut(true);

        BlockingQueue<Runnable> staticBlockingQueue = new ArrayBlockingQueue<>(3);
        BlockingThreadPoolExecutor staticExecutor = new BlockingThreadPoolExecutor(1, 3, 1, TimeUnit.SECONDS, staticBlockingQueue);
        // staticExecutor.setRejectedExecutionHandler(new CustomRejectedExecutionHandler());
        staticExecutor.prestartAllCoreThreads();

        BlockingQueue<Runnable> dynamicBlockingQueue = new ArrayBlockingQueue<>(50);
        BlockingThreadPoolExecutor dynamicExecutor = new BlockingThreadPoolExecutor(1, 5, 5000, TimeUnit.MILLISECONDS, dynamicBlockingQueue);
        dynamicExecutor.prestartAllCoreThreads();

//        StaticWindowHandler staticWindowHandler = new StaticWindowHandler();
        DynamicWindowHandler dynamicWindowHandler = new DynamicWindowHandler();

        DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Spark config

        port(8080);

        after((request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://localhost:3000");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        // Routes

        get("/StaticWindow", "application/json", (req, res) -> {
            try {
                String timestamp = timestampFormat.format(LocalDateTime.now());
                String clientId = req.queryParams("clientId");
                System.out.println(timestamp + " Received static window request for client ID '" + clientId + "'");

                staticBlockingQueue.offer(new StaticWindowHandler(clientId));

                return "Successful static window request " + clientId;

            } catch(RejectedExecutionException e) {
                System.out.println(e.getMessage());
                res.status(503);
                return "Service Unavailable";
            }
        });

        get("/DynamicWindow", (req, res) -> {
            System.out.println(new Date() + " - Received dynamic window request");
            String clientId = req.queryParams("clientId");
            Long currentTimestamp = System.currentTimeMillis() / 1000;
            boolean shouldExecute = dynamicWindowHandler.verifyClientsRequests(clientId, currentTimestamp);
            if (shouldExecute) {
                dynamicExecutor.submit(dynamicWindowHandler);
            } else {
                res.status(503);
                return "Service Unavailable";
            }

            return "Successful dynamic window request " + clientId;
        });

        // TODO: If a key is pressed run the following
        // staticExecutor.shutdown();
        // try {
        //     boolean result = staticExecutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        //     System.out.println("Termination result = " + result);
        // } catch(InterruptedException e) {
        //     System.out.println(e.getMessage());
        // }
    }
}