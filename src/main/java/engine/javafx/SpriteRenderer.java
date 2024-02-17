package engine.javafx;

import engine.identification.Identifier;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import temp.learnBot.gameobjects.CoinGameobject;

public final class SpriteRenderer extends CanvasRenderer
{
    private final Image image;
    private final double finalWidth;
    private final double finalHeight;
    public SpriteRenderer(Identifier identifier)
    {
        this.image = ResourceManager.getSpriteById(identifier);
        finalWidth = image.getWidth() * Screen.getUnitScaleMultiplier();
        finalHeight = image.getHeight() * Screen.getUnitScaleMultiplier();
        setDimensions(image.getWidth(), image.getHeight());
    }
    public void render(GraphicsContext gc)
    {
        var x = getPivotPoint().x;
        var y = getPivotPoint().y;
        gc.drawImage(image, isFlipX() ? -finalWidth + x : -x, isFlipY() ? -finalHeight + y : -y, finalWidth, finalHeight);
    }
}