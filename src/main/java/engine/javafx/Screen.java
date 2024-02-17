package engine.javafx;

import engine.mathUtil.Vec2;

public class Screen
{
    private static Vec2 actualDimensions;
    private static double actualDpi;
    private static Vec2 dimensions;
    private static double dpi;
    private static double aspectRatio;
    private static double unitScaleMultiplier;
    static void init()
    {
        var primaryScreen = javafx.stage.Screen.getPrimary();
        dimensions = new Vec2(primaryScreen.getBounds().getWidth(), primaryScreen.getBounds().getHeight());
        actualDimensions = dimensions.mult(Math.round(dimensions.x * primaryScreen.getOutputScaleX()), Math.round(dimensions.y * primaryScreen.getOutputScaleY()));
        dpi = primaryScreen.getDpi();
        actualDpi = Math.round(dpi * primaryScreen.getOutputScaleX()); //just estimation
        aspectRatio = dimensions.x / dimensions.y;
        unitScaleMultiplier = Math.min(dimensions.x / 1920, dimensions.y / 1080);
    }

    public static Vec2 getActualDimensions()
    {
        return actualDimensions;
    }
    public static Vec2 getDimensions()
    {
        return dimensions;
    }
    public static double getActualDpi()
    {
        return actualDpi;
    }
    public static double getDpi()
    {
        return dpi;
    }
    public static double getAspectRatio()
    {
        return aspectRatio;
    }
    public static double getUnitScaleMultiplier()
    {
        return unitScaleMultiplier;
    }
}
