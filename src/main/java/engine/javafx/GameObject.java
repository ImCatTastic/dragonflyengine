package engine.javafx;

import engine.identification.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GameObject
{
    private int layer = 0;
    private final HashSet<Identifier> tags = new HashSet<>();
    protected final Transform2D transform;
    protected CanvasRenderer canvasRenderer;
    GameObject(@NotNull GameScene scene)
    {
        this.transform = new Transform2D(this, scene);
    }
    public GameObject()
    {
        this.transform = new Transform2D(this);
    }
    public int getLayer()
    {
        return layer;
    }
    public CanvasRenderer getCanvasRenderer()
    {
        return canvasRenderer;
    }
    public Transform2D getTransform()
    {
        return transform;
    }
    public void setParent(GameObject gameObject)
    {
        transform.setParent(gameObject.getTransform());
    }
    public void setParent(Transform2D transform)
    {
        transform.setParent(transform);
    }
    public void setCanvasRenderer(@NotNull CanvasRenderer canvasRenderer)
    {
        this.canvasRenderer = canvasRenderer;
        this.canvasRenderer.init(transform);
    }
    public void destroy()
    {
        transform.setParent(null);
    }
    public void addTag(Identifier tag)
    {
        tags.add(tag);
    }
    public void removeTag(Identifier tag)
    {
        tags.remove(tag);
    }
    public boolean containsTag(Identifier tag)
    {
        return tags.contains(tag);
    }
    public void setLayer(int layer)
    {
        this.layer = layer;
    }
    void performUpdate()
    {
        update();
        transform.update();
    }
    protected void start() {}
    protected void update() {}
}