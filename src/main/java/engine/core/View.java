package engine.core;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.HashMap;

public class View
{
    private final static View primary = new View(0, 0);
    public static View getPrimary()
    {
        return primary;
    }
    private final static HashMap<GameScene, ArrayList<View>> viewsByScenes = new HashMap<>();
    private final static ArrayList<View> usableViews = new ArrayList<>();
    public static void generateUsableViews()
    {
        usableViews.clear();
        usableViews.add(primary);

        var views = viewsByScenes.get(SceneManager.getActiveScene());
        if(views != null)
            usableViews.addAll(views);
    }
    public static ArrayList<View> getUsableViews()
    {
        return usableViews;
    }
    private final Canvas canvas;
    private double zoom = 1;
    private double width;
    private double height;
    private Camera activeCamera;
    public View(double width, double height)
    {
        this.width = width;
        this.height = height;
        this.canvas = new Canvas(width, height);
        Engine.getInstance().addFXCanvas(canvas);
    }
    public void resize(double width, double height)
    {
        this.width = width;
        this.height = height;
        canvas.setWidth(width);
        canvas.setHeight(height);
    }
    public void setWidth(double width)
    {
        resize(width, height);
    }
    public void setHeight(double height)
    {
        resize(width, height);
    }
    public void setZoom(double zoom)
    {
        this.zoom = zoom;
    }
    public void setActiveCamera(Camera activeCamera)
    {
        if(activeCamera.getTransform().getScene() != SceneManager.getActiveScene())
            throw new IllegalStateException("Scene Mismatch while trying to set active camera.");

        this.activeCamera = activeCamera;
    }
    public GraphicsContext getGraphicsContext()
    {
        return canvas.getGraphicsContext2D();
    }
    public double getWidth()
    {
        return width;
    }
    public double getHeight()
    {
        return height;
    }
    public double getZoom()
    {
        return zoom;
    }
    public Camera getActiveCamera()
    {
        return activeCamera;
    }
}