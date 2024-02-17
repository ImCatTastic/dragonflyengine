package engine.animation;

import engine.mathUtil.Vec2;
import engine.util.PropertyGetter;
import engine.util.PropertySetter;
import org.jetbrains.annotations.NotNull;

public class Vec2Transition extends Transition<Vec2Transition, Vec2>
{
    protected Vec2Transition(
            PropertyGetter<Vec2> getFrom, PropertyGetter<Vec2> getDelta,
            PropertySetter<Vec2> propertySetter, double duration, boolean reverse)
    {
        super(getFrom,getDelta, propertySetter, duration, reverse);
    }
    private final Vec2 result = new Vec2(0,0); //Avoid creating new object every frame
    @Override
    protected @NotNull Vec2 calcFrame(@NotNull Vec2 from, @NotNull Vec2 delta, double normalizedProgress)
    {
        /*
        result.x = from.x + delta.x * normalizedProgress;
        result.y = from.y + delta.y * normalizedProgress;
        return result;

         */
        return new Vec2();
    }
}