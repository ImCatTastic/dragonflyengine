package engine.javafx;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

public class Camera extends GameObject
{
    private static double unit = -1;
    private static double scaledUnit = -1;
    public static void setUnit(int unit)
    {
        Camera.unit = unit;
        scaledUnit = unit * Screen.getUnitScaleMultiplier();
    }
    public static double getUnit()
    {
        return unit;
    }
    public static double getScaledUnit()
    {
        return scaledUnit;
    }
    private final double posX;
    private final double posY;
    private double width;
    private double height;
    private boolean doResize;
    private boolean dirty = true;
    private boolean enabled = true;
    private double zoom = 1;
    private Affine affine = new Affine();
    private double relativeHScale = 1;
    private double relativeVScale = 1;
    private double hFactor = 1;
    private double vFactor = 1;
    private final Canvas canvas = new Canvas();
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    public Camera(double posX, double posY, double width, double height, boolean useRelativeSize, boolean doResize)
    {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.doResize = doResize;

        if(useRelativeSize)
        {
            relativeHScale = width / Window.getDimensions().x;
            relativeVScale = height / Window.getDimensions().y;
        }

        //canvas.setTranslateX(posX);
        //canvas.setTranslateY(posY);
        canvas.setWidth(width);
        canvas.setHeight(height);
        Engine.getInstance().registerCamera(canvas);
    }
    public Camera(double width, double height, boolean useRelativeSize, boolean doResize)
    {
        this(0,0, width,height, useRelativeSize, doResize);
    }
    public Camera(double width, double height, boolean useRelativeSize)
    {
        this(0,0, width,height, useRelativeSize, true);
    }
    public Camera(double width, double height)
    {
        this(0,0, width,height,true,true);
    }
    public GraphicsContext getGraphicsContext()
    {
        return gc;
    }
    public boolean hasFixedSize()
    {
        return doResize;
    }
    public boolean isDirty()
    {
        return dirty && !(dirty = false);
    }
    public boolean isEnabled()
    {
        return enabled;
    }
    public double getPosX()
    {
        return posX;
    }
    public double getPosY()
    {
        return posY;
    }
    public double getWidth()
    {
        return width;
    }
    public double getHeight()
    {
        return height;
    }
    public Affine getViewMatrix()
    {
        return affine;
    }
    public void setScale(double hFactor, double vFactor)
    {
        this.hFactor = hFactor;
        this.vFactor = vFactor;
    }
    void requestResize()
    {
        doResize = true;
    }
    @Override
    public void update()
    {
        if(doResize)
        {
            width = Window.getDimensions().x;
            height = Window.getDimensions().y;
            canvas.setWidth(width);
            canvas.setHeight(height);
            doResize = false;
            dirty = true;
        }

        if(dirty)
        {
            affine.setToIdentity();
            affine.appendTranslation(width * 0.5, height * 0.5);
            affine.append(transform.getAffine());

            if(doResize)
                affine.appendScale(relativeHScale * zoom, relativeVScale * zoom);
            else
                affine.appendScale(zoom, zoom);

            dirty = false;
        }
    }
}