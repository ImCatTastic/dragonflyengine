package engine.animation;

import engine.util.Interpolator;
import engine.util.PropertySetter;

public class DoubleTransition extends Transition<Double>
{
    public DoubleTransition(PropertySetter<Double> propertySetter, double duration, boolean reverse, Interpolator interpolator)
    {
        super(propertySetter, duration, reverse, interpolator);
    }
    public DoubleTransition(PropertySetter<Double> propertySetter, double duration, Interpolator interpolator)
    {
        super(propertySetter, duration, interpolator);
    }
    public DoubleTransition(PropertySetter<Double> propertySetter, double duration, boolean reverse)
    {
        super(propertySetter, duration, reverse);
    }
    public DoubleTransition(PropertySetter<Double> propertySetter, double duration)
    {
        super(propertySetter, duration);
    }
    @Override
    protected Double add(Double first, Double second)
    {
        return first + second;
    }
    @Override
    protected Double sub(Double first, Double second)
    {
        return first - second;
    }
    @Override
    protected Double calcFrame(Double from, Double delta, double normalizedProgress)
    {
        return from + delta * normalizedProgress;
    }
}