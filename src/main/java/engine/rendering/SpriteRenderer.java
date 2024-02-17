package engine.rendering;

import engine.geometry.Transform2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

import java.io.InputStream;

public final class SpriteRenderer
{
    private Sprite sprite;
    private Color color;
    private boolean flipX;
    private boolean flipY;
    private FillMode fillModeX; //TODO: implement
    private FillMode fillModeY; //TODO: implement
    private final Transform2D objectTransform;
    private Affine affine = new Affine();
    private boolean dirty = true;


    Image image;
    public SpriteRenderer(Sprite sprite, Transform2D transform2D)
    {
        this.sprite = sprite;
        this.objectTransform = transform2D;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("sprites/pickachu.jpg");
        image = new Image(inputStream);
    }
    public void markDirty()
    {
        dirty = true;
    }
    public void setColor(Color color)
    {
        this.color = color;
        dirty = true;
    }
    public void setFillMode(FillMode fillMode)
    {
        this.fillModeX = fillMode;
        this.fillModeY = fillMode;
        dirty = true;
    }
    public void setFillModeX(FillMode fillModeX)
    {
        this.fillModeX = fillModeX;
        dirty = true;
    }
    public void setFillModeY(FillMode fillModeY)
    {
        this.fillModeY = fillModeY;
        dirty = true;
    }
    public void setFlipX(boolean flipX)
    {
        this.flipX = flipX;
        dirty = true;
    }
    public void setFlipY(boolean flipY)
    {
        this.flipY = flipY;
        dirty = true;
    }
    private void createAffine()
    {
        double x = objectTransform.getPosition().x + sprite.getImage().getWidth() * objectTransform.getScale().x * (flipX ? -1 : 1) / 2;
        double y = objectTransform.getPosition().y + sprite.getImage().getHeight() * objectTransform.getScale().y * (flipY ? -1 : 1) / 2;

        affine = new Affine();
        affine.appendTranslation(x, y);
        affine.appendRotation(objectTransform.getRotation());
        affine.appendScale(objectTransform.getScale().x, objectTransform.getScale().y);
    }
    public void render(GraphicsContext gc)
    {
        if(dirty)
        {
            createAffine();
            dirty = false;
        }

        gc.setTransform(affine);
        gc.drawImage(image, -image.getWidth() / 2, -image.getHeight() / 2);
    }
}
