package engine.core;

import engine.util.Platform;

public class Application
{
    private static Platform platform;
    private static String version;
    private static boolean isInit = false;
    public static void init(String version)
    {
        if(isInit)
            throw new RuntimeException("");

        isInit = true;

        Application.version = version;

        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArch = System.getProperty("os.arch");

        // Java Virtual Machine Information
        String javaVersion = System.getProperty("java.version");
        String javaVendor = System.getProperty("java.vendor");
        String javaHome = System.getProperty("java.home");

        System.out.println("\nJava Virtual Machine:");
        System.out.println("Version: " + javaVersion);
        System.out.println("Vendor: " + javaVendor);
        System.out.println("Java Home: " + javaHome);

        if(osName.equals("Linux"))
            platform = Platform.LINUX;
        else if(osName.equals("Windows"))
            platform = Platform.WINDOWS;
        else if(osName.equals("Mac OS X"))
            platform = Platform.WINDOWS;
        else
            platform = Platform.UNSUPPORTED;
    }
    public static void quit()
    {
        System.exit(0);
    }
    public static Platform getPlatform()
    {
        return platform;
    }
    public static String getVersion()
    {
        return version;
    }
}
