package temp.learnBot;

import engine.javafx.Engine;
import engine.javafx.GameManager;
import temp.learnBot.entity.CoinEntity;
import temp.learnBot.gameobjects.WorldConfig;
import org.jetbrains.annotations.NotNull;
import temp.learnBot.item.CoinItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class World
{
    private static WorldManager worldManager;
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
    public static void create(int width, int height, Runnable main)
    {
        if(instance != null)
            return;

        Properties properties = loadConfig();
        WorldConfig.init(properties);

        instance = new World(width, height);
        worldManager = WorldManager.create(width, height, main);
        Engine.run(worldManager);

        //main.run();

        if(true)
            return;

        if(!WorldConfig.headlessModeEnabled())
            worldManager = WorldManager.create(width, height, main);
        else
            main.run();
    }
    public static void placeCoin(int x, int y)
    {
        instance.placeCoin(x, y, new CoinItem());
    }
    public static void placeCoins(int x, int y, int n)
    {
        for (int i = 0; i < n; i++)
            placeCoin(x, y);
    }
    void placeCoin(int x, int y, CoinItem coinItem)
    {
        if(fields[x][y].hasEntityOfType(CoinEntity.class))
            fields[x][y].getEntitiesByType(CoinEntity.class).get(0).addItem(new CoinItem());

        else
            new CoinEntity(x, y, coinItem);
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

    void transferEntity(Entity<?> entity, int x, int y)
    {
        if(!positionInWorld(x ,y)) //TODO: better error handling
            throw new IllegalStateException("Position not in world!");

        instance.fields[entity.x][entity.y].removeEntity(entity);
        instance.fields[x][y].addEntity(entity);
    }

    void addEntity(@NotNull Entity<?> entity)
    {
        var x = entity.getX();
        var y = entity.getY();
        confirmPosition(x, y);
        instance.fields[x][y].addEntity(entity);
    }

    void removeEntity(@NotNull Entity<?> entity)
    {
        instance.fields[entity.getX()][entity.getY()].removeEntity(entity);
    }


    public static void confirmPosition(int x, int y)
    {
        if (x > instance.width - 1 || x < 0) throw new IllegalArgumentException("Invalid x-coordinate: " + x);
        if (y > instance.height - 1 || y < 0) throw new IllegalArgumentException("Invalid y-coordinate: " + y);
    }
    public static World getInstance()
    {
        return instance;
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
