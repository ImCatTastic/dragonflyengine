package engine;

import com.sun.prism.GraphicsPipeline;
import engine.mathUtil.Vec2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import org.jetbrains.annotations.NotNull;
import learnBot.eventHandlers.KeyListener;
import learnBot.eventHandlers.MouseListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Renderer extends Application
{
    public static final int Z_INDEX_LIMIT = 20;
    private static Renderer instance;
    public static Renderer getInstance()
    {
        if(instance == null)
            throw new RuntimeException("Instance not created yet!");

        return instance;
    }
    public Renderer()
    {
        if(instance == null)
            instance = this;
    }

    private static Runnable gameLogic;
    private static EventConfig eventConfig;
    public static void configure(double width, double height, double unit, @NotNull Runnable mainCode, @NotNull EventConfig eventConfig)
    {
        if(isInit)
            return;

        Renderer.unit.set(unit);
        instance.initialWindowWidth = width * unit;
        instance.initialWindowHeight = height * unit;

        Renderer.eventConfig = eventConfig;
        Renderer.gameLogic = mainCode;
        isInit = true;
    }
    private static boolean isInit = false;
    //#endregion

    //#region components
    private Scene scene;
    private Stage primaryStage;
    private StackPane container;
    private final Pane[] layers = new Pane[Z_INDEX_LIMIT + 1];
    //#endregion

    public static final SimpleDoubleProperty unit = new SimpleDoubleProperty(1);
    public final SimpleDoubleProperty scale = new SimpleDoubleProperty(1);
    private static double windowWidth;
    private static double windowHeight;
    private static double initialWindowWidth;
    private static double initialWindowHeight;


    //#region events
    private final ExecutorService eventExecutorService = Executors.newSingleThreadExecutor();
    private final ArrayList<KeyListener> keyListeners = new ArrayList<>();
    private final ArrayList<MouseListener> mouseListeners = new ArrayList<>();
    private final Set<KeyCode> keysPressed = new HashSet<>();
    @Override
    public void start(Stage primaryStage)
    {
        if(!isInit)
            throw new RuntimeException("Renderer not initialized!");

        windowWidth = initialWindowWidth;
        windowHeight = initialWindowHeight;

        Pane root = new StackPane();
        scene = new Scene(root, windowWidth, windowHeight, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FOPEngine");

        StackPane pane = new StackPane();
        //pane.setStyle("-fx-background-color: red");
        //pane.setPrefSize(100, 500);
        root.getChildren().add(pane);


        for (int i = 0; i < layers.length; i++)
            layers[i] = new Pane();
        pane.getChildren().addAll(layers);

        primaryStage.show();

        if(gameLogic != null)
        {
            Thread logicThread = new Thread(gameLogic);
            logicThread.start();
        }

        if(eventConfig.enableResizeEvents)
        {
            scene.widthProperty().addListener((observable, oldValue, newValue) ->
            {
                windowWidth = newValue.intValue();
                resize();
            });

            scene.heightProperty().addListener((observable, oldValue, newValue) ->
            {
                windowHeight = newValue.intValue();
                resize();
            });
        }

        new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                for (GameObject gameObject : gameObjects)
                {
                    Vec2 worldPos = gameObject.getWorldCoords();
                    objectComps.get(gameObject).relocate(worldPos.x * unit.get() * scale.get(), worldPos.y * unit.get() * scale.get());
                    objectComps.get(gameObject).setRotate(gameObject.getRotation());
                }
            }
        }.start();

        getCurrentGraphicsPipeline();
    }

    public Rectangle createRectangle(double x, double y, double width, double height)
    {
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(unit.multiply(width).multiply(scale));
        rectangle.heightProperty().bind(unit.multiply(height).multiply(scale));
        rectangle.xProperty().bind(unit.multiply(x).multiply(scale));
        rectangle.yProperty().bind(unit.multiply(y).multiply(scale));
        return rectangle;
    }




    ArrayList<GameObject> gameObjects = new ArrayList<>();
    HashMap<GameObject, Group> objectComps = new HashMap<>();
    protected void registerGameObject(GameObject gameObject, Group rootNode)
    {
        Platform.runLater(() ->
        {
            layers[gameObject.getZIndex()].getChildren().add(rootNode);
            objectComps.put(gameObject, rootNode);
            gameObjects.add(gameObject);
        });
    }

    public void addMouseListener(MouseListener listener)
    {
        mouseListeners.add(listener);
    }

    public void addKeyListener(KeyListener listener)
    {
        keyListeners.add(listener);
    }

    private void resize()
    {
        scale.set(Math.min(
            windowWidth / initialWindowWidth,
            windowHeight / initialWindowHeight));
}
private static void getCurrentGraphicsPipeline()
    {
        String msg = GraphicsPipeline.getPipeline().getClass().getName();

        if(msg.equals("com.sun.prism.d3d.D3DPipeline"))
            System.out.println("Note: Using Direct3D pipleline.");

        else
            System.out.println("Note: Using " + msg + " pipleline.");
    }
}

