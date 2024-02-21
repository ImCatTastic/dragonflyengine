package engine.animation;

import engine.util.PropertyGetter;
import engine.util.PropertySetter;
import org.jetbrains.annotations.NotNull;

public final class DoubleTransition extends Transition<DoubleTransition, Double>
{
    DoubleTransition(
            PropertyGetter<Double> getFrom, PropertyGetter<Double> getDelta,
            PropertySetter<Double> propertySetter, double duration, boolean reverse)
    {
        super(getFrom,getDelta, propertySetter, duration, reverse);
    }
    @Override
    protected @NotNull Double calcFrame(@NotNull Double from, @NotNull Double delta, double normalizedProgress)
    {
        return from + delta * normalizedProgress;
    }
}