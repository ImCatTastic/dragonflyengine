package engine.core;

import engine.logging.LogHandler;
import javafx.application.Application;

public final class Engine
{
    private final static Engine instance = new Engine();
    static Engine getInstance()
    {
        return instance;
    }
    private Engine() {}

    private boolean running = false;
    private GameManager gameManager;
    private Renderer renderer;
    private final SceneManager sceneManager = new SceneManager();
    private final MainLoop loop = new MainLoop();
    private final InputHandler inputHandler = new InputHandler();
    private final AnimationHandler animationHandler = new AnimationHandler();

    public void run(Config config)
    {
        if(running)
            throw new RuntimeException("Cannot call run twice!");

        running = true;
        LogHandler.setStartDate();
        Renderer.loadConfig(config);
        Renderer.onLoadComplete(() ->
        {
            new Thread(() ->
            {
                while (true)
                {
                    LogHandler.update();

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();


            loop.start(gameManager, animationHandler);
        });
        Application.launch(Renderer.class);
    }

    public InputHandler getInputHandler()
    {
        return inputHandler;
    }

    public SceneManager getSceneManager()
    {
        return sceneManager;
    }

    public void setGameManager(GameManager gameManager)
    {
        if(this.gameManager != null)
            throw new IllegalArgumentException("GameEngine already set");

        this.gameManager = gameManager;
    }

    public void setRenderer(Renderer renderer)
    {
        if(this.renderer != null)
            throw new IllegalArgumentException("Renderer already set");

        this.renderer = renderer;
    }
}
