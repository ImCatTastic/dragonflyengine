package engine;

import com.sun.prism.GraphicsPipeline;
import engine.mathUtil.Vec2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Renderer extends Application
{
    public static final int Z_INDEX_LIMIT = 20;
    private static Renderer instance;
    static Renderer getInstance()
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

    private final Pane[] layers = new Pane[Z_INDEX_LIMIT + 1];
    //#endregion
    private double windowWidth;
    private double windowHeight;
    private double initialWindowWidth;
    private double initialWindowHeight;


    private static Runnable onLoadRunnable;
    static void onLoadComplete(Runnable runnable)
    {
        onLoadRunnable = runnable;
    }

    //#region events
    @Override
    public void start(@NotNull Stage primaryStage)
    {
        initialWindowWidth = Engine.getUnits().width() * Engine.getUnits().refUnit();
        initialWindowHeight = Engine.getUnits().height() * Engine.getUnits().refUnit();

        windowWidth = initialWindowWidth;
        windowHeight = initialWindowHeight;

        Pane root = new StackPane();
        //#region components
        Scene scene = new Scene(root, windowWidth, windowHeight, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FOPEngine");

        StackPane pane = new StackPane();
        root.getChildren().add(pane);

        for (int i = 0; i < layers.length; i++)
            layers[i] = new Pane();
        pane.getChildren().addAll(layers);

        primaryStage.show();

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

        new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                updateObjects(true);
            }
        }.start();

        onLoadRunnable.run();
        getCurrentGraphicsPipeline();
    }


    CopyOnWriteArrayList<GameObject> gameObjects = new CopyOnWriteArrayList<>();
    HashMap<Integer, Node> objectComps = new HashMap<>();
    void addGameObject(GameObject gameObject, Node rootNode)
    {
        Platform.runLater(() ->
        {
            layers[gameObject.getZIndex()].getChildren().add(rootNode);
            objectComps.put(gameObject.getId(), rootNode);
            gameObjects.add(gameObject);
        });
    }

    private void resize()
    {
        Engine.getUnits().scale().set(Math.min(
            windowWidth / initialWindowWidth,
            windowHeight / initialWindowHeight));

        updateObjects(false);
    }

    private void updateObjects(boolean checkUpdate)
    {
        for (GameObject gameObject : gameObjects)
        {
            if(gameObject.needsUpdate() || !checkUpdate)
            {
                Vec2 worldPos = gameObject.getWorldCoords();
                objectComps.get(gameObject.getId()).relocate(
                        worldPos.x * Engine.getUnits().refUnit() * Engine.getUnits().scale().get(),
                        worldPos.y * Engine.getUnits().refUnit() * Engine.getUnits().scale().get());
                objectComps.get(gameObject.getId()).setRotate(gameObject.getRotation());
            }
        }
    }

    private static void getCurrentGraphicsPipeline()
    {
        String msg = GraphicsPipeline.getPipeline().getClass().getName();

        if(msg.equals("com.sun.prism.d3d.D3DPipeline"))
            System.out.println("Note: Using Direct3D pipeline.");

        else
            System.out.println("Note: Using " + msg + " pipeline.");
    }
}

