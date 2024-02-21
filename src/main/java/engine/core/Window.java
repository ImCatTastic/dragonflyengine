package engine.core;

import engine.identification.Identifier;
import engine.logging.Log;
import engine.ui.shape.Rectangle2D;
import engine.util.Config;
import engine.util.math.Vec2;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;

public class Window
{
    public final static Identifier LOG_ID = new Identifier("window");
    private static boolean init = false;
    private static Vec2 screenOffset = new Vec2();
    private static Vec2 minSize = null;
    private static Vec2 initialDimensions;
    private static Vec2 dimensions;
    private static Stage stage;
    private static Scene scene;
    private final static HashMap<GameScene, Pane> scenes = new HashMap<>();
    static void create(Stage stage, Pane root, Config config)
    {
        if(init)
        {
            Log.error(LOG_ID, "Attempted to call create() again");
            return;
        }

        Log.info(LOG_ID, "Creating window with values: <width: " + config.windowWidth + "> | <height: " + config.windowHeight + ">");

        init = true;
        Window.stage = stage;
        scene = new Scene(root, config.windowWidth, config.windowHeight, config.windowBackgroundColor);

        initialDimensions = new Vec2(config.windowWidth, config.windowHeight);
        dimensions = new Vec2(initialDimensions);

        stage.setScene(scene);
        stage.setTitle(config.windowTitle);
        stage.setFullScreen(config.startFullscreen);
        initEvents();
    }
    public static void display()
    {
        stage.show();
        Log.info(LOG_ID, "Window is now being displayed.");
    }
    static Pane registerScene(GameScene scene, Pane pane)
    {
        scenes.put(scene, pane);
        return pane;
    }
    static void setScene(GameScene gameScene)
    {
        scenes.forEach((x, y) -> y.setVisible(x == gameScene));
    }
    private static void initEvents()
    {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> Input.activateKey(event.getCode()));
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (event) -> Input.deactivateKey(event.getCode()));

        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> Input.activateMouseButton(event.getButton()));
        scene.addEventHandler(MouseEvent.MOUSE_RELEASED, (event) -> Input.deactivateMouseButton(event.getButton()));

        scene.addEventHandler(MouseEvent.MOUSE_MOVED, (event) -> Input.moveMouse(event.getX(), event.getY()));
        scene.addEventHandler(ScrollEvent.SCROLL, (event) -> Input.increaseScrollCounter(event.getDeltaY()));

        scene.widthProperty().addListener((observable, oldValue, newValue) ->
        {
            dimensions = new Vec2(newValue.doubleValue(), dimensions.y);
            View.getPrimary().resize(dimensions.x, dimensions.y);
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) ->
        {
            dimensions = new Vec2(dimensions.x, newValue.doubleValue());
            View.getPrimary().resize(dimensions.x, dimensions.y);
        });
    }
    public static Vec2 getScreenPosition()
    {
        return initialDimensions;
    }
    public static Vec2 getInitialDimensions()
    {
        return initialDimensions;
    }
    public static Vec2 getDimensions()
    {
        return dimensions;
    }
}
