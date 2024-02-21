package engine.shapePainter;

import engine.util.PivotPoint;
import engine.util.RenderData;
import engine.util.math.Vec2;
import javafx.scene.canvas.GraphicsContext;

public abstract class PaintableShape
{
    protected Vec2 position = new Vec2(0,0);
    protected PivotPoint pivotPoint = PivotPoint.CENTER;
    public void setPosition(Vec2 position)
    {
        this.position = position;
    }
    public void setAlignment(PivotPoint pivotPoint)
    {
        this.pivotPoint = pivotPoint;
    }
    public abstract void draw(GraphicsContext gc, RenderData renderData, Vec2 dimensions);
}
