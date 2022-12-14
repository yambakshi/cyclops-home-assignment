import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

public class StaticWindowTask implements Callable<String> {
    String clientId;
    DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public StaticWindowTask(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String call() throws Exception {
        String threadName = Thread.currentThread().getName();
        String timestamp = this.timestampFormat.format(LocalDateTime.now());

        System.out.println(timestamp + " Processing static window request for client ID '" + this.clientId + "' in thread " + threadName + "...");
        Thread.sleep(1000);
        System.out.println(timestamp + " Finished processing static window in thread " + threadName + " for client ID '" + this.clientId + "'");

        // return the thread name executing this callable task
        return Thread.currentThread().getName();
    }
}