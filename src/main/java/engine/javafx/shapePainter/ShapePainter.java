package engine.javafx.shapePainter;

import engine.javafx.Camera;
import javafx.scene.paint.Color;

public class ShapePainter
{
    public void paintRectangle(double x, double y, double width, double height, Color color)
    {

    }

    protected double scale(double v)
    {
        return v * Camera.getUnit();
    }
}
