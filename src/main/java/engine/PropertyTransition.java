package engine;

import engine.animation.Animation;
import engine.util.PropertyHolder;

import java.util.function.Function;

public class PropertyTransition implements Animation
{
    PropertyHolder<Double> property;
    Double start;
    Double end;
    double duration;
    Function<Double, Double> interpolator;

    public PropertyTransition(PropertyHolder<Double> property, Double end, double duration, Function<Double, Double> interpolator)
    {
        this.property = property;
        this.start = property.get();
        this.end = end;
        this.duration = duration;
        this.interpolator = interpolator;
    }

    public PropertyTransition(PropertyHolder<Double> property, Double end, double duration)
    {
        this.property = property;
        this.start = property.get();
        this.end = end;
        this.duration = duration;
        this.interpolator = (t) -> t;
    }

    double timePassed = 0;
    public boolean update(double t)
    {
        timePassed = timePassed + t > 1 ? 1 : timePassed + t;
        property.set(property.get() + (end - property.get()) * interpolator.apply(t));
        return timePassed >= 1; //If animation complete remove from animationPlayer
    }
}
