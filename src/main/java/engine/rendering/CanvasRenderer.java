package engine.rendering;

import engine.core.GameObject;
import engine.mathUtil.Vec2;
import javafx.scene.canvas.GraphicsContext;

public abstract class CanvasRenderer extends VisualComponent
{
    public CanvasRenderer(GameObject gameObject, Vec2 dimensions)
    {
        super(gameObject, dimensions);
    }
}
