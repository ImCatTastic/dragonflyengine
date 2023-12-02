package engine.animation;

import engine.util.Interpolator;
import engine.util.PropertyGetter;
import engine.util.PropertySetter;

public abstract class Transition<T> extends Animation
{
    private T fromValue;
    private T deltaValue;
    private T toValue;
    private PropertyGetter<T> dynamicFromValue;
    private PropertyGetter<T> dynamicDeltaValue;
    private PropertyGetter<T> dynamicToValue;
    private PropertySetter<T> propertySetter;
    private T from;
    private T delta;
    protected Transition(PropertySetter<T> propertySetter, double duration, boolean reverse, Interpolator interpolator)
    {
        super(duration, reverse, interpolator);
        this.propertySetter = propertySetter;
    }
    protected Transition(PropertySetter<T> propertySetter, double duration, Interpolator interpolator)
    {
        super(duration, interpolator);
        this.propertySetter = propertySetter;
    }
    protected Transition(PropertySetter<T> propertySetter, double duration, boolean reverse)
    {
        super(duration, reverse);
        this.propertySetter = propertySetter;
    }
    protected Transition(PropertySetter<T> propertySetter, double duration)
    {
        super(duration);
        this.propertySetter = propertySetter;
    }
    @Override
    public final void init()
    {
        super.init();

        if ((fromValue == null && dynamicFromValue == null) && (toValue == null && dynamicToValue == null) ||
            (fromValue == null && dynamicFromValue == null) && (deltaValue == null && dynamicDeltaValue == null) ||
            (toValue == null && dynamicToValue == null) && (deltaValue == null && dynamicDeltaValue == null))
            throw new IllegalArgumentException("Not enough arguments provided for Transition.");

        from = dynamicFromValue != null ? dynamicFromValue.get() : fromValue;
        T to = dynamicToValue != null ? dynamicToValue.get() : toValue;
        delta = dynamicDeltaValue != null ? dynamicDeltaValue.get() : deltaValue;

        delta = from != null && to != null ? sub(to, from) : deltaValue;
        from = from == null ? sub(to, delta) : from;
    }
    protected abstract T add(T first, T second);
    protected abstract T sub(T first, T second);
    protected abstract T calcFrame(T from, T delta, double normalizedProgress);
    public Transition<T> setFrom(T fromValue)
    {
        this.fromValue = fromValue;
        return this;
    }
    public Transition<T> setDelta(T deltaValue)
    {
        this.deltaValue = deltaValue;
        return this;
    }
    public Transition<T> setTo(T toValue)
    {
        this.toValue = toValue;
        return this;
    }
    public Transition<T> setFrom(PropertyGetter<T> fromValue)
    {
        this.dynamicFromValue = fromValue;
        return this;
    }
    public Transition<T> setDelta(PropertyGetter<T> deltaValue)
    {
        this.dynamicDeltaValue = deltaValue;
        return this;
    }
    public Transition<T> setTo(PropertyGetter<T> toValue)
    {
        this.dynamicToValue = toValue;
        return this;
    }
    @Override
    protected final void onUpdate()
    {
        propertySetter.set(calcFrame(from, delta, normalizedProgress));
    }
}