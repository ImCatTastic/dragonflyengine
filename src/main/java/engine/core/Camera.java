package engine.core;

import engine.util.FitMode;

public class Camera extends GameObject
{
    private double zoom = 1;
    private double hSize = 16;
    private double vSize = 9;
    private FitMode fitMode = FitMode.HEIGHT;
    public void setHorizontalSize(double size)
    {
        this.hSize = size;
    }
    public void setVerticalSize(double size)
    {
        this.vSize = size;
    }
    public void setFitMode(FitMode fitMode)
    {
        this.fitMode = fitMode;
    }
    public void setZoom(double zoom)
    {
        this.zoom = zoom;
    }
    public double getHorizontalSize()
    {
        return hSize;
    }
    public double getVerticalSize()
    {
        return vSize;
    }
    public double getZoom()
    {
        return zoom;
    }
    public double convertToUnit(double w, double h, double s)
    {
        var idealRatio = hSize / vSize;
        var aspectRatio = w / h;
        var mult = zoom * s;

        return switch (fitMode)
        {
            case HEIGHT -> h / vSize * mult;
            case WIDTH -> w / hSize * mult;
            case MIN -> aspectRatio > idealRatio ? h / vSize * mult : w / hSize * mult;
            case MAX -> aspectRatio < idealRatio ? h / vSize * mult : w / hSize * mult;
        };
    }
}