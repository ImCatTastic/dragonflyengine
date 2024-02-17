package engine.javafx.shapePainter;

import engine.javafx.Camera;
import engine.javafx.CanvasRenderer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleShape extends PaintableShape
{
    private final double x;
    private final double y;
    private final double width;
    private final double height;
    private final double strokeThickness;
    private Color fill;
    private Color stroke;
    private final StrokeType strokeType;
    public RectangleShape(double x, double y, double width, double height, Color fill, double strokeThickness, Color stroke, StrokeType strokeType)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fill = fill;
        this.strokeThickness = strokeThickness;
        this.stroke = stroke;
        this.strokeType = strokeType;
    }
    public RectangleShape(double x, double y, double width, double height, Color fill, double strokeThickness, Color stroke)
    {
        this(x, y, width, height, fill, strokeThickness, stroke, StrokeType.CENTER);
    }
    public RectangleShape(double x, double y, double width, double height, Color fill)
    {
        this(x, y, width, height, fill, 0, null, null);
    }
    public void setFill(Color fill)
    {
        this.fill = fill;
    }
    public void setStroke(Color stroke)
    {
        this.stroke = stroke;
    }
    @Override
    public void draw(GraphicsContext gc, CanvasRenderer renderer)
    {
        var unit = Camera.getScaledUnit();
        var scaledX = x * unit;
        var scaledY = y * unit;
        var scaledW = width * unit;
        var scaledH = height * unit;

        var pivotPoint = renderer.getPivotPoint();

        if(fill != null)
        {
            gc.setFill(fill);
            gc.fillRect(scaledX - pivotPoint.x,scaledY - pivotPoint.y, scaledW, scaledH);
        }

        if(strokeThickness > 0)
        {
            gc.setStroke(stroke);
            gc.setLineWidth(strokeThickness);
            gc.strokeRect(scaledX - pivotPoint.x,scaledY - pivotPoint.y, scaledW, scaledH);
        }
    }
}