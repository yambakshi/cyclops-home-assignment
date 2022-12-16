import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.concurrent.*;

public class StaticThreadPoolExecutor extends ThreadPoolExecutor {
    private static final Logger logger = LogManager.getLogger();
    ConcurrentHashMap<String, ArrayList<Long>> clientsRequests = new ConcurrentHashMap<>();

    public StaticThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                    TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new ThreadPoolExecutor.AbortPolicy());
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        String threadName = Thread.currentThread().getName();
        logger.info("Static Window - '"  + threadName + "' - Performing beforeExecute() logic...");
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
            // and more than 5 secs have passed since the start-timeframe request
            if (requestsSize > 0 && requestTimestamp - clientRequestsCount.get(0) > 5) {
                clientRequestsCount.clear();
            }

            clientRequestsCount.add(requestTimestamp);
        } else { // 5 requests' timestamps or more are already stored (which means it's the 6th or more request)

            // If more than 5 secs have passed since the start-timeframe request
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
        String threadName = Thread.currentThread().getName();

        if (t != null) {
            logger.info("Static Window - '"  + threadName + " - Performing exception handler logic...");
            t.printStackTrace();
        }

        logger.info("Static Window - '"  + threadName + "' - Performing afterExecute() logic...");
    }
}