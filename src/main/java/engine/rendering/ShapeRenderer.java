package engine.rendering;

import engine.shapePainter.PaintableShape;
import engine.util.RenderData;
import engine.util.math.Vec2;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public non-sealed class ShapeRenderer extends CanvasRenderer
{
    private final ArrayList<PaintableShape> paintableShapes = new ArrayList<>();
    public ShapeRenderer(double width, double height)
    {
        setDimensions(new Vec2(width, height));
    }
    public void addShape(PaintableShape paintableShape)
    {
        paintableShapes.add(paintableShape);
    }
    @Override
    public final void render(GraphicsContext gc, RenderData renderData)
    {
        var d = dimensions.mult(renderData.unit);
        for (PaintableShape paintableShape : paintableShapes)
            paintableShape.draw(gc, renderData, d);
    }
}
