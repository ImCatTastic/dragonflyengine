package engine.core;

import engine.identification.Identifier;
import engine.logging.Log;
import engine.util.Config;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Engine extends Application
{
    public final static Identifier LOG_ID = new Identifier("engine");
    private static Engine instance;
    private static GameManager gameManager;
    public Engine()
    {
        instance = this;
    }
    static Engine getInstance()
    {
        return instance;
    }
    public static GameManager getGameManager()
    {
        return gameManager;
    }
    private final Pane root = new Pane();
    public static void run(GameManager gameManager)
    {
        if(Engine.gameManager != null)
            throw new IllegalStateException("cannot run engine again.");

        Engine.gameManager = gameManager;
        Application.launch(Engine.class);
    }
    void addFXCanvas(Canvas canvas)
    {
        root.getChildren().add(canvas);
    }
    @Override
    public void start(Stage stage)
    {
        Log.info(LOG_ID, "Starting engine...");

        Screen.init();
        final var config = new Config();
        gameManager.init(config);
        SceneManager.init(root);
        engine.core.Application.init(config.version);
        ResourceManager.loadResources();
        View.getPrimary().resize(config.windowWidth, config.windowHeight);
        View.generateUsableViews();

        Window.create(stage, root, config);
        gameManager.start();

        Window.display();
        GameLoop.start();
    }
}
