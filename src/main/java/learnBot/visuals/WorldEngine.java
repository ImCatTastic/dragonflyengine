package learnBot.visuals;

import engine.core.Engine;
import engine.core.GameManager;
import engine.core.GameObject;
import engine.core.ScalingMode;
import engine.logging.LogCategory;
import engine.logging.LogPriority;
import engine.logging.Logger;
import engine.logging.LoggerIdentifier;
import engine.util.Namespace;
import learnBot.Direction;


public class WorldEngine extends GameManager
{
    public final static Namespace WORLD_ENGINE_NAMESPACE = new Namespace("world_engine");
    public final static LoggerIdentifier LOGGER_ID = new LoggerIdentifier(WORLD_ENGINE_NAMESPACE, "logger");
    private final Logger logger = new Logger(LOGGER_ID);

    private BoardVC boardVC;
    private final int width;
    private final int height;
    private final Runnable main;






    private static WorldEngine instance;
    public static WorldEngine getInstance()
    {
        return instance;
    }
    public double speed = 1;
    public static void setSpeed(double value)
    {
        instance.speed = value;
    }
    public static double getSpeed()
    {
        return instance.speed;
    }
    final static double speedLimit = 10;
    public static void addChild(FOPVisualComponent fopVisualComponent)
    {
        instance.boardVC.addChild(fopVisualComponent);
    }

    public static WorldEngine create(int width, int height, Runnable main)
    {
        if(instance == null)
            instance = new WorldEngine(width, height, main);

        return instance;
    }
    private WorldEngine(int width, int height, Runnable main)
    {
        this.width = width;
        this.height = height;
        this.main = main;
        start();
    }

    @Override
    protected void onStart()
    {
        logger.log("Test", LogPriority.LOW, LogCategory.DEBUG);


        if(true)
            return;

        //Create new thread since fop runs as one continuous program and would block the loop
        new Thread(() ->
        {
            boardVC = new BoardVC(width, height, Config.BORDER_SIZE_FACTOR);

            for (Direction value : Direction.values())
                new BorderVC(value);

            main.run();
        }).start();
    }

    @Override
    protected void onUpdate() {

    }
}
