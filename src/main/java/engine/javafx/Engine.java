package engine.javafx;

import engine.core.Config;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Engine extends Application
{
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
    void registerCamera(Canvas canvas)
    {
        root.getChildren().add(canvas);
    }
    @Override
    public void start(Stage stage)
    {
        Screen.init();

        final var config = new Config();
        gameManager.init(config);
        Camera.setUnit(config.unitSize);
        ResourceManager.loadResources();

        Window.create(stage, root, config);
        gameManager.start();
        Window.display();
        GameLoop.start();
    }
}
