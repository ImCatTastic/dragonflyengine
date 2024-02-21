package engine.core;

import engine.ui.UICanvas;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GameScene implements Iterable<Transform2D>
{
    private final GameObject root = new GameObject(this);
    private final UICanvas uiCanvas;
    public GameScene()
    {
        var root = SceneManager.addScene(this);
        uiCanvas = new UICanvas(root);
    }
    public void addGameObject(GameObject object)
    {
        object.getTransform().setParent(root.getTransform());
    }
    public UICanvas getUiCanvas()
    {
        return uiCanvas;
    }
    public int getObjectCount()
    {
        return root.getTransform().size();
    }
    private boolean orderUpdate = false;
    void requestROrderUpdate()
    {
        orderUpdate = true;
    }
    public boolean requireOrderUpdate()
    {
        return orderUpdate && !(orderUpdate == false);
    }
    private boolean breadthFirstEnabled = false;
    public void useBreadthFirst()
    {
        breadthFirstEnabled = true;
    }
    @NotNull
    @Override
    public Iterator<Transform2D> iterator()
    {
        if(breadthFirstEnabled)
            root.getTransform().useBreadthFirst();
        return root.getTransform().iterator();
    }
}