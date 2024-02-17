package engine.javafx;

import engine.core.Config;
import engine.core.InputHandler;
import engine.core.Key;
import engine.core.MouseButton;
import engine.mathUtil.Vec2;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import temp.learnBot.eventHandlers.KeyListener;
import temp.learnBot.eventHandlers.MouseListener;

import java.util.HashSet;
import java.util.Set;

public abstract class GameManager
{
    public abstract void init(Config config);
    public abstract void start();
    public abstract void update();
    public void resize(Vec2 oldDimensions, Vec2 newDimensions)
    {

    }
}