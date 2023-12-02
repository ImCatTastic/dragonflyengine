package engine.animation;

import engine.util.Interpolator;

public class SpriteAnimation extends Animation
{
    public SpriteAnimation(double duration, boolean reverse, Interpolator interpolator)
    {
        super(duration, reverse, interpolator);
    }

    @Override
    protected void onUpdate()
    {

    }
}
