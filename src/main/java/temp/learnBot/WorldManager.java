package temp.learnBot;

import engine.core.*;
import engine.identification.IDRegistry;
import engine.identification.Identifier;
import engine.rendering.ShapeRenderer;
import engine.shapePainter.RectangleShape;
import engine.ui.Anchor;
import engine.ui.shape.Rectangle2D;
import engine.util.Config;
import engine.util.FitMode;
import engine.util.math.Vec2;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
        config.windowWidth = 660;
        config.windowHeight = 660;

        IDRegistry.register(IDRegistry.SPRITE, PICKACHU_SPRITE_ID);
        IDRegistry.register(IDRegistry.SPRITE, COIN_SPRITE_ID);
        //AudioManager.registerSoundEffect(COIN_PICKUP_SOUND_ID);
        //AudioManager.registerSoundEffect(LEVEL_COMPLETE_SOUND_ID);
    }

    private Camera primaryCamera;

    @Override
    public void start()
    {
        var primaryScene = new GameScene();
        primaryCamera = new Camera();
        primaryCamera.setHorizontalSize(totalWidth + 3);
        primaryCamera.setVerticalSize(totalHeight + 3);
        primaryCamera.setFitMode(FitMode.MIN);
        primaryScene.addGameObject(primaryCamera);
        View.getPrimary().setActiveCamera(primaryCamera);

        var boardRenderer = new ShapeRenderer(totalWidth, totalHeight);
        boardRenderer.addShape(new RectangleShape(0,0, totalWidth, totalHeight, Color.GREY));
        board.setCanvasRenderer(boardRenderer);
        primaryScene.addGameObject(board);

        for (int y = 0; y < 1; y++)
        {
            for (int x = 0; x < 4; x++)
            {
                var pos = convertCoords(x, y);
                var field = new FieldGameObject(pos);
                fields.put(pos, field);
                field.setParent(board);
            }
        }

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

        //TEST START:
        //TODO: remove

        int totalSize = 150;

        y.setFill(Color.BLUEVIOLET);
        y.setLeft((double) totalSize / 4);
        y.setBottom(150);
        y.setWidth(75);
        y.setHeight(totalHeight * 100);
        y.setHorizontalAnchor(Anchor.Horizontal.LEFT);
        y.setVerticalAnchor(Anchor.Vertical.BOTTOM);
        SceneManager.getActiveScene().getUiCanvas().add(y);

        //TEST END:




        new Thread(main).start();
    }
    Rectangle2D y = new Rectangle2D();
    public static Runnable runnable = () -> {};

    double i = 0;
    @Override
    public void update()
    {
        runnable.run();

        i = (i + 2.75) % 1000;
        y.setHeight(i);

        if(true)
            return;

        double size = 2.5;

        i += size * 0.005;
        i %= size * 2;
        double out;
        if(i > size)
        {
            out = size * 2 - i;
        }
        else
        {
            out = i;
        }

        //https://gamedev.stackexchange.com/questions/188841/how-to-smoothly-interpolate-2d-camera-with-pan-and-zoom
        primaryCamera.setZoom(1 * Math.pow(size, out / (size * 2)));
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