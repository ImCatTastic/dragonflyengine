package engine.animation;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class AnimationProperties
{
    private final static Function<Double, Double> DEFAULT_INTERPOLATOR = (t) -> t;
    public Runnable onComplete;
    public Runnable onHalfComplete;
    public Function<Double, Double> interpolator;
    public AnimationProperties(Runnable onComplete, Runnable onHalfComplete, @NotNull Function<Double, Double> interpolator)
    {
        this.onComplete = onComplete;
        this.onHalfComplete = onHalfComplete;
        this.interpolator = interpolator;
    }
    public AnimationProperties(Runnable onComplete, Runnable onHalfComplete)
    {
        this(onComplete, onHalfComplete, DEFAULT_INTERPOLATOR);
    }
    public AnimationProperties(Runnable onComplete, Function<Double, Double> interpolator)
    {
        this(onComplete, null, interpolator);
    }
    public AnimationProperties(Runnable onComplete)
    {
        this(onComplete, null, DEFAULT_INTERPOLATOR);
    }
    public AnimationProperties(Function<Double, Double> interpolator)
    {
        this(null, null, interpolator);
    }
    public AnimationProperties()
    {
        this(null, null, DEFAULT_INTERPOLATOR);
    }
    public AnimationProperties(@NotNull AnimationProperties properties)
    {
        this(properties.onComplete, properties.onHalfComplete, properties.interpolator);
    }
}
