package engine.animation.player;

import engine.Event;

public class AnimationPlayer
{
    private AnimationState currentState;
    public AnimationPlayer(AnimationState rootState)
    {
        currentState = rootState;
    }
}
