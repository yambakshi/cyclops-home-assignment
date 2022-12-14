import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicWindowHandler implements Runnable {

    ConcurrentHashMap<String, ArrayList<Long>> clientsRequests = new ConcurrentHashMap<>();

    public boolean verifyClientRequests(String clientId, Long currentTimestamp) {
        ArrayList<Long> clientRequestsCount = this.clientsRequests.computeIfAbsent(clientId, k -> new ArrayList<>());



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
