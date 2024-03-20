package engine.ui;

import engine.util.PivotPoint;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

public class Rotation2D
{
    private double angle = 0;
    private PivotPoint pivotPoint = PivotPoint.CENTER;
    private final DoubleProperty pivotX = new SimpleDoubleProperty(0.5);
    private final DoubleProperty pivotY = new SimpleDoubleProperty(0.5);
    public double getAngle()
    {
        return angle;
    }
    public PivotPoint getPivotPoint()
    {
        return pivotPoint;
    }
    public void setAngle(double value)
    {
        angle = value;
    }
    public void setPivotPoint(PivotPoint pivotPoint)
    {
        this.pivotPoint = pivotPoint;
        pivotX.set(pivotPoint.getX());
        pivotY.set(pivotPoint.getY());
    }
}
