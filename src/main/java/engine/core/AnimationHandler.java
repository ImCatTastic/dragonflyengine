package engine.core;

import engine.animation.Animation;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.ConcurrentLinkedDeque;

public class AnimationHandler
{
    private final ConcurrentLinkedDeque<Animation<?>> animations = new ConcurrentLinkedDeque<>();
    public void queueAnimation(@NotNull Animation<?> animation)
    {
        animation.init();
        animations.add(animation);
    }
    public void queueAnimations(@NotNull Animation<?> @NotNull ... animations)
    {
        for (Animation<?> animation : animations)
        {
            animation.init();
            this.animations.add(animation);
        }
    }
    public void update()
    {
        animations.removeIf(Animation::update);
    }
}
