package learnBot;

import engine.util.Time;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class FOPAnimation
{
    public FOPAnimation(double startValue, double endValue, BiConsumer<Double, Double> onUpdate)
    {
        this.startValue = startValue;
        this.endValue = endValue;
        this.onUpdate = onUpdate;
    }
    public FOPAnimation setDuration(double duration)
    {
        this.duration = duration;
        this.reciprocalDuration = 1.0 / duration;
        return this;
    }
    public FOPAnimation setUnlockThread(boolean unlockThread) {
        this.unlockThread = unlockThread;
        return this;
    }
    public FOPAnimation setInterpolator(Function<Double, Double> interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public FOPAnimation setOnComplete(Runnable onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    private final double startValue;
    private final double endValue;
    private final BiConsumer<Double, Double> onUpdate;
    private Runnable onComplete;
    private double duration = 1;
    private double progress = 0;
    private double reciprocalDuration;
    private boolean unlockThread = false;
    private Function<Double, Double> interpolator = (t) -> t;
    public boolean update()
    {
        progress += Time.deltaTime;
        progress = Math.min(progress, duration);

        final double normalizedProgress = progress * reciprocalDuration;
        final double val = startValue + (endValue - startValue) * interpolator.apply(normalizedProgress);
        onUpdate.accept(normalizedProgress, val);

        if(progress >= duration)
        {
            if(unlockThread)
                Sync.giveSignal();

            if(onComplete != null)
                onComplete.run();

            return true;
        }

        return false;
    }
}
