package learnBot;



import learnBot.visuals.Config;
import learnBot.visuals.FieldVC;
import learnBot.visuals.WorldEngine;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class World
{
    private static WorldEngine engine;
    private static World instance;
    private final Field[][] fields;
    private final int width;
    private final int height;
    private World(int width, int height)
    {
        this.width = width;
        this.height = height;

        fields = new Field[width][height];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                fields[x][y] = new Field(x, y);
    }
    public static void createWorld(int width, int height, Runnable main)
    {
        engine = WorldEngine.create(width, height, main);

        if(true)
            return;

        if(instance != null)
            return;

        Properties properties = loadConfig();
        Config.init(properties);

        instance = new World(width, height);

        if(!Config.headlessModeEnabled())
            engine = WorldEngine.create(width, height, main);
        else
            main.run();
    }

    public static void placeCoin(int x, int y)
    {
        new Coin(x, y);
    }
    public static void placeCoins(int x, int y, int n)
    {
        for (int i = 0; i < n; i++)
            new Coin(x, y);
    }
    public static void placeBlock(int x, int y)
    {
        new Block(x, y);
    }
    public static void placeWall(int x, int y, boolean horizontal)
    {
        new Wall(x, y, horizontal);
    }
    protected static boolean positionInWorld(int x, int y)
    {
        return x >= 0 && x < instance.width &&
               y >= 0 && y < instance.height;
    }
    public static Field getField(int x, int y)
    {
        if(!positionInWorld(x, y))
            throw new RuntimeException("");

        return instance.fields[x][y];
    }
    protected static void registerEntity(@NotNull Entity entity)
    {
        instance.fields[entity.getX()][entity.getY()].addEntity(entity);
    }
    public static int getWidth()
    {
        return instance.width;
    }
    public static int getHeight()
    {
        return instance.height;
    }
    protected static Obstacle isObstacleBlockingField(int x, int y, Entity entity, boolean teleport)
    {
        return instance.fields[x][y].obstacleBlockingPath(entity, teleport);
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
