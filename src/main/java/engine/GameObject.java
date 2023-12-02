package engine;

import engine.animation.Animation;
import javafx.application.Platform;
import javafx.scene.Group;
import engine.collider.Collider2D;
import engine.mathUtil.Vec2;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;


public class GameObject
{
    private static int objectCount = 0;
    private final int id;
    public int getId() {
        return id;
    }

    protected final Transform2D transform;
    public final int zIndex;
    private Collider2D collider;
    private GameObject parent;
    protected final Group rootNode = new Group();

    private boolean needsUpdate = true;
    public boolean needsUpdate()
    {
        return needsUpdate;
    }
    public void update()
    {
        needsUpdate = false;
    }
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
        collider.updatePosition(transform.position);
        Platform.runLater(() -> rootNode.getChildren().add(collider2D.getNode()));
    }

    public GameObject(double x, double y, double rotation)
    {
        this.id = objectCount++;

        this.transform = new Transform2D(new Vec2(x, y), new Vec2(1,1), rotation);
        this.zIndex = 0;

        Engine.registerGameObject(this, rootNode);
    }
    public GameObject(double x, double y)
    {
        this(x, y, 0);
    }

    public GameObject()
    {
        this(0, 0, 0);
    }

    protected void addShape(Shape shape)
    {
        Platform.runLater(() -> rootNode.getChildren().add(shape));
    }
    public void hide()
    {
        Platform.runLater(() -> rootNode.setVisible(false));
    }
    public void reveal()
    {
        Platform.runLater(() -> rootNode.setVisible(true));
    }
    boolean hasUI = false;
    protected void setUI(Pane ui)
    {
        if(!hasUI)
        {
            rootNode.getChildren().add(ui);
            hasUI = true;
        }
    }
    public final Vec2 getWorldCoords()
    {
        final Vec2 pos = new Vec2(transform.position);

        if(parent != null)
            pos.add(parent.getWorldCoords());

        return pos;
    }
    public void setPosition(Vec2 newPosition)
    {
        transform.position.x = newPosition.x;
        transform.position.y = newPosition.y;
        requestUpdate();
    }
    public void setPosition(double x, double y)
    {
        transform.position.x = x;
        transform.position.y = y;
        requestUpdate();
    }
    public void setX(double x)
    {
        transform.position.x = x;
        requestUpdate();
    }
    public void incX(double x)
    {
        transform.position.x += x;
        requestUpdate();
    }
    public double getLocalX()
    {
        return transform.position.x;
    }
    public double getWorldX()
    {
        return getWorldCoords().x;
    }
    public void setY(double y)
    {
        transform.position.y = y;
        requestUpdate();
    }
    public void incY(double y)
    {
        transform.position.y += y;
        requestUpdate();
    }
    public double getLocalY()
    {
        return transform.position.y;
    }
    public double getWorldY()
    {
        return getWorldCoords().y;
    }
    public void setRotation(double rotation)
    {
        transform.rotation = rotation;
    }
    public double getRotation()
    {
        return transform.rotation;
    }
    public void setScale(double scale)
    {
        transform.scale.x = scale;
        transform.scale.y = scale;
    }
    public void setScaleX(double scaleX)
    {
        transform.scale.x = scaleX;
    }
    public double getScaleX()
    {
        return transform.scale.x;
    }
    public void setScaleY(double scaleY)
    {
        transform.scale.y = scaleY;
    }
    public double getScaleY()
    {
        return transform.scale.y;
    }
    private void requestUpdate()
    {
        needsUpdate = true;
        if(hasCollider())
            collider.updatePosition(transform.position);
    }
    public Collider2D getCollider() {
        return collider;
    }
    public boolean hasCollider()
    {
        return collider != null;
    }
    public int getZIndex()
    {
        return zIndex;
    }
    protected void queueAnimation(Animation animation)
    {
        Engine.animationHandler.queueAnimation(animation);
    }
    public void queueAnimations(@NotNull Animation... animations)
    {
        Engine.animationHandler.queueAnimations(animations);
    }
}