package engine.animation;

import engine.util.Time;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class Animation
{
    public static final int REPEAT_INDEFINITE = -1;
    private double progress = 0;
    protected double normalizedProgress = 0;
    private final AnimationProperties properties;
    private boolean locked = false; //Prevent from modifying the animation while it is running
    private boolean halfCompletePlayed = false;
    public Animation(double duration, int repeatCount, boolean reverse)
    {
        this(new AnimationProperties(duration, repeatCount, reverse));
    }
    public Animation(double duration, boolean reverse)
    {
        this(new AnimationProperties(duration, 1, reverse));
    }
    public Animation(double duration, int repeatCount)
    {
        this(new AnimationProperties(duration, repeatCount, false));
    }
    public Animation(double duration)
    {
        this(new AnimationProperties(duration, 1, false));
    }
    protected Animation(@NotNull AnimationProperties properties)
    {
        this.properties = properties;
    }
    public boolean update()
    {
        progress = Math.min(progress + Time.deltaTime, properties.duration);
        normalizedProgress = progress * properties.reciprocalDuration;
        normalizedProgress = properties.interpolator.apply(normalizedProgress > 1 ? 2 - normalizedProgress : normalizedProgress);
        onUpdate();

        if(properties.onHalfComplete != null && !halfCompletePlayed && progress >= properties.halfDuration)
        {
            halfCompletePlayed = true;
            properties.onHalfComplete.run();
        }

        else if(progress == properties.duration)
        {
            if(properties.onComplete != null)
                properties.onComplete.run();

            locked = false;
            return true;
        }

        return shouldInterrupt;
    }
    private boolean shouldInterrupt = false;
    void interrupt()
    {
        shouldInterrupt = true;
    }
    protected abstract void onUpdate();
    public void init()
    {
        progress = 0;
        locked = true;
        halfCompletePlayed = false;
    }

    private final ArrayList<AnimationListener> listeners = new ArrayList<>();
    public void addListener(AnimationListener listener)
    {
        if(!listeners.contains(listener))
            listeners.add(listener);
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
}