package engine.collider;

import javafx.scene.shape.Circle;
import engine.mathUtil.Vec2;

public class CircleCollider2D extends Collider2D
{
    private final Circle shape;
    private final double radius;

    public CircleCollider2D(Vec2 position, double radius)
    {
        super(new Circle(radius));
        this.radius = radius;
        this.shape = (Circle) getShape();
    }
}
