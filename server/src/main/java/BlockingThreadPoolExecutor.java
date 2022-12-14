import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class BlockingThreadPoolExecutor extends ThreadPoolExecutor {
    private final Semaphore semaphore;
    DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public BlockingThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                      TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new ThreadPoolExecutor.AbortPolicy());
        semaphore = new Semaphore(corePoolSize);
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
        boolean acquired = false;

        do {
            try {
                semaphore.acquire();
                acquired = true;
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        } while (!acquired);

        try {
            super.execute(task);
        } catch (final RejectedExecutionException e) {
            System.out.println("Task Rejected");
            semaphore.release();
            throw e;
        }
        semaphore.release();
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