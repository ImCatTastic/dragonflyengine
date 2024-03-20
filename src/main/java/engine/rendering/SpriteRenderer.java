package engine.rendering;

import engine.core.Transform2D;
import engine.identification.Identifier;
import engine.core.ResourceManager;
import engine.util.RenderData;
import engine.util.math.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public final class SpriteRenderer extends RenderComponent
{
    public SpriteRenderer(Transform2D transform)
    {
        super(transform);
        setDimensions(1, 1);
    }
    private Image image;
    public void setSprite(Identifier identifier)
    {
        this.image = ResourceManager.getSpriteById(identifier);
    }
    @Override
    public void render(GraphicsContext gc, RenderData data)
    {
        var x = data.pivotOffset.x;
        var y = data.pivotOffset.y;
        var w = getBoundingBox().width * data.unit;
        var h = getBoundingBox().height * data.unit;
        gc.drawImage(image, isFlipX() ? -w + x : -x, isFlipY() ? -h + y : -y, w, h);
    }
}