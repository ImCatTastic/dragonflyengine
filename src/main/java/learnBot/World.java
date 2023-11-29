package learnBot;

import engine.Engine;
import engine.GameManager;
import learnBot.visualComponent.BoardVC;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class World
{
    private static int width;
    private static int height;
    public static double speed = 1;
    public final static double speedLimit = 10;
    private static Field[][] fields;

    public static void createWorld(int width, int height, Runnable main)
    {
        Properties properties = loadConfig();
        Config.init(properties);

        World.width = width;
        World.height = height;

        if(!Config.headlessModeEnabled())
        {
            Engine.width = width + Config.BORDER_SIZE_FACTOR * (width + 1) + Config.MARGIN * 2;
            Engine.height = height + Config.BORDER_SIZE_FACTOR * (height + 1) + Config.MARGIN * 2;
            Engine.unit = Config.UNIT;
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

                        BoardVC boardVC =  new BoardVC(width, height, Config.BORDER_SIZE_FACTOR);

                        for (Direction value : Direction.values())
                            new Border(value);

                        fields = new Field[width][height];
                        for (int y = 0; y < height; y++)
                            for (int x = 0; x < width; x++)
                                fields[x][y] = new Field(x, y, boardVC);

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
            fields = new Field[width][height];
            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++)
                    fields[x][y] = new Field(x,y);

            main.run();
        }
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
        return x >= 0 && x < World.getWidth() &&
               y >= 0 && y < World.getHeight();
    }

    public static Field getField(int x, int y)
    {
        if(!positionInWorld(x, y))
            throw new RuntimeException("");

        return fields[x][y];
    }

    protected static void registerEntity(Entity entity)
    {
        fields[entity.getX()][entity.getY()].addEntity(entity);
    }

    public static int getWidth()
    {
        return width;
    }
    public static int getHeight()
    {
        return height;
    }

    protected static Obstacle isObstacleBlockingField(int x, int y, Entity entity, boolean teleport)
    {
        return fields[x][y].obstacleBlockingPath(entity, teleport);
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
