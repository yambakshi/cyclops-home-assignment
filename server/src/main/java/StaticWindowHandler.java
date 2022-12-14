import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionException;

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
            try {
                System.out.println(timestamp + " Processing static window request for client ID '" + this.clientId + "' in thread '" + threadName + "'...");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(timestamp + " Thread '" + threadName + "' of client ID '" + this.clientId + "' was interrupted");
            }

            System.out.println(timestamp + " Finished processing static window in thread '" + threadName + "' for client ID '" + this.clientId + "'");
        } catch (RejectedExecutionException e) {
            System.out.println(timestamp + " Thread '" + threadName + "' for client ID '" + this.clientId + "' was rejected");
        }

    }
}