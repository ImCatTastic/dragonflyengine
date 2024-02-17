package engine.javafx.shapePainter;

import engine.javafx.CanvasRenderer;
import engine.javafx.Alignment;
import engine.mathUtil.Vec2;
import javafx.scene.canvas.GraphicsContext;

public abstract class PaintableShape
{
    protected Vec2 position = new Vec2(0,0);
    protected Alignment alignment = Alignment.CENTER;
    public void setPosition(Vec2 position)
    {
        this.position = position;
    }
    public void setAlignment(Alignment alignment)
    {
        this.alignment = alignment;
    }
    public abstract void draw(GraphicsContext gc, CanvasRenderer renderer);
}
