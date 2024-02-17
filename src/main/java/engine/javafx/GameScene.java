package engine.javafx;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GameScene implements Iterable<Transform2D>
{
    private final GameObject root = new GameObject(this);
    public GameScene()
    {
        SceneManager.addScene(this);
    }
    public void add(GameObject object)
    {
        object.getTransform().setParent(root.getTransform());
    }
    private boolean orderUpdate = false;
    void requestROrderUpdate()
    {
        orderUpdate = true;
    }
    boolean requireOrderUpdate()
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