package engine.rendering;

import engine.core.GameObject;
import engine.mathUtil.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

public final class SpriteRenderer extends VisualComponent
{
    private WritableImage writableImage = new WritableImage(400, 300);
    private String path;

    public SpriteRenderer(GameObject gameObject, Vec2 dimensions)
    {
        super(gameObject, dimensions);
    }

    @Override
    public void onRender(GraphicsContext gc, Vec2 position, Vec2 dimensions)
    {

    }
}
