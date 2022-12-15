import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StaticWindowTask implements Runnable {
    private static final Logger logger = LogManager.getLogger();
    String clientId;
    Long requestTimestamp;

    public StaticWindowTask(String clientId, Long currentTimestamp) {
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
            logger.info("Static Window - Client ID: '"  + this.clientId + "' - '" + threadName + "' - Processing request...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("Static Window - Client ID: '"  + this.clientId + "' - '" + threadName + "' - Thread was interrupted");
        }

        logger.info("Static Window - Client ID: '"  + this.clientId + "' - '" + threadName + "' - Finished processing static window in thread");
    }
}