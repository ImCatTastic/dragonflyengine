package engine.rendering;

import engine.util.PivotPoint;
import engine.util.RenderData;
import engine.util.math.Vec2;
import javafx.scene.canvas.GraphicsContext;

public sealed abstract class CanvasRenderer permits CustomRenderer, ShapeRenderer, SpriteRenderer
{
    private PivotPoint pivotPoint = PivotPoint.CENTER;
    protected Vec2 dimensions;
    private boolean flipX;
    private boolean flipY;
    private boolean raycastTarget = true;
    protected void setDimensions(Vec2 dimensions)
    {
        if(this.dimensions != null)
            throw new IllegalStateException("dimensions already defined");

        this.dimensions = dimensions;
    }
    protected void setDimensions(double x, double y)
    {
        setDimensions(new Vec2(x, y));
    }
    public void setPivotPoint(PivotPoint pivotPointOrientation)
    {
        this.pivotPoint = pivotPointOrientation;
    }
    public void setIsRaycastTarget(boolean value)
    {
        this.raycastTarget = value;
    }
    public Vec2 getDimensions()
    {
        return dimensions;
    }
    public PivotPoint getPivotPoint()
    {
        return pivotPoint;
    }
    protected boolean isFlipX()
    {
        return flipX;
    }
    protected boolean isFlipY()
    {
        return flipY;
    }
    public boolean isRaycastTarget()
    {
        return raycastTarget;
    }
    public void setFlipX(boolean flipX)
    {
        this.flipX = flipX;
    }
    public void setFlipY(boolean flipY)
    {
        this.flipY = flipY;
    }
    protected abstract void render(GraphicsContext gc, RenderData renderData);
}