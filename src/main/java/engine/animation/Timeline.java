package engine.animation;

import engine.util.Interpolator;

public class Timeline extends Animation
{
    public Timeline(double duration, boolean reverse, Interpolator interpolator)
    {
        super(duration, reverse, interpolator);
    }

    @Override
    protected void onUpdate()
    {

    }
}
