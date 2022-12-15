import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicWindowTask implements Runnable {
    private static final Logger logger = LogManager.getLogger();
    String clientId;
    Long requestTimestamp;

    ConcurrentHashMap<String, ArrayList<Long>> clientsRequests = new ConcurrentHashMap<>();

    public DynamicWindowTask(String clientId, Long currentTimestamp) {
        this.clientId = clientId;
        this.requestTimestamp = currentTimestamp;
    }

    public String getClientId() {
        return clientId;
    }

    public Long getRequestTimestamp() {
        return this.requestTimestamp;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        try {
            logger.info("Dynamic Window - Client ID: '"  + this.clientId + "' - '" + threadName + "' - Processing request...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("Dynamic Window - Client ID: '"  + this.clientId + "' - '" + threadName + "' - Thread was interrupted");
        }

        logger.info("Dynamic Window - Client ID: '"  + this.clientId + "' - '" + threadName + "' - Finished processing static window in thread");
    }
}
