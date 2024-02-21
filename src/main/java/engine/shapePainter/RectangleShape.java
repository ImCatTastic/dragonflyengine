package engine.shapePainter;

import engine.util.RenderData;
import engine.util.math.Vec2;
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
    public void draw(GraphicsContext gc, RenderData data, Vec2 dimensions)
    {
        var scaledX = x * data.unit;
        var scaledY = y * data.unit;
        var scaledW = width * data.unit;
        var scaledH = height * data.unit;

        if(fill != null)
        {
            gc.setFill(fill);
            gc.fillRect(scaledX - data.pivotOffset.x,scaledY - data.pivotOffset.y, scaledW, scaledH);
        }

        if(strokeThickness > 0)
        {
            gc.setStroke(stroke);
            gc.setLineWidth(strokeThickness);
            gc.strokeRect(scaledX - data.pivotOffset.x,scaledY - data.pivotOffset.y, scaledW, scaledH);
        }
    }
}