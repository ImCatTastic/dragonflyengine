package engine.javafx;

import engine.identification.Identifier;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Sprite
{
    private Image baseImage;
    public Sprite()
    {

    }
    public void load(Image image)
    {
        if(baseImage == null)
            baseImage = image;
    }
    public Image getBaseImage()
    {
        return baseImage;
    }

    /*
    public Image createVariant(int width, int height)
    {
        ImageView imageView = new ImageView(baseImage);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        var variant = imageView.snapshot(null, null);
        variants.add(variant);
        return imageView.snapshot(null, null);
    }

     */
}
