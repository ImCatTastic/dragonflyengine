package engine.core;

import engine.identification.Identifier;
import engine.logging.Log;
import engine.util.Config;
import engine.util.math.Vec2;
import javafx.beans.property.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public class Window
{
    public final static Identifier LOG_ID = new Identifier("window");
    private static boolean init = false;
    private static Vec2 screenOffset = new Vec2();
    private static Vec2 minSize = null;
    private static Vec2 initialDimensions;
    private final static ReadOnlyObjectWrapper<Vec2> dimensions = new ReadOnlyObjectWrapper<>();
    private static Stage stage;
    private static Scene scene;
    private final static HashMap<GameScene, Pane> scenes = new HashMap<>();

    private static final ReadOnlyDoubleWrapper pixelConversionFactor = new ReadOnlyDoubleWrapper(1.0);
    public static ReadOnlyDoubleProperty getPixelConversionFactorProperty()
    {
        return pixelConversionFactor.getReadOnlyProperty();
    }

    static void create(Stage stage, Pane root, Config config)
    {
        Rectangle2D bounds = javafx.stage.Screen.getPrimary().getBounds();

        if(bounds.getWidth() > bounds.getHeight())
            pixelConversionFactor.set(bounds.getHeight() / 1080);
        else
            pixelConversionFactor.set(bounds.getWidth() / 1920);

        if(init)
        {
            Log.error(LOG_ID, "Attempted to call create() again");
            return;
        }

        Log.info(LOG_ID, "Creating window with values: <width: " + config.windowWidth + "> | <height: " + config.windowHeight + ">");

        init = true;
        Window.stage = stage;
        scene = new Scene(root, config.windowWidth, config.windowHeight, config.windowBackgroundColor);

        scene.addEventHandler(KeyEvent.ANY, event ->
        {
            // Handle key events here
            System.out.println("Key pressed: " + event.getCode());
        });

        initialDimensions = new Vec2(config.windowWidth, config.windowHeight);
        dimensions.set(new Vec2(initialDimensions));

        stage.setScene(scene);
        stage.setTitle(config.windowTitle);
        stage.setFullScreen(config.startFullscreen);

        root.setBackground(null);
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
            var d = dimensions.get();
            dimensions.set(new Vec2(newValue.doubleValue(), d.y));
            View.getPrimary().resize(d.x, d.y);
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) ->
        {
            var d = dimensions.get();
            dimensions.set(new Vec2(d.x, newValue.doubleValue()));
            View.getPrimary().resize(d.x, d.y);
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
        return dimensions.get();
    }
    public static ReadOnlyObjectProperty<Vec2> getDimensionProperty()
    {
        return dimensions.getReadOnlyProperty();
    }
}
