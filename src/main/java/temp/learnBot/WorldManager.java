package temp.learnBot;

import engine.core.Config;
import engine.identification.IDRegistry;
import engine.identification.Identifier;
import engine.javafx.*;
import engine.javafx.shapePainter.RectangleShape;
import engine.mathUtil.Vec2;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import temp.learnBot.gameobjects.FieldGameObject;
import temp.learnBot.gameobjects.WorldConfig;

import java.util.HashMap;


public class WorldManager extends GameManager
{
    //public final static Namespace NAMESPACE = new Namespace("world_engine");
    //public final static LoggerIdentifier LOGGER_ID = new LoggerIdentifier(NAMESPACE, "logger");
    //private final Logger logger = new Logger(LOGGER_ID);

    public final static Identifier COIN_SPRITE_ID = new Identifier("coin_gold.png");

    private final static Identifier PICKACHU_SPRITE_ID = new Identifier("pickachu.jpg");
    public final static Identifier COIN_PICKUP_SOUND_ID = new Identifier("collect_coin.wav");
    public final static Identifier LEVEL_COMPLETE_SOUND_ID = new Identifier("success.wav");


    private final int width;
    private final int height;
    private final Runnable main;
    private static WorldManager instance;
    public static WorldManager getInstance()
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
    public final static double speedLimit = 5;
    public static WorldManager create(int width, int height, Runnable main)
    {
        if(instance == null)
            instance = new WorldManager(width, height, main);

        return instance;
    }
    private final double totalWidth;
    private final double totalHeight;
    private final GameObject board = new GameObject();
    private final HashMap<Vec2, FieldGameObject> fields = new HashMap<>();
    private WorldManager(int width, int height, Runnable main)
    {
        this.width = width;
        this.height = height;
        this.main = main;

        totalWidth = width + WorldConfig.BORDER_SIZE * (width + 1);
        totalHeight = height + WorldConfig.BORDER_SIZE * (height + 1);
    }
    @Override
    public void init(Config config)
    {
        config.windowTitle = "FOPBot";
        config.unitSize = 64;
        config.windowWidth = (int) ((totalWidth + WorldConfig.MARGIN * 2) * config.unitSize);
        config.windowHeight = (int) ((totalHeight + WorldConfig.MARGIN * 2) * config.unitSize);

        //Font.loadFont(getClass().getResourceAsStream("/path/to/your/font.ttf"), 12);

        IDRegistry.register(IDRegistry.SPRITE, PICKACHU_SPRITE_ID);
        IDRegistry.register(IDRegistry.SPRITE, COIN_SPRITE_ID);
        //AudioManager.registerSoundEffect(COIN_PICKUP_SOUND_ID);
        //AudioManager.registerSoundEffect(LEVEL_COMPLETE_SOUND_ID);
    }
    @Override
    public void start()
    {
        var primaryScene = new GameScene();

        var dimensions = Window.getDimensions();
        var primaryCamera = new Camera(dimensions.x, dimensions.y);
        primaryScene.add(primaryCamera);

        var boardRenderer = new ShapeRenderer(totalWidth, totalHeight);
        boardRenderer.addShape(new RectangleShape(0,0, totalWidth, totalHeight, Color.GREY));
        board.setCanvasRenderer(boardRenderer);
        primaryScene.add(board);

        var hb = WorldConfig.BORDER_SIZE * 0.5;
        var hw = totalWidth * 0.5;
        var hh = totalHeight * 0.5;

        for (Direction dir : Direction.values())
        {
            double x = !dir.isHorizontal() ? 0 : dir == Direction.RIGHT ? hw - hb : -hw + hb;
            double y = dir.isHorizontal() ? 0 : dir == Direction.DOWN ? hh - hb : -hh + hb;

            double borderWidth  = !dir.isHorizontal() ? totalWidth : WorldConfig.BORDER_SIZE;
            double borderHeight = !dir.isHorizontal() ? WorldConfig.BORDER_SIZE : totalHeight;

            var border = new GameObject();
            border.getTransform().setPosition(x, y);

            var color = new Color(255/255d,255/255d,255/255d,1); //TODO: color palette
            var renderer = new ShapeRenderer(borderWidth, borderHeight);
            renderer.addShape(new RectangleShape(0, 0, borderWidth, borderHeight, color));
            border.setCanvasRenderer(renderer);
            border.setParent(board);
        }

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                var pos = convertCoords(x, y);
                var field = new FieldGameObject(pos);
                fields.put(pos, field);
                field.setParent(board);
            }
        }

        new Thread(main).start();
    }
    public static Runnable runnable = () -> {};
    @Override
    public void update()
    {
        runnable.run();
        /*
        var speed = 1d + WorldConfig.BORDER_SIZE;

        if(Input.getKeyDown(KeyCode.SPACE))
        {
            robot.playTeleport((int) (Math.random() * 5), (int) (Math.random() * 5));
        }
        if(Input.getKeyDown(KeyCode.S))
        {
            var pos = robot.getTransform().getPosition();
            robot.getTransform().setPosition(pos.x, pos.y - speed);
        }
        if(Input.getKeyDown(KeyCode.A))
        {
            var pos = robot.getTransform().getPosition();
            robot.getTransform().setPosition(pos.x - speed, pos.y);
        }
        if(Input.getKeyDown(KeyCode.D))
        {
            var pos = robot.getTransform().getPosition();
            robot.getTransform().setPosition(pos.x + speed, pos.y);
        }
         */
    }
    void addObject(GameObject object)
    {
        Platform.runLater(() -> object.setParent(board));
    }
    void clearObject(GameObject object)
    {

    }
    public Vec2 convertCoords(int x, int y)
    {
        double offsetX = totalWidth * 0.5 - 0.5 - WorldConfig.BORDER_SIZE;
        double offsetY = totalHeight * 0.5 - 0.5 - WorldConfig.BORDER_SIZE;
        var mx = x - offsetX + WorldConfig.BORDER_SIZE * x;
        var my = y - offsetY + WorldConfig.BORDER_SIZE * y;
        return new Vec2(mx, my);
    }
}