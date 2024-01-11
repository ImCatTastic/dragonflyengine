package engine.core;

import javafx.scene.input.KeyCode;
import learnBot.eventHandlers.KeyListener;
import learnBot.eventHandlers.MouseListener;

import java.util.HashSet;
import java.util.Set;

public abstract class GameManager
{
    private final Engine engine;
    public GameManager()
    {
        engine = Engine.getInstance();
        engine.setGameManager(this);
    }

    public void addMouseListener(MouseListener listener)
    {
        engine.getInputHandler().addMouseListener(listener);
    }
    public void addKeyListener(KeyListener listener)
    {
        engine.getInputHandler().addKeyListener(listener);
    }

    public Set<KeyCode> getKeysPressed()
    {
        return null;
    }

    public Set<MouseButton> getMouseButtonsPressed()
    {
        return null;
    }

    protected Config getConfig()
    {
        return new Config();
    }

    protected final void setUnit(int unitSize)
    {

    }

    protected final void setWidth(int width)
    {

    }

    protected final void setHeight(int height)
    {

    }

    protected final void setFullscreen(boolean enableFullscreen)
    {

    }

    protected final void start()
    {
        engine.run(getConfig());
    }

    protected final void addScene(Scene scene)
    {
        engine.getSceneManager().addScene(scene);
    }

    protected final void setScene(int index)
    {

    }

    protected final Scene getScene()
    {
        return engine.getSceneManager().getActiveScene();
    }

    protected abstract void onStart();
    protected abstract void onUpdate();
}
