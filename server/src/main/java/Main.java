import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
//        StaticWindowHandler staticWindowHandler = new StaticWindowHandler();
//        DynamicWindowHandler dynamicWindowHandler = new DynamicWindowHandler();
//        Thread staticWindowThread = new Thread(staticWindowHandler, "staticWindowThread");
//        Thread dynamicWindowThread = new Thread(dynamicWindowHandler, "dynamicWindowThread");
        ConcurrentHashMap<String, ArrayList<Long>> clientsRequests = new ConcurrentHashMap<>();

        port(8080);

        after((request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://localhost:3000");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        get("/StaticWindow", "application/json", (req, res) -> {
//            staticWindowThread.start();

            String clientId = req.queryParams("clientId");
            Long currentTimestamp = System.currentTimeMillis() / 1000;
            ArrayList<Long> clientRequestsCount = clientsRequests.computeIfAbsent(clientId, k -> new ArrayList<>());

            // If less than 5 requests' timestamps were stored
            int requestsSize = clientRequestsCount.size();
            if (requestsSize < 5) {

                // If more than 5 secs have passed since the first request
                if (requestsSize > 0 && currentTimestamp - clientRequestsCount.get(0) > 5) {
                    clientRequestsCount.clear();
                }

                clientRequestsCount.add(currentTimestamp);
            } else { // In the 6th request

                // If more than 5 secs have passed since the first request
                if (currentTimestamp - clientRequestsCount.get(0) > 5) {
                    clientRequestsCount.clear();
                    clientRequestsCount.add(currentTimestamp);
                } else {
                    res.status(503);
                    return "Service Unavailable";

                }
            }

            return "Static World for client " + clientId;
        });

        get("/DynamicWindow", (req, res) -> {
//            dynamicWindowThread.start();

            String clientId = req.queryParams("clientId");
            Long currentTimestamp = System.currentTimeMillis() / 1000;
            ArrayList<Long> clientRequestsCount = clientsRequests.computeIfAbsent(clientId, k -> new ArrayList<>());

            return "Dynamic World for client " + clientId;
        });
    }
}