package temp.learnBot;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskScheduler
{
    private final static int taskLimit = 64;
    private final Object lock = new Object();
    private final AtomicBoolean running = new AtomicBoolean();
    private boolean isReady = false;
    private final LinkedList<Runnable> tasks = new LinkedList<>();
    public void schedule(Runnable task)
    {
        if(!UserConfig.enableGUI)
            return;

        if(running.get())
            throw new IllegalStateException("cannot interact with scheduler while executing");

        if(tasks.size() >= taskLimit)
            throw new IllegalStateException("TaskLimit exceeded.");

        tasks.add(task);

        if(UserConfig.sequentialScheduling)
            execute();
    }
    public void notifyCompletion() //NOTE: executed from other thread
    {
        if(tasks.isEmpty())
        {
            running.set(false);

            if(UserConfig.sequentialScheduling)
            {
                synchronized (lock)
                {
                    isReady = true;
                    lock.notify();
                }
            }

            return;
        }

        tasks.removeFirst().run();
    }
    public void execute()
    {
        if(running.get())
            throw new IllegalStateException("cannot interact with scheduler while executing");

        if(tasks.isEmpty())
            return;

        running.set(true);
        tasks.removeFirst().run();

        if(UserConfig.sequentialScheduling)
        {
            isReady = false;
            synchronized (lock)
            {
                while (!isReady)
                {
                    try
                    {
                        lock.wait();
                    }

                    catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    public boolean isRunning()
    {
        return running.get();
    }
}