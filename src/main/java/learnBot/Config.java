package learnBot;

import java.util.Properties;

public class Config
{
    public static final double unit = 75;
    public static final double margin = 1;
    public static final double borderSize = 0.1;
    public static final double innerFieldFactor = 0.75;

    private static boolean isInit = false;
    public static void init(Properties properties)
    {
        if(isInit)
            return;

        isInit = true;
        headlessMode = Boolean.parseBoolean(properties.getProperty("headless-mode"));
    }
    private static boolean headlessMode = false;

    public static boolean headlessModeEnabled()
    {
        return headlessMode;
    }
}
