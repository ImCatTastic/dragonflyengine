package engine.core;

import engine.logging.LogHandler;
import engine.rendering.Renderer;
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
    private Renderer2 renderer;
    private final Renderer _renderer = new Renderer();
    private final SceneManager sceneManager = new SceneManager();
    private final MainLoop loop = new MainLoop();
    private final InputHandler inputHandler = new InputHandler();

    public void run(Config config)
    {
        if(running)
            throw new RuntimeException("Cannot call run twice!");

        _renderer.run(config, inputHandler);

        if(true)
            return;

        running = true;
        LogHandler.setStartDate();
        Renderer2.loadConfig(config);
        Renderer2.onLoadComplete(() ->
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

            loop.start(gameManager, null);
        });
        Application.launch(Renderer2.class);
    }

    public void start()
    {

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
    public void setRenderer(Renderer2 renderer)
    {
        if(this.renderer != null)
            throw new IllegalArgumentException("Renderer already set");

        this.renderer = renderer;
    }
}