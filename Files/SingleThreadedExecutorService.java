package sd.lab.concurrency.exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class SingleThreadedExecutorService implements ExecutorService {

    private volatile boolean shutdown = false;
    private final CompletableFuture<?> termination = new CompletableFuture<>();
    private final BlockingQueue<Runnable> tasks = new LinkedBlockingDeque<>();
    private final Thread backgroundThread = new Thread(this::backgroundThreadMain);

    public SingleThreadedExecutorService() {
        backgroundThread.start();
    }

    private void backgroundThreadMain() {
        while(true){
            final Runnable toExec = tasks.poll();
            if(toExec == null){
                if(shutdown){
                    termination.complete(null);
                    return;
                }
                continue;
            }
            try {
                toExec.run();
            } catch (Exception ex) {
                if(toExec instanceof FutureTask){
                }
            }
        }
    }

    @Override
    public void shutdown() {
        shutdown = true;
    }

    @Override
    public List<Runnable> shutdownNow() {
        shutdown();
        backgroundThread.interrupt();
        var runnables = new ArrayList<Runnable>();
        tasks.drainTo(runnables);
        return runnables;
    }

    @Override
    public boolean isShutdown() {
        return shutdown;
    }

    @Override
    public boolean isTerminated() {
        return termination.isDone();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        try {
            termination.get(timeout, unit);
            return true;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        if(isShutdown()){
            throw new RejectedExecutionException();
        }
        FutureTask futureTask = new FutureTask<>(() -> {
            try {
                T res = task.call();
                return res;
            }catch (Exception ex){
                return ex;
            }
        });
        tasks.add(futureTask);
        return futureTask;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        if(isShutdown()){
            throw new RejectedExecutionException();
        }
        FutureTask futureTask = new FutureTask(() -> {
            task.run();
            return result;
        });
        tasks.add(futureTask);
        return futureTask;
    }

    @Override
    public Future<?> submit(Runnable task) {
        return submit(task, null);
    }

    @Override
    public void execute(Runnable command) {
        if(isShutdown()){
            throw new RejectedExecutionException();
        }
        tasks.add(command);
    }

    // ignore the following methods: they are not tested

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        throw new Error("this must not be implemented");
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        throw new Error("this must not be implemented");
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        throw new Error("this must not be implemented");
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new Error("this must not be implemented");
    }
}
