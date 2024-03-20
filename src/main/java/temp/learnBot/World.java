package temp.learnBot;

import engine.core.Engine;
import org.jetbrains.annotations.NotNull;
import temp.learnBot.item.CoinItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private final List<Entity<?>> obstacles = new ArrayList<>();
    public void gameOver()
    {
        for (Entity<?> obstacle : obstacles)
        {
            obstacle.gameOver();
        }
    }


    public static void create(int width, int height, Runnable main)
    {
        if(instance != null)
            throw new IllegalStateException("An instance of World already exists.");

        instance = new World(width, height);

        Runnable runnable = () ->
        {
            for (int i = 0; i < width; i++)
                new Wall(i, 0, Direction.DOWN);
            for (int i = 0; i < width; i++)
                new Wall(i, height - 1, Direction.UP);
            for (int i = 0; i < height; i++)
                new Wall(width - 1, i, Direction.RIGHT);
            for (int i = 0; i < width; i++)
                new Wall(0, i, Direction.LEFT);

            main.run();
        };

        if(UserConfig.enableGUI)
        {
            worldManager = WorldManager.create(width, height, runnable);
            Engine.run(worldManager);
        }
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
        if(fields[x][y].hasEntityOfType(Coin.class))
            fields[x][y].getEntitiesByType(Coin.class).get(0).addItem(new CoinItem());

        else
            new Coin(x, y, coinItem);
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

    public List<Entity<?>> getAllEntities()
    {
        return Arrays.stream(fields)
                     .flatMap(row -> Arrays.stream(row).flatMap(field -> field.getEntities().stream()))
                     .collect(Collectors.toList());
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
        if(entity instanceof Obstacle obstacle)
            obstacles.add(entity);

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
    protected static Obstacle isObstacleBlockingField(int x, int y, Entity<?> entity, boolean teleport)
    {
        return instance.fields[x][y].obstacleBlockingPath(entity, teleport);
    }
}