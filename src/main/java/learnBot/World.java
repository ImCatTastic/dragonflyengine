package learnBot;

import engine.Engine;
import engine.GameManager;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class World
{
    private static int width;
    private static int height;
    private static Board board;
    public static double speed = 1;
    public final static double speedLimit = 10;

    public static void createWorld(int width, int height, Runnable main)
    {
        System.out.println("hello world");

        Properties properties = loadConfig();
        Config.init(properties);

        World.width = width;
        World.height = height;

        if(!Config.headlessModeEnabled())
        {
            Engine.width = width + Config.borderSize * (width + 1) + Config.margin * 2;
            Engine.height = height + Config.borderSize * (height + 1) + Config.margin * 2;
            Engine.unit = Config.unit;
            Engine.start(new GameManager()
            {
                @Override
                public void onStart()
                {
                    //Create new thread since fop runs as one continuous program and would block the loop
                    new Thread(() ->
                    {
                        World.width = width;
                        World.height = height;
                        World.board = new Board(width, height, Config.borderSize);

                        for (Direction value : Direction.values())
                            new Border(value);

                        main.run();
                    }).start();
                }

                @Override
                public void onUpdate()
                {

                }
            });
        }

        else
        {
            main.run();
        }
    }

    public static int getWidth()
    {
        return width;
    }
    public static int getHeight()
    {
        return height;
    }

    private static @NotNull Properties loadConfig()
    {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("config.properties");

        try {
            prop.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prop;
    }
}
