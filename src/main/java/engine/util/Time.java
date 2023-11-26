package engine.util;

public class Time
{
    public static double deltaTime;

    public static void pause(int duration)
    {
        try
        {
            Thread.sleep(duration);
        }

        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
