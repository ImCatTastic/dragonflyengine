package engine.animation;

import engine.util.Interpolator;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class AnimationProperties
{
    private static final Interpolator DEFAULT_INTERPOLATOR = (t) -> t;
    public final double duration;
    public final double halfDuration;
    public final double reciprocalDuration;
    public final int repeatCount;
    public final boolean reverse;
    public Interpolator interpolator = DEFAULT_INTERPOLATOR;
    public Runnable onComplete;
    public Runnable onHalfComplete;
    public AnimationProperties(double duration, int repeatCount, boolean reverse)
    {
        this.duration = duration * (reverse ? 2 : 1);
        this.halfDuration = this.duration * 0.5;
        this.reciprocalDuration = (1 / duration);
        this.repeatCount = repeatCount;
        this.reverse = reverse;
    }
    public AnimationProperties(@NotNull AnimationProperties properties) 
    {
        duration = properties.duration;
        halfDuration = properties.duration * 0.5;
        reciprocalDuration = (1 / properties.duration) * (properties.reverse ? 2 : 1);
        repeatCount = properties.repeatCount;
        reverse = properties.reverse;
        onComplete = properties.onComplete;
        onHalfComplete = properties.onHalfComplete;
        interpolator = properties.interpolator;
    }
}
