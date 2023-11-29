package engine.animation;

import engine.util.PropertyGetter;
import engine.util.PropertySetter;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class TransitionBuilder<T>
{
    protected T fromValue;
    protected T byValue;
    protected T toValue;
    protected PropertyGetter<T> dynamicFromValue;
    protected PropertyGetter<T> dynamicByValue;
    protected PropertyGetter<T> dynamicToValue;
    protected PropertySetter<T> propertySetter;
    protected double duration;
    protected boolean reverse;
    protected AnimationProperties properties = new AnimationProperties();
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
    public TransitionBuilder<T> setInterpolator(Function<java.lang.Double, java.lang.Double> interpolator)
    {
        properties.interpolator = interpolator;
        return this;
    }
    public TransitionBuilder<T> setOnComplete(Runnable onComplete)
    {
        properties.onComplete = onComplete;
        return this;
    }
    public TransitionBuilder<T> setOnHalfComplete(Runnable onHalfComplete)
    {
        properties.onHalfComplete = onHalfComplete;
        return this;
    }
    public TransitionBuilder<T> setProperties(AnimationProperties properties)
    {
        this.properties = new AnimationProperties(properties);
        return this;
    }
    public TransitionBuilder<T> setFrom(T fromValue)
    {
        this.fromValue = fromValue;
        return this;
    }
    public TransitionBuilder<T> setBy(T byValue)
    {
        this.byValue = byValue;
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
    public TransitionBuilder<T> setBy(PropertyGetter<T> byValue)
    {
        this.dynamicByValue = byValue;
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
            DoubleTransition transition = new DoubleTransition(propertySetter, duration, reverse);

            transition.setFrom(fromValue)
                    .setBy(byValue)
                    .setTo(toValue)
                    .setFrom(dynamicFromValue)
                    .setFrom(dynamicByValue)
                    .setFrom(dynamicToValue);

            return (DoubleTransition) transition.setProperties(properties);
        }
    }
}
