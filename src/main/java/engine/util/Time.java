package engine.util;

public class Time
{
    /**
     * Readonly property!!!
     * Modification will result
     * in bugs!!!
     */
    public static double deltaTime;
    public static int millisDeltaTime;
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