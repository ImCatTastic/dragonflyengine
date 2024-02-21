package temp.learnBot.gameobjects;

import engine.core.GameObject;
import engine.util.math.Vec2;
import engine.util.Interpolator;
import temp.learnBot.WorldManager;

public abstract class FOPGameObject extends GameObject
{
    public FOPGameObject(int x, int y, int z)
    {
        transform.setPosition(convertPoint(x, y));
        transform.setZ(z);
    }
    protected Vec2 convertPoint(int x, int y)
    {
        return WorldManager.getInstance().convertCoords(x, y);
    }
    protected Interpolator getCustomInterpolator(final double speed)
    {
        //blendFactor based on speed, such that animations don't look weird if the speed is too high
        double blendFactor = Math.exp(-0.2 * speed);

        return (t) ->
        {
            double easeInOut = 3 * t * t - 2 * t * t * t;
            return t + blendFactor * (easeInOut - t);
        };
    }
}
