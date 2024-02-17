package engine.core;

import engine.animation.Animation;
import engine.geometry.Transform2D;
import engine.physics.force.PhysicsComponent;
import engine.rendering.Sprite;
import engine.rendering.SpriteRenderer;
import javafx.application.Platform;
import javafx.scene.Group;
import engine.physics.collision.Collider2D;
import engine.mathUtil.Vec2;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.ArrayList;


public class GameObject
{
    private static int objectCount = 0;

    public static final byte FLAG_HIERARCHY = 0b00000001;
    public static final byte FLAG_TRANSFORM = 0b00000010;
    public static final byte FLAG_COLLIDER  = 0b00000100;
    public static final byte FLAG_RENDERER  = 0b00001000;
    public static final byte FLAG_PHYSICS   = 0b00010000;
    private byte dirtyFlags = 0b0;
    public byte getDirtyFlags()
    {
        return dirtyFlags;
    }







    private final int id;
    public int getId()
    {
        return id;
    }
    private GameObject parent;
    private Scene hostScene;
    protected final Transform2D transform;
    private SpriteRenderer spriteRenderer;
    private Collider2D collider;
    private PhysicsComponent physicsComponent;
    private final ArrayList<GameObject> children = new ArrayList<>();


    protected void setParent(GameObject parent)
    {
        if (this.parent != null)
            removeFromParent();

        this.parent = parent;
    }
    protected void removeFromParent()
    {
        this.parent.removeChild(this);
    }
    protected void removeChild(GameObject object)
    {
        children.remove(object);
    }
    public GameObject addChild(@NotNull GameObject gameObject)
    {
        gameObject.setParent(this);
        return gameObject;
    }
    protected void addCollider(Collider2D collider2D)
    {
        if(collider != null)
            return;

        this.collider = collider2D;
        //collider.updatePosition(transform.getPosition());
    }
    public final void createSpriteRenderer(Sprite sprite)
    {
        if(spriteRenderer == null)
        {
            spriteRenderer = new SpriteRenderer(sprite, transform);
            if(hostScene != null)
                hostScene.addSpriteRenderer(spriteRenderer, transform.getzIndex());
        }
    }
    public SpriteRenderer getSpriteRenderer()
    {
        return spriteRenderer;
    }
    public GameObject(float x, float y, int z, float rotation)
    {
        id = objectCount++;
        transform = new Transform2D(z);
        transform.setPosition(x, y);
        transform.setRotation(rotation);
    }
    public GameObject(float x, float y, float rotation)
    {
        this.id = objectCount++;
        transform = new Transform2D(0);
        transform.setPosition(x, y);
        transform.setRotation(rotation);
    }
    public GameObject(float x, float y)
    {
        this(x, y, 0);
    }
    public GameObject()
    {
        this(0, 0, 0);
    }
    public Transform2D getTransform()
    {
        return transform;
    }
    public Collider2D getCollider() {
        return collider;
    }
    public boolean hasCollider()
    {
        return collider != null;
    }
    public void setHost(Scene hostScene)
    {
        if(hostScene == null)
            this.hostScene = hostScene;
    }
    protected void queueAnimation(Animation<?> animation)
    {
        //Engine.getInstance().animationHandler.queueAnimation(animation);
    }
    public void queueAnimations(@NotNull Animation<?>... animations)
    {
        //Engine.getInstance().animationHandler.queueAnimations(animations);
    }
}