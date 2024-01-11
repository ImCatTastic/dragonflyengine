package engine.animation.player;

import engine.animation.Animation;
import engine.animation.AnimationListener;
import engine.animation.player.AnimationPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimationState
{
    private final Animation animation;
    private final AnimationPlayer player;
    public AnimationState(Animation animation, AnimationPlayer player)
    {
        this.animation = animation;
        this.player = player;
        animation.addListener(new AnimationListener()
        {
            @Override
            public void onStart()
            {

            }

            @Override
            public int onRoundComplete()
            {
                return 0;
            }

            @Override
            public void onComplete()
            {
                player.notifyCompletion();
            }
        });
    }

    public void createStateChanger()
    {
        //if(keyevent)
        //receive keyevent, check if any keyevent repsponds to an animtion
    }


    AnimationState getNextState()
    {
        return nextState;
    }
    private AnimationState onCompleteState = this; //Default is itself, meaning running in a loop
    private AnimationState nextState = onCompleteState;
    public void setOnCompleteState(AnimationState animationState)
    {
        this.onCompleteState = animationState;
    }
    Animation getAnimation()
    {
        return animation;
    }
}

//AddKeyTrigger({'w'}, noCaps, noShift, waitFinish, animation:0);

//K:e,
//K:w