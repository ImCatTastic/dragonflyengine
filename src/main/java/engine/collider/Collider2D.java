package engine.collider;

import javafx.scene.Node;
import javafx.scene.shape.Shape;
import engine.mathUtil.Vec2;

public class Collider2D
{
    private final Vec2 position = new Vec2(0,0);
    private final Shape shape;

    public Collider2D(Shape shape)
    {
        this.shape = shape;
    }

    protected double getX()
    {
        return position.x;
    }

    protected double getY()
    {
        return position.y;
    }

    public void updatePosition(Vec2 position)
    {
        this.position.x = position.x;
        this.position.y = position.y;
    }
    protected Shape getShape()
    {
        return shape;
    }
    public Node getNode()
    {
        return shape;
    }
}
