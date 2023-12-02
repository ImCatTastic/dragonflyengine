package engine.animation;

import engine.util.Interpolator;
import engine.util.PropertyGetter;
import engine.util.PropertySetter;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class TransitionBuilder<T>
{
    protected T fromValue;
    protected T deltaValue;
    protected T toValue;
    protected PropertyGetter<T> dynamicFromValue;
    protected PropertyGetter<T> dynamicDeltaValue;
    protected PropertyGetter<T> dynamicToValue;
    protected PropertySetter<T> propertySetter;
    protected double duration;
    protected boolean reverse;
    protected Interpolator interpolator;
    protected Runnable onComplete;
    protected Runnable onHalfComplete;
    public TransitionBuilder<T> setDuration(double duration)
    {
        this.duration = duration;
        return this;
    }
    public TransitionBuilder<T> setPropertySetter(PropertySetter<T> propertySetter)
    {
        this.propertySetter = propertySetter;
        return this;
    }
    public TransitionBuilder<T> setReverse(boolean reverse)
    {
        this.reverse = reverse;
        return this;
    }
    public TransitionBuilder<T> setInterpolator(Interpolator interpolator)
    {
        this.interpolator = interpolator;
        return this;
    }
    public TransitionBuilder<T> setOnComplete(Runnable onComplete)
    {
        this.onComplete = onComplete;
        return this;
    }
    public TransitionBuilder<T> setOnHalfComplete(Runnable onHalfComplete)
    {
        this.onHalfComplete = onHalfComplete;
        return this;
    }
    public TransitionBuilder<T> setFrom(T fromValue)
    {
        this.fromValue = fromValue;
        return this;
    }
    public TransitionBuilder<T> setDelta(T byValue)
    {
        this.deltaValue = byValue;
        return this;
    }
    public TransitionBuilder<T> setTo(T toValue)
    {
        this.toValue = toValue;
        return this;
    }
    public TransitionBuilder<T> setFrom(PropertyGetter<T> fromValue)
    {
        this.dynamicFromValue = fromValue;
        return this;
    }
    public TransitionBuilder<T> setDelta(PropertyGetter<T> byValue)
    {
        this.dynamicDeltaValue = byValue;
        return this;
    }
    public TransitionBuilder<T> setTo(PropertyGetter<T> toValue)
    {
        this.dynamicToValue = toValue;
        return this;
    }
    public abstract Transition<T> build();
    public static class Double extends TransitionBuilder<java.lang.Double>
    {
        public DoubleTransition build()
        {
            return (DoubleTransition)
                    new DoubleTransition(propertySetter, duration, reverse)
                    .setFrom(fromValue)
                    .setDelta(deltaValue)
                    .setTo(toValue)
                    .setFrom(dynamicFromValue)
                    .setDelta(dynamicToValue)
                    .setTo(dynamicDeltaValue)
                    .setOnComplete(onComplete)
                    .setOnHalfComplete(onComplete);
        }
    }
}
