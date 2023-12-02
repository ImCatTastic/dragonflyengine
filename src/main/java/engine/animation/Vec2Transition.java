package engine.animation;

import engine.mathUtil.Vec2;
import engine.util.Interpolator;
import engine.util.PropertySetter;
import org.jetbrains.annotations.NotNull;

public class Vec2Transition extends Transition<Vec2>
{
    public Vec2Transition(@NotNull PropertySetter<Vec2> propertySetter, double duration, boolean reverse, Interpolator interpolator)
    {
        super(propertySetter, duration, reverse, interpolator);
    }
    public Vec2Transition(@NotNull PropertySetter<Vec2> propertySetter, double duration, Interpolator interpolator)
    {
        super(propertySetter, duration, interpolator);
    }
    public Vec2Transition(@NotNull PropertySetter<Vec2> propertySetter, double duration, boolean reverse)
    {
        super(propertySetter, duration, reverse);
    }
    public Vec2Transition(@NotNull PropertySetter<Vec2> propertySetter, double duration)
    {
        super(propertySetter, duration);
    }
    @Override
    protected Vec2 add(@NotNull Vec2 first, @NotNull Vec2 second)
    {
        return first.add(second);
    }
    @Override
    protected Vec2 sub(@NotNull Vec2 first, @NotNull Vec2 second)
    {
        return first.sub(second);
    }
    private final Vec2 result = new Vec2(0,0); //Avoid creating new object every frame
    @Override
    protected Vec2 calcFrame(@NotNull Vec2 from, @NotNull Vec2 delta, double normalizedProgress)
    {
        result.x = from.x + delta.x * normalizedProgress;
        result.y = from.y + delta.y * normalizedProgress;
        return result;
    }
}