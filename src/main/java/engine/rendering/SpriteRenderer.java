package engine.rendering;

import engine.identification.Identifier;
import engine.core.ResourceManager;
import engine.util.RenderData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public final class SpriteRenderer extends CanvasRenderer
{
    private final Image image;
    public SpriteRenderer(Identifier identifier, double width, double height)
    {
        setDimensions(width, height);
        this.image = ResourceManager.getSpriteById(identifier);
    }
    @Override
    public void render(GraphicsContext gc, RenderData data)
    {
        var x = data.pivotOffset.x;
        var y = data.pivotOffset.y;
        var w = dimensions.x * data.unit;
        var h = dimensions.y * data.unit;
        gc.setFill(Color.GREEN);
        gc.drawImage(image, isFlipX() ? -w + x : -x, isFlipY() ? -h + y : -y, w, h);
    }
}