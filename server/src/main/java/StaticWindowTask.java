import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StaticWindowTask implements Runnable {
    String clientId;
    Long requestTimestamp;
    DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

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
        String timestamp = this.timestampFormat.format(LocalDateTime.now());
        String threadName = Thread.currentThread().getName();

        try {
            System.out.println(timestamp + " - Static Window - Client ID: '"  + this.clientId + "' - '" + threadName + "' - Processing request...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            timestamp = this.timestampFormat.format(LocalDateTime.now());
            System.out.println(timestamp + " - Static Window - Client ID: '"  + this.clientId + "' - '" + threadName + "' - Thread was interrupted");
        }

        timestamp = this.timestampFormat.format(LocalDateTime.now());
        System.out.println(timestamp + " - Static Window - Client ID: '"  + this.clientId + "' - '" + threadName + "' - Finished processing static window in thread ");
    }
}