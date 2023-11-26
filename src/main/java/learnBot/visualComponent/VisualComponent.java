package learnBot.visualComponent;

import engine.GameObject;
import engine.UpdateableGameObject;
import learnBot.Config;
import learnBot.World;

import java.util.function.Function;

public abstract class VisualComponent extends UpdateableGameObject
{
    private double xOffset;
    private double yOffset;
    public VisualComponent(int x, int y, double xOffset, double yOffset)
    {
        super(0,0);
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        setX(getOffsetX(x));
        setY(getOffsetY(y));
    }
    public double getOffsetX(double x)
    {
        return x + Config.borderSize * x + xOffset;
    }
    public double getOffsetY(double y)
    {
        return World.getHeight() - 1 - y + Config.borderSize * (World.getHeight() - 1 - y) + yOffset;
    }

    protected Function<Double, Double> getCustomInterpolator(final double speed)
    {
        double res = Math.max(0, Math.min(World.speedLimit, speed));
        double blendFactor = Math.exp(-0.2 * res);

        return (t) ->
        {
            double linear = t;
            double easeInOut = 3 * t * t - 2 * t * t * t;

            return linear + blendFactor * (easeInOut - linear);
        };
    }
}
