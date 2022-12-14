import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.*;

public class BlockingThreadPoolExecutor extends ThreadPoolExecutor {
    DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    ConcurrentHashMap<String, ArrayList<Long>> clientsRequests = new ConcurrentHashMap<>();

    public BlockingThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                      TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new ThreadPoolExecutor.AbortPolicy());
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        String threadName = Thread.currentThread().getName();
        String timestamp = this.timestampFormat.format(LocalDateTime.now());
        System.out.println(timestamp + " - Static Window - '"  + threadName + "' - Performing beforeExecute() logic...");
    }

    @Override
    public void execute(final Runnable task) {
        String clientId = ((StaticWindowTask)task).getClientId();
        Long requestTimestamp = ((StaticWindowTask)task).getRequestTimestamp();
        ArrayList<Long> clientRequestsCount = this.clientsRequests.computeIfAbsent(clientId, k -> new ArrayList<>());
        int requestsSize = clientRequestsCount.size();

        // If less than 5 requests' timestamps were stored
        if (requestsSize < 5) {

            // If it's not the start-timeframe request
            // and more than 5 secs have passed since the first request
            if (requestsSize > 0 && requestTimestamp - clientRequestsCount.get(0) > 5) {
                clientRequestsCount.clear();
            }

            clientRequestsCount.add(requestTimestamp);
        } else { // 5 requests' timestamps or more are already stored it means it's the 6th or more request

            // If more than 5 secs have passed since the first request
            if (requestTimestamp - clientRequestsCount.get(0) > 5) {
                clientRequestsCount.clear();
                clientRequestsCount.add(requestTimestamp);
            } else {
                throw new RejectedExecutionException("Client ID: '" + clientId + "' Exceeded rate limit of 5 requests in 5 seconds");
            }
        }

        super.execute(task);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        String timestamp = this.timestampFormat.format(LocalDateTime.now());
        String threadName = Thread.currentThread().getName();

        if (t != null) {
            System.out.println(timestamp + " - Static Window - '"  + threadName + " - Performing exception handler logic...");
            t.printStackTrace();
        }

        System.out.println(timestamp + " - Static Window - '"  + threadName + "' - Performing afterExecute() logic...");
    }
}