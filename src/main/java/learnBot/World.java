package learnBot;

import engine.Engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class World
{
    private static World instance;
    public static World getInstance() {
        return instance;
    }

    public Board board;
    public static void createWorld(int width, int height, Runnable main)
    {
        if(instance != null)
            throw new RuntimeException("World already created");

        Properties properties = loadConfig();
        Engine.width = width;
        Engine.height = height;
        Engine.unit = Double.parseDouble(properties.getProperty("unit"));
        Engine.start(main);

        instance = new World(width, height, Double.parseDouble(properties.getProperty("border-size")));
    }

    private final int width;
    private final int height;
    private World(int width, int height, double borderSize)
    {
        this.width = width;
        this.height = height;
        this.board = new Board(width, height, borderSize);
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }

    private static Properties loadConfig()
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
