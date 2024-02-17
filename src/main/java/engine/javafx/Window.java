package engine.javafx;

import engine.core.Config;
import engine.javafx.gameObject.EmbeddedView;
import engine.mathUtil.Vec2;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Window
{
    private static boolean init = false;
    private static Vec2 screenOffset = new Vec2();
    private static Vec2 initialDimensions;
    private static Vec2 dimensions;
    private static Stage stage;
    private static Scene scene;
    private final static ArrayList<EmbeddedView> views = new ArrayList<>();
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
    static void create(Stage stage, Pane root, Config config)
    {
        if(init)
            throw new RuntimeException("Window already created");

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
            //scaleW = dimensions.x / initialDimensions.x;

            for (Camera camera : CameraManager.getAllCameras())
            {
                if(!camera.hasFixedSize())
                {
                    camera.requestResize();
                }
            }
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) ->
        {
            dimensions = new Vec2(dimensions.x, newValue.doubleValue());
            //scaleH = dimensions.y / initialDimensions.y;

            for (Camera camera : CameraManager.getAllCameras())
            {
                if(!camera.hasFixedSize())
                {
                    camera.requestResize();
                }
            }
        });
    }
}
