package engine.javafx.gameObject;

import engine.javafx.FitMode;
import engine.javafx.ScalingMode;
import engine.javafx.Window;
import engine.mathUtil.Vec4;
import javafx.scene.canvas.Canvas;

public class EmbeddedView
{
    private final Canvas canvas = new Canvas();
    private Vec4 bounds;
    private double hSize;
    private double vSize;
    private final FitMode fitMode;
    private boolean preserveRatio = true;
    private boolean fixedSize = true;
    public EmbeddedView(int x, int y, int width, int height, double size)
    {
        this(x, y, width, height, FitMode.HEIGHT, size, size);
    }
    public EmbeddedView(int x, int y, int width, int height, FitMode fitMode, double hSize, double vSize)
    {
        this.bounds = new Vec4(x, y, width, height);
        this.fitMode = fitMode;
        this.hSize = hSize;
        this.vSize = vSize;
    }
    public double scaledUnit()
    {
        var w = canvas.getWidth();
        var h = canvas.getHeight();
        var idealRatio = hSize / vSize;
        var aspectRatio = canvas.getWidth() / canvas.getHeight();

        return switch (fitMode)
        {
            case HEIGHT -> h / vSize;
            case WIDTH -> w / hSize;
            case SMALLER -> aspectRatio > idealRatio ? h / vSize : w / hSize;
            case LARGER -> aspectRatio < idealRatio ? h / vSize : w / hSize;
        };
    }
}