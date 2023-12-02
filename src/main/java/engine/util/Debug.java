package engine.util;

public class Debug
{
    public static void printStackTrace()
    {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (StackTraceElement caller : stackTrace)
        {
            System.out.println(caller.getClassName() + " : " + caller.getLineNumber());
        }
        System.out.println(" ");
        System.out.println(" ");
    }
}
