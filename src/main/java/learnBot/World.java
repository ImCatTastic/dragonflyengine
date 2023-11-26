package learnBot;

import engine.Engine;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class World
{
    private static int width;
    private static int height;
    private static Board board;

    public static void createWorld(int width, int height, Runnable main)
    {
        Properties properties = loadConfig();
        Config.init(properties);

        World.width = width;
        World.height = height;

        Engine.width = width + Config.borderSize * (width + 1) + Config.margin * 2;
        Engine.height = height + Config.borderSize * (height + 1) + Config.margin * 2;
        Engine.unit = Config.unit;
        Engine.start(() ->
        {
            World.width = width;
            World.height = height;
            World.board = new Board(width, height, Config.borderSize);

            new Border(Direction.LEFT);
            new Border(Direction.UP);
            new Border(Direction.RIGHT);
            new Border(Direction.DOWN);

            main.run();
        });
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
