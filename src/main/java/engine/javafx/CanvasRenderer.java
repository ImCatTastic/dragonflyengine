package engine.javafx;

import engine.mathUtil.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public sealed abstract class CanvasRenderer permits CustomRenderer, ShapeRenderer, SpriteRenderer
{
    private Alignment pivotOrientation = Alignment.CENTER;
    private Vec2 pivotPoint;
    private Vec2 dimensions;
    private boolean flipX;
    private boolean flipY;
    private Transform2D objectTransform;
    private boolean dirty = true;
    private final Affine affine = new Affine();
    private final Affine ref = new Affine();
    protected void setDimensions(Vec2 dimensions)
    {
        this.dimensions = dimensions.mult(Screen.getUnitScaleMultiplier());
    }
    protected void setDimensions(double x, double y)
    {
        setDimensions(new Vec2(x, y));
    }
    public void setPivotOrientation(Alignment pivotOrientation)
    {
        this.pivotOrientation = pivotOrientation;
        dirty = true;
    }
    public Vec2 getDimensions()
    {
        return dimensions;
    }
    public Alignment getPivotOrientation()
    {
        return pivotOrientation;
    }
    public Vec2 getPivotPoint()
    {
        return pivotPoint;
    }
    public Transform2D getObjectTransform()
    {
        return objectTransform;
    }
    public void setFlipX(boolean flipX)
    {
        this.flipX = flipX;
    }
    public void setFlipY(boolean flipY)
    {
        this.flipY = flipY;
    }
    protected boolean isFlipX()
    {
        return flipX;
    }
    protected boolean isFlipY()
    {
        return flipY;
    }
    private void calcPivotPoint()
    {
        pivotPoint = new Vec2(
                dimensions.x * pivotOrientation.getX(),
                dimensions.y * pivotOrientation.getY()
        );
        dirty = true;
    }
    public boolean init(Transform2D transform2D)
    {
        if(dimensions == null)
            return false;//TODO: Log message

        this.objectTransform = transform2D;
        calcPivotPoint();

        return true;
    }
    public void doRender(GraphicsContext gc, Affine cameraAffine)
    {
        if(dirty || objectTransform.wasDirty())
        {
            calcPivotPoint();
            affine.setToTransform(objectTransform.getAffine());
            if(flipX || flipY) affine.appendScale(flipX ? -1 : 1, flipY ? -1 : 1);
            dirty = false;
        }

        ref.setToTransform(cameraAffine);
        ref.append(affine);
        gc.setTransform(ref);
        render(gc);
    }
    protected abstract void render(GraphicsContext gc);
}