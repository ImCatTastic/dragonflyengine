package engine.animation.player;

import engine.animation.Animation;
import engine.animation.player.AnimationPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimationState
{
    private final Animation animation;
    public AnimationState(Animation animation)
    {
        this.animation = animation;
    }
    Animation getAnimation()
    {
        return animation;
    }

    public void createStateChanger()
    {
        //if(keyevent)
        //receive keyevent, check if any keyevent repsponds to an animtion
    }
}

//AddKeyTrigger({'w'}, noCaps, noShift);

//K:e,
//K:w