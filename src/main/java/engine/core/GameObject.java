package engine.core;

import engine.identification.Identifier;
import engine.rendering.*;
import engine.util.RenderData;
import javafx.scene.canvas.GraphicsContext;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GameObject
{
    private int layer = 0;
    private final HashSet<Identifier> tags = new HashSet<>();
    protected final Transform2D transform;
    protected RenderComponent renderComponent;
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
    public RenderComponent getCanvasRenderer()
    {
        return renderComponent;
    }
    public Transform2D getTransform()
    {
        return transform;
    }
    public void setParent(GameObject gameObject)
    {
        transform.setParent(gameObject.getTransform());
    }
    @SuppressWarnings("unchecked")
    public <T extends RenderComponent> T createRenderComponent(Class<T> type)
    {
        if(renderComponent != null)
            throw new IllegalStateException("GameObject already has a renderComponent.");

        T component = null;
        if(type.equals(SpriteRenderer.class))
            component = (T) new SpriteRenderer(transform);
        else if(type.equals(ShapeRenderer.class))
            component =  (T) new ShapeRenderer(transform);
        else if(type.equals(CustomRenderer.class))
            component =  (T) new CustomRenderer(transform);
        else if(type.equals(TextRenderer.class))
            component =  (T) new TextRenderer(transform);

        if(component == null)
            throw new IllegalArgumentException("No component for class: " + type.getName());

        renderComponent = component;
        return component;
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