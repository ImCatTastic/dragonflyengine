package engine.rendering;

import javafx.scene.image.Image;
import java.io.InputStream;

public class Sprite
{
    private final Image image;
    public Sprite(String path, ImageType imageType)
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("sprites/" + path + imageType);
        if (inputStream != null)
            image = new Image(inputStream);
        else
            image = null;
    }
    public Image getImage()
    {
        return image;
    }
}