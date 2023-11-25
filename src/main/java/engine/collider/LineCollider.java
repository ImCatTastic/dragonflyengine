package engine.collider;

import javafx.scene.shape.Line;

public class LineCollider extends Collider2D
{
    public LineCollider(double x1, double x2, boolean horizontal)
    {
        super(new Line());
    }
}
