package engine.rendering;

import javafx.scene.image.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.nio.IntBuffer;

public class WriteableImageView extends ImageView
{
    private int[] rawInts;
    private int width;
    private int height;
    private IntBuffer buffer;
    private PixelBuffer<IntBuffer> pixelBuffer;

    public WriteableImageView(int width, int height)
    {
        this.width = width;
        this.height = height;

        buffer = IntBuffer.allocate(width * height);
        rawInts = buffer.array();

        pixelBuffer = new PixelBuffer<>(width, height, buffer, PixelFormat.getIntArgbPreInstance());

        setImage(new WritableImage(pixelBuffer));
    }

    public int[] getPixels()
    {
        return rawInts;
    }
    public void setPixels(int[] rawPixels)
    {
        System.arraycopy(rawPixels, 0, rawInts, 0, rawPixels.length);
    }
    public void setArgb(int x, int y, int colorARGB)
    {
        rawInts[(x % width) + (y * width)] = colorARGB;
    }

    public void updateBuffer()
    {
        pixelBuffer.updateBuffer(b -> null);
    }
}
