package engine.rendering;

import engine.core.GameObject;
import engine.core.Scaler;
import engine.mathUtil.Vec2;
import javafx.scene.canvas.GraphicsContext;
import org.jetbrains.annotations.NotNull;

public abstract class VisualComponent
{
    protected final GameObject gameObject;
    protected final Vec2 dimensions;
    public VisualComponent(GameObject gameObject, Vec2 dimensions)
    {
        this.gameObject = gameObject;
        this.dimensions = dimensions;
    }
    private Vec2 center;
    public final void render(GraphicsContext gc, @NotNull Scaler scaler)
    {
        Vec2 p = scaler.apply(gameObject.getWorldCoords());
        Vec2 d = scaler.apply(this.dimensions);
        onRender(gc, p, d);
    }
    public abstract void onRender(GraphicsContext gc, Vec2 position, Vec2 dimensions);
}
