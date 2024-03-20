package engine.spriteBuilder;

import engine.core.BoundingBox;
import engine.util.Alignment;
import engine.util.PivotPoint;
import engine.util.RenderData;
import engine.util.math.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class PaintableShape
{
    private Paint fill = new Color(1, 0, 1, 1);
    private Paint stroke = null;
    private double strokeThickness = 0;
    protected boolean dirty = true;
    private StrokeType strokeType = StrokeType.CENTER;
    public void setFill(Paint fill)
    {
        this.fill = fill;
        dirty = true;
    }
    public void setStroke(Paint stroke)
    {
        this.stroke = stroke;
        dirty = true;
    }
    public void setStrokeThickness(double strokeThickness)
    {
        this.strokeThickness = strokeThickness;
        dirty = true;
    }
    public void setStrokeType(StrokeType strokeType)
    {
        this.strokeType = strokeType;
        dirty = true;
    }
    public Paint getFill()
    {
        return fill;
    }
    public Paint getStroke() {
        return stroke;
    }
    public double getStrokeThickness()
    {
        return strokeThickness;
    }
    public StrokeType getStrokeType()
    {
        return strokeType;
    }
    private Alignment alignment = Alignment.CENTER;
    protected BoundingBox boundingBox = new BoundingBox(-0.5,-0.5,1,1);
    public BoundingBox getBoundingBox()
    {
        return boundingBox;
    }
    public void setPosition(Vec2 position)
    {
        calcBoundingBox(position.x, position.y, boundingBox.width, boundingBox.height);
        dirty = true;
    }
    public void setAlignment(Alignment alignment)
    {
        this.alignment = alignment;
        calcBoundingBox(boundingBox.minX, boundingBox.minY, boundingBox.width, boundingBox.height);
        dirty = true;
    }
    public Alignment getAlignment()
    {
        return alignment;
    }
    private void calcBoundingBox(double x, double y, double width, double height)
    {
        boundingBox = new BoundingBox(
                x - width * alignment.x,
                y - height * alignment.y,
                width,
                height
        );
    }
    public abstract void draw(GraphicsContext gc, RenderData data, Vec2 dimensions);
}
