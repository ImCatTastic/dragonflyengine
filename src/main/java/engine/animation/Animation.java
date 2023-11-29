package engine.animation;

import engine.util.Time;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public abstract class Animation
{
    protected double progress = 0;
    protected double normalizedProgress = 0;
    protected final double duration;
    protected final double halfDuration;
    protected final double reciprocalDuration;
    private AnimationProperties properties = new AnimationProperties();
    private boolean locked = false;
    private boolean halfCompletePlayed = false;
    public Animation(final double duration, final boolean reverse)
    {
        double factor = reverse ? 2 : 1;
        this.duration = duration * factor;
        this.halfDuration = this.duration * 0.5;
        this.reciprocalDuration = (1 / this.duration) * factor;
    }
    public boolean update()
    {
        progress = Math.min(progress + Time.deltaTime, duration);
        normalizedProgress = progress * reciprocalDuration;
        normalizedProgress = properties.interpolator.apply(normalizedProgress > 1 ? 2 - normalizedProgress : normalizedProgress);
        onUpdate();

        if(properties.onHalfComplete != null && !halfCompletePlayed && progress >= halfDuration)
        {
            halfCompletePlayed = true;
            properties.onHalfComplete.run();
        }

        else if(progress == duration)
        {
            if(properties.onComplete != null)
                properties.onComplete.run();

            locked = false;
            return true;
        }

        return false;
    }
    protected abstract void onUpdate();
    public void init()
    {
        progress = 0;
        locked = true;
        halfCompletePlayed = false;
    }
    public Animation setInterpolator(Function<Double, Double> interpolator)
    {
        if(!locked)
            properties.interpolator = interpolator;

        return this;
    }
    protected Function<Double, Double> getInterpolator()
    {
        return properties.interpolator;
    }
    public Animation setOnComplete(Runnable onComplete)
    {
        if(!locked)
            properties.onComplete = onComplete;

        return this;
    }
    public Animation setOnHalfComplete(Runnable onHalfComplete)
    {
        if(!locked)
            properties.onHalfComplete = onHalfComplete;

        return this;
    }
    public Animation setProperties(@NotNull AnimationProperties properties)
    {
        if(!locked)
            this.properties = new AnimationProperties(properties);

        return this;
    }
}
