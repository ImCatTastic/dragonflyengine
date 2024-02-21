package engine.animation;

import engine.animation.Animation;
import engine.animation.AnimationController;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class AnimationManager
{
    private AnimationManager() {}
    private final static LinkedList<Animation<?>> animations = new LinkedList<>();
    private final static LinkedList<AnimationController> animationControllers = new LinkedList<>();
    public static void queueAnimation(@NotNull Animation<?> animation)
    {
        animation.init();
        animations.add(animation);
    }
    public static void queueAnimations(@NotNull Animation<?> @NotNull ... animations)
    {
        for (Animation<?> animation : animations)
            queueAnimation(animation);
    }
    public static void addController(@NotNull AnimationController animationController)
    {
        if(!animationControllers.contains(animationController))
            animationControllers.add(animationController);
    }
    public static void update(double deltaTime)
    {
        animations.removeIf(animation -> animation.update(deltaTime));
    }
}
