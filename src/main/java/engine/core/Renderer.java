package engine.core;

import com.sun.prism.GraphicsPipeline;
import engine.logging.LogCategory;
import engine.logging.LogPriority;
import engine.logging.Logger;
import engine.logging.LoggerIdentifier;
import engine.rendering.VisualComponent;
import engine.ui.UIElement;
import engine.util.Identifier;
import engine.util.Namespace;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CopyOnWriteArrayList;

public final class Renderer extends Application
{
    public final static Namespace RENDERER_NAMESPACE = new Namespace("renderer");
    public final static LoggerIdentifier LOGGER_ID = new LoggerIdentifier(RENDERER_NAMESPACE, "logger");
    private final Logger logger = new Logger(LOGGER_ID);
    public static final int Z_INDEX_LIMIT_LAYER = 20;
    public static final int Z_INDEX_LIMIT_UI = 10;

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

    @SuppressWarnings("unchecked")
    private final CopyOnWriteArrayList<VisualComponent>[] visualComponentLayers = new CopyOnWriteArrayList[Z_INDEX_LIMIT_LAYER];

    private final Pane[] layers = new Pane[Z_INDEX_LIMIT_LAYER + 1];
    private final Pane[] uiLayers = new Pane[Z_INDEX_LIMIT_LAYER + 1];
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

    private static Config config;
    static void loadConfig(Config config)
    {
        if(Renderer.config == null)
            Renderer.config = config;
    }



    private final Settings renderSettings = new Settings();


    //#region events
    @Override
    public void start(@NotNull Stage primaryStage)
    {
        if(config == null)
            throw new IllegalStateException("Missing config");

        renderSettings.unitSize = config.unitSize;

        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();

        if(config.scaleMonitor)
            renderSettings.unitSize *= (int) Math.min((screenHeight / 1080), (screenWidth / 1920));

        initialWindowWidth = config.unitAsReference ? config.windowWidth * renderSettings.unitSize : config.windowWidth;
        initialWindowHeight = config.unitAsReference ? config.windowHeight * renderSettings.unitSize : config.windowHeight;
        windowWidth = initialWindowWidth;
        windowHeight = initialWindowHeight;

        for (int i = 0; i < visualComponentLayers.length; i++)
            visualComponentLayers[i] = new CopyOnWriteArrayList<>();

        Pane root = new Pane();
        //#region components
        Scene scene = new Scene(root, windowWidth, windowHeight, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FOPEngine");

        StackPane pane = new StackPane();
        root.getChildren().add(pane);

        for (int i = 0; i < layers.length; i++)
            layers[i] = new Pane();
        pane.getChildren().addAll(layers);

        for (int i = 0; i < uiLayers.length; i++)
            uiLayers[i] = new Pane();
        pane.getChildren().addAll(uiLayers);

        primaryStage.show();

        final Canvas canvas = new Canvas(windowWidth, windowHeight);

        scene.widthProperty().addListener((observable, oldValue, newValue) ->
        {
            windowWidth = newValue.intValue();
            resize();

            canvas.setWidth(windowWidth);
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) ->
        {
            windowHeight = newValue.intValue();
            resize();

            canvas.setHeight(windowHeight);
        });


        root.getChildren().add(canvas);


        new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
                graphicsContext.clearRect(0,0,windowWidth, windowHeight);

                /*
                for (CopyOnWriteArrayList<VisualComponent> visualComponentLayer : visualComponentLayers)
                    for (VisualComponent visualComponent : visualComponentLayer)
                        visualComponent.render(graphicsContext, scaler);

                 */
            }
        }.start();


        getCurrentGraphicsPipeline();
        Engine.getInstance().setRenderer(this);
        onLoadRunnable.run();

        logger.log(getCurrentGraphicsPipeline(), LogPriority.HIGH, LogCategory.INFO);
    }

    void addVisualComponent(VisualComponent visualComponent, int zLayer)
    {
        visualComponentLayers[zLayer].add(visualComponent);
    }

    void addUIElement(UIElement uiElement)
    {
        Platform.runLater(() ->
        {

        });
    }

    private void resize()
    {
        double scaleWidth = windowWidth / initialWindowWidth;
        double scaleHeight = windowHeight / initialWindowHeight;

        //scaler = new Scaler(Engine.scalingMode, Engine.units.refUnit, scaleWidth, scaleHeight, scaleWidth > scaleHeight);
    }

    private static String getCurrentGraphicsPipeline()
    {
        String msg = GraphicsPipeline.getPipeline().getClass().getName();

        if(msg.equals("com.sun.prism.d3d.D3DPipeline"))
            return "Note: Using Direct3D pipeline.";

        else
            return "Note: Using " + msg + " pipeline.";
    }
}

