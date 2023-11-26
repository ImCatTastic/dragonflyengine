package learnBot;

public class Sync
{
    private static final Object lock = new Object();
    private static boolean isReady = false;

    public static void waitForSignal()
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
    public static void giveSignal()
    {
        synchronized (lock)
        {
            isReady = true;
            lock.notify();
        }
    }
}
