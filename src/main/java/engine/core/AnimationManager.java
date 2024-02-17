package engine.core;

import engine.animation.Animation;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;

public class AnimationManager
{
    private AnimationManager() {}
    private final static LinkedList<Animation<?>> animations = new LinkedList<>();
    public static void queueAnimation(@NotNull Animation<?> animation)
    {
        animation.init();
        animations.add(animation);
    }
    public static void queueAnimations(@NotNull Animation<?> @NotNull ... animations)
    {
        for (Animation<?> animation : animations)
        {
            animation.init();
            AnimationManager.animations.add(animation);
        }
    }
    static void update()
    {
        //animations.removeIf(Animation::update);
    }
}
