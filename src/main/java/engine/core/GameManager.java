package engine.core;

import javafx.scene.input.KeyCode;
import temp.learnBot.eventHandlers.KeyListener;
import temp.learnBot.eventHandlers.MouseListener;

import java.util.Set;

public abstract class GameManager
{
    private final Engine engine;
    public GameManager()
    {
        engine = Engine.getInstance();
        engine.setGameManager(this);
        addScene(new Scene(0));
        setActiveScene(0);
    }
    public void addMouseListener(MouseListener listener)
    {
        engine.getInputHandler().addMouseListener(listener);
    }
    public void addKeyListener(KeyListener listener)
    {
        engine.getInputHandler().addKeyListener(listener);
    }
    public Set<Key> getPressedKeys()
    {
        return null;
    }
    public Set<MouseButton> getPressedMouseButtons()
    {
        return null;
    }
    public final void run()
    {
        final Config config = new Config();
        init(config);
        engine.run(config.copy());
    }
    protected final void addScene(Scene scene)
    {
        engine.getSceneManager().addScene(scene);
    }
    protected final void setActiveScene(int index)
    {
        engine.getSceneManager().setActiveScene(index);
    }
    protected final Scene getActiveScene()
    {
        return engine.getSceneManager().getActiveScene();
    }

    protected abstract void init(Config config);
    protected abstract void onStart();
    protected abstract void onUpdate();
}
