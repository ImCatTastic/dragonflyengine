package engine;


import engine.util.PropertyHolder;
import engine.util.ShapeBuilder;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import engine.collider.Collider2D;
import engine.mathUtil.Vec2;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import learnBot.visualComponent.RobotVC;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;


public class GameObject
{
    private static int objectCount = 0;

    private final int id;

    public int getId() {
        return id;
    }

    private final Vec2 position = new Vec2(0,0);
    private double rotation = 0;
    public final int zIndex;
    private Collider2D collider;
    private GameObject parent;
    private final Group rootNode = new Group();

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
        collider.updatePosition(position);
        Platform.runLater(() -> rootNode.getChildren().add(collider2D.getNode()));
    }

    public GameObject(double x, double y, double rotation)
    {
        this.id = objectCount++;
        this.position.x = x;
        this.position.y = y;
        this.rotation = rotation;
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

    private void addShape(Shape shape)
    {
        Platform.runLater(() -> rootNode.getChildren().add(shape));
    }

    protected Rectangle addRectangle(double x, double y, double width, double height)
    {
        Rectangle rectangle = ShapeBuilder.createRectangle(width, height);
        rectangle.setX(x);
        rectangle.setY(y);
        addShape(rectangle);
        return rectangle;
    }



    protected Rectangle addRectangle(double x, double y)
    {
        return addRectangle(x, y, 1, 1);
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

    public Vec2 getWorldCoords()
    {
        double x = position.x;
        double y = position.y;

        if(parent != null)
        {
            Vec2 pos = parent.getWorldCoords();
            x += pos.x;
            y += pos.y;
        }

        return new Vec2(x,y);
    }

    public double getRotation() {
        return rotation;
    }

    public double getWorldX()
    {
        return getWorldCoords().x;
    }

    public double getWorldY()
    {
        return getWorldCoords().y;
    }
    private void updatePosition()
    {
        needsUpdate = true;
        if(hasCollider())
            collider.updatePosition(position);
    }
    public void setRotation(double rotation)
    {
        this.rotation = rotation;
        needsUpdate = true;
    }

    public void setX(double x)
    {
        position.x = x;
        updatePosition();
    }

    public void setY(double y)
    {
        position.y = y;
        updatePosition();
    }

    public void setPosition(Vec2 position)
    {
        setPosition(position.x, position.y);
    }

    public void setPosition(double x, double y)
    {
        position.x = x;
        position.y = y;
        updatePosition();
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
}
