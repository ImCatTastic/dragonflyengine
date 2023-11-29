package engine;

import engine.animation.Animation;
import learnBot.FOPAnimation;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ConcurrentLinkedDeque;

public class AnimationHandler
{
    protected int animationsInQueue()
    {
        return animations.size();
    }
    private final ConcurrentLinkedDeque<Animation> animations = new ConcurrentLinkedDeque<>();
    public void queueAnimation(@NotNull Animation animation)
    {
        animation.init();
        animations.add(animation);
    }
    protected void update()
    {
        animations.removeIf(Animation::update);
    }
}
