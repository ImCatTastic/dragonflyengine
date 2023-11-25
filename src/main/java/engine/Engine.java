package engine;


import javafx.application.Application;

public class Engine
{
    public static double width = 10;
    public static double height = 10;
    public static double unit = 10;
    public static final EventConfig eventConfig = new EventConfig();
    private static boolean running = false;
    public static void start(Runnable main)
    {
        if(running)
            return;

        running = true;
        Renderer.configure(width, height, unit, main, eventConfig);
        Application.launch(Renderer.class);
    }
}
