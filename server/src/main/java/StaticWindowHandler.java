import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class StaticWindowHandler implements Runnable {
    ConcurrentHashMap<String, ArrayList<Long>> clientsRequests = new ConcurrentHashMap<>();

    public boolean verifyClientRequests(String clientId, Long currentTimestamp) {
        ArrayList<Long> clientRequestsCount = this.clientsRequests.computeIfAbsent(clientId, k -> new ArrayList<>());

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
                return false;
            }
        }

        return true;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println(new Date() + " Processing static window request in thread " + threadName + "...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(new Date() + "thread " + threadName + " was interrupted");
        }

        System.out.println(new Date() + " Finished processing in thread " + threadName);
    }
}