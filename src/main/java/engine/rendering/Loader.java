package engine.rendering;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

import java.io.IOException;
import java.io.InputStream;

public class Loader
{
    public static void loadImage(String fullPath)
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fullPath))
        {
            if(inputStream != null)
            {
                Image image = new Image(inputStream);

                PixelReader pixelReader = image.getPixelReader();

                int width = (int) image.getWidth();
                int height = (int) image.getHeight();
                WriteableImageView buffer = new WriteableImageView(width, height);

                for (int y = 0; y < height; y++)
                    for (int x = 0; x < width; x++)
                        buffer.setArgb(x, y, pixelReader.getArgb(x, y));

                int x = 0;
            }
        }

        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
