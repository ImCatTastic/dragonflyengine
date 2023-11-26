package learnBot.visualComponent;

import engine.GameObject;
import learnBot.Config;
import learnBot.World;

import java.util.function.Function;

public class VisualComponent extends GameObject
{
    private double xOffset;
    private double yOffset;
    public VisualComponent(int x, int y, double xOffset, double yOffset)
    {
        super(
                x + Config.borderSize * x + xOffset,
                World.getHeight() - 1 - y + Config.borderSize * (World.getHeight() - 1 - y) + yOffset
        );

        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    @Override
    public void setX(double x)
    {
        super.setX(x + Config.borderSize * x + xOffset);
    }
    @Override
    public void setY(double y)
    {
        super.setY(y + Config.borderSize * y + yOffset);
    }

    public void setExplicitX(double x)
    {
        super.setX(x);
    }
    public void setExplicitY(double y)
    {
        super.setY(y);
    }

    protected Function<Double, Double> getCustomInterpolator(final double blendFactor)
    {
        return (t) ->
        {
            double linear = t;
            double easeInOut = 3 * t * t - 2 * t * t * t;

            return linear + blendFactor * (easeInOut - linear);
        };
    }

    protected void pause(int duration)
    {
        try
        {
            Thread.sleep(duration);
        }

        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
