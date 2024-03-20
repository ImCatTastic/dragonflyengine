package engine.rendering;

import engine.core.Transform2D;
import engine.spriteBuilder.PaintableShape;
import engine.spriteBuilder.SpriteBuilder;
import engine.util.RenderData;
import engine.util.math.Vec2;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public non-sealed class ShapeRenderer extends RenderComponent
{
    public ShapeRenderer(Transform2D transform)
    {
        super(transform);
    }
    private boolean hasDimensions = false;
    @Override
    public void setDimensions(Vec2 dimensions)
    {
        super.setDimensions(dimensions);
        hasDimensions = true;
    }
    private final ArrayList<PaintableShape> paintableShapes = new ArrayList<>();
    public void addShape(PaintableShape paintableShape)
    {
        paintableShapes.add(paintableShape);
        if(!hasDimensions)
        {
            this.setDimensions(paintableShape.getBoundingBox().dimensions().div(SpriteBuilder.getModifier()));
        }
    }
    @Override
    public final void render(GraphicsContext gc, RenderData data)
    {
        var d = getBoundingBox().dimensions().mult(data.unit);
        for (PaintableShape paintableShape : paintableShapes)
            paintableShape.draw(gc, data, d);
    }
}