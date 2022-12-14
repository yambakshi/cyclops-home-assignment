import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StaticWindowHandler implements Runnable {
    String clientId;
    DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public StaticWindowHandler(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void run() {
        String timestamp = this.timestampFormat.format(LocalDateTime.now());
        String threadName = Thread.currentThread().getName();

        try {
            System.out.println(timestamp + " - Static Window - Client ID: '"  + this.clientId + "' - '" + threadName + "' - Processing request...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            timestamp = this.timestampFormat.format(LocalDateTime.now());
            System.out.println(timestamp + " - Static Window - Client ID: '"  + this.clientId + "' - '" + threadName + "' - Thread was interrupted");
        }

        timestamp = this.timestampFormat.format(LocalDateTime.now());
        System.out.println(timestamp + " - Static Window - Client ID: '"  + this.clientId + "' - '" + threadName + "' - Finished processing static window in thread ");
    }
}