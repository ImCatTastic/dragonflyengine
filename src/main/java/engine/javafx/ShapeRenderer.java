package engine.javafx;

import engine.javafx.shapePainter.PaintableShape;
import engine.javafx.shapePainter.ShapePainter;
import engine.mathUtil.Vec2;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public non-sealed class ShapeRenderer extends CanvasRenderer
{
    private final ArrayList<PaintableShape> paintableShapes = new ArrayList<>();
    public ShapeRenderer(double width, double height)
    {
        setDimensions(new Vec2(width, height).mult(Camera.getUnit()));
    }
    public void addShape(PaintableShape paintableShape)
    {
        paintableShapes.add(paintableShape);
    }
    @Override
    public final void render(GraphicsContext gc)
    {
        for (PaintableShape paintableShape : paintableShapes)
            paintableShape.draw(gc, this);
    }
}
