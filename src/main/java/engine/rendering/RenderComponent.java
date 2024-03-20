package engine.rendering;

import engine.core.BoundingBox;
import engine.core.GameObject;
import engine.core.Transform2D;
import engine.util.PivotPoint;
import engine.util.RenderData;
import engine.util.math.Vec2;
import javafx.scene.canvas.GraphicsContext;

public sealed abstract class RenderComponent permits CustomRenderer, ShapeRenderer, SpriteRenderer, TextRenderer
{
    private final Transform2D transform;
    private PivotPoint pivotPoint = PivotPoint.CENTER;
    protected BoundingBox boundingBox = new BoundingBox(0,0,0,0);
    private boolean flipX;
    private boolean flipY;
    public RenderComponent(Transform2D transform)
    {
        this.transform = transform;
    }
    public void setPivotPoint(PivotPoint pivotPoint)
    {
        this.pivotPoint = pivotPoint;
        calcBounds(new Vec2(boundingBox.width, boundingBox.height));
    }
    public void setDimensions(Vec2 dimensions)
    {
        calcBounds(dimensions);
    }
    public void setDimensions(double x, double y)
    {
        setDimensions(new Vec2(x, y));
    }
    private void calcBounds(Vec2 dimensions)
    {
        var pos = new Vec2(
                transform.getPosition().x - dimensions.x * pivotPoint.getX(),
                transform.getPosition().y - dimensions.y * pivotPoint.getY()
        );
        boundingBox = new BoundingBox(pos.x, pos.y, dimensions.x, dimensions.y);
    }
    public BoundingBox getBoundingBox()
    {
        return boundingBox;
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