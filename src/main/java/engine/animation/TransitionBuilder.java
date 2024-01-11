package engine.animation;

import engine.mathUtil.Vec2;
import engine.util.Interpolator;
import engine.util.PropertyGetter;
import engine.util.PropertySetter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public sealed abstract class TransitionBuilder<T, V> permits
        TransitionBuilder.Double,
        TransitionBuilder.Vec2
{
    private final static double DEFAULT_DURATION = 1d;
    public TransitionBuilder() {}
    private V fromValue;
    private V deltaValue;
    private V toValue;
    private PropertyGetter<V> dynamicFromValue;
    private PropertyGetter<V> dynamicDeltaValue;
    private PropertyGetter<V> dynamicToValue;
    private PropertySetter<V> propertySetter;
    private double duration = 1;
    private boolean reverse = false;
    private Interpolator interpolator = (t) -> t;
    private Runnable onComplete;
    private Runnable onHalfComplete;
    @Contract(" _ -> this")
    public final TransitionBuilder<T, V> setDuration(double duration)
    {
        this.duration = duration;
        return this;
    }
    @Contract(" _ -> this")
    public final TransitionBuilder<T, V> setPropertySetter(@NotNull PropertySetter<V> propertySetter)
    {
        this.propertySetter = propertySetter;
        return this;
    }
    @Contract(" _ -> this")
    public final TransitionBuilder<T, V> setReverse(boolean reverse)
    {
        this.reverse = reverse;
        return this;
    }
    @Contract(" _ -> this")
    public final TransitionBuilder<T, V> setInterpolator(@NotNull Interpolator interpolator)
    {
        this.interpolator = interpolator;
        return this;
    }
    @Contract(" _ -> this")
    public final TransitionBuilder<T, V> setOnComplete(Runnable onComplete)
    {
        this.onComplete = onComplete;
        return this;
    }
    @Contract(" _ -> this")
    public final TransitionBuilder<T, V> setOnHalfComplete(Runnable onHalfComplete)
    {
        this.onHalfComplete = onHalfComplete;
        return this;
    }
    @Contract(" _ -> this")
    public final TransitionBuilder<T, V> setFrom(V fromValue)
    {
        this.fromValue = fromValue;
        return this;
    }
    @Contract(" _ -> this")
    public final TransitionBuilder<T, V> setDelta(V byValue)
    {
        this.deltaValue = byValue;
        return this;
    }
    @Contract(" _ -> this")
    public final TransitionBuilder<T, V> setTo(V toValue)
    {
        this.toValue = toValue;
        return this;
    }
    @Contract(" _ -> this")
    public final TransitionBuilder<T, V> setFrom(PropertyGetter<V> fromValue)
    {
        this.dynamicFromValue = fromValue;
        return this;
    }
    @Contract(" _ -> this")
    public final TransitionBuilder<T, V> setDelta(PropertyGetter<V> byValue)
    {
        this.dynamicDeltaValue = byValue;
        return this;
    }
    @Contract(" _ -> this")
    public final TransitionBuilder<T, V> setTo(PropertyGetter<V> toValue)
    {
        this.dynamicToValue = toValue;
        return this;
    }
    protected abstract V add(V first, V second);
    protected abstract V sub(V first, V second);
    @SuppressWarnings("unchecked")
    public final @NotNull T build()
    {
        if ((fromValue == null && dynamicFromValue == null) && (toValue == null && dynamicToValue == null) ||
                (fromValue == null && dynamicFromValue == null) && (deltaValue == null && dynamicDeltaValue == null) ||
                (toValue == null && dynamicToValue == null) && (deltaValue == null && dynamicDeltaValue == null))
            throw new IllegalArgumentException("Not enough arguments provided for Transition.");

        else if(duration <= 0)
            throw new IllegalArgumentException("null may not be used");

        else if(propertySetter == null)
            throw new IllegalArgumentException("propertySetter required");

        boolean toNull = toValue == null && dynamicToValue == null;
        boolean fromNull = fromValue == null && dynamicFromValue == null;

        final PropertyGetter<V> getFrom;
        final PropertyGetter<V> getDelta;
        final PropertyGetter<V> getTo = dynamicToValue != null ? dynamicToValue : () -> toValue;

        if(fromNull)
        {
            getDelta = dynamicDeltaValue != null ? dynamicDeltaValue : () -> deltaValue;
            getFrom = () -> sub(getTo.get(), getDelta.get());
        }
        else
        {
            final V finalFrom = getImmutableCopy(fromValue);
            final V finalDelta = getImmutableCopy(deltaValue);

            getFrom = dynamicFromValue != null ? dynamicFromValue : () -> finalFrom;
            getDelta = toNull ?
                    dynamicDeltaValue != null ? dynamicDeltaValue : () -> finalDelta :
                    () -> sub(getTo.get(), getFrom.get());
        }

        var castedInstance = (Transition<T, V>) getNewInstance(getFrom, getDelta);
        castedInstance.setInterpolator(interpolator);
        castedInstance.setOnComplete(onComplete);
        castedInstance.setOnHalfComplete(onHalfComplete);
        return (T) castedInstance;
    }

    protected PropertySetter<V> getPropertySetter()
    {
        return propertySetter;
    }
    protected double getDuration()
    {
        return duration;
    }
    protected boolean getReverse()
    {
        return reverse;
    }
    @Contract("_, _ -> new")
    protected abstract T getNewInstance(@NotNull PropertyGetter<V> getFrom, @NotNull PropertyGetter<V> getDelta);
    protected abstract V getImmutableCopy(V reference);
    public final static class Double extends TransitionBuilder<DoubleTransition, java.lang.Double>
    {
        public Double(@NotNull PropertySetter<java.lang.Double> propertySetter, double duration)
        {
            setPropertySetter(propertySetter);
            setDuration(duration);
        }
        public Double(@NotNull PropertySetter<java.lang.Double> propertySetter)
        {
            setPropertySetter(propertySetter);
        }
        @Override
        protected @NotNull DoubleTransition getNewInstance(
                @NotNull PropertyGetter<java.lang.Double> getFrom,
                @NotNull PropertyGetter<java.lang.Double> getDelta)
        {
            return new DoubleTransition(getFrom,getDelta, getPropertySetter(), getDuration(), getReverse());
        }

        @Override
        protected java.lang.Double getImmutableCopy(java.lang.Double reference)
        {
            return reference;
        }
        @Override
        protected @NotNull java.lang.Double add(@NotNull java.lang.Double first, @NotNull java.lang.Double second)
        {
            return first + second;
        }
        @Override
        protected @NotNull java.lang.Double sub(@NotNull java.lang.Double first, @NotNull java.lang.Double second)
        {
            return first - second;
        }
    }
    public final static class Vec2 extends TransitionBuilder<Vec2Transition, engine.mathUtil.Vec2>
    {
        public Vec2(@NotNull PropertySetter<engine.mathUtil.Vec2> propertySetter, double duration)
        {
            setPropertySetter(propertySetter);
            setDuration(duration);
        }
        public Vec2(@NotNull PropertySetter<engine.mathUtil.Vec2> propertySetter)
        {
            setPropertySetter(propertySetter);
        }
        public Vec2() {}
        @Override
        protected @NotNull Vec2Transition getNewInstance(
                @NotNull PropertyGetter<engine.mathUtil.Vec2> getFrom,
                @NotNull PropertyGetter<engine.mathUtil.Vec2> getDelta)
        {
            return new Vec2Transition(getFrom,getDelta, getPropertySetter(), getDuration(), getReverse());
        }

        @Override
        protected engine.mathUtil.Vec2 getImmutableCopy(engine.mathUtil.Vec2 reference) {
            return null;
        }

        @Override
        protected @NotNull engine.mathUtil.Vec2 add(@NotNull engine.mathUtil.Vec2 first, @NotNull engine.mathUtil.Vec2 second)
        {
            return first.add(second);
        }
        @Override
        protected @NotNull engine.mathUtil.Vec2 sub(@NotNull engine.mathUtil.Vec2 first, @NotNull engine.mathUtil.Vec2 second)
        {
            return first.sub(second);
        }
    }
}
