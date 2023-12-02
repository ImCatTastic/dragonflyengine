package engine.animation;

import engine.mathUtil.Vec4;
import engine.util.Interpolator;
import engine.util.PropertySetter;
import org.jetbrains.annotations.NotNull;

public class Vec4Transition extends Transition<Vec4>
{
    public Vec4Transition(@NotNull PropertySetter<Vec4> propertySetter, double duration, boolean reverse, Interpolator interpolator)
    {
        super(propertySetter, duration, reverse, interpolator);
    }
    public Vec4Transition(@NotNull PropertySetter<Vec4> propertySetter, double duration, Interpolator interpolator)
    {
        super(propertySetter, duration, interpolator);
    }
    public Vec4Transition(@NotNull PropertySetter<Vec4> propertySetter, double duration, boolean reverse)
    {
        super(propertySetter, duration, reverse);
    }
    public Vec4Transition(@NotNull PropertySetter<Vec4> propertySetter, double duration)
    {
        super(propertySetter, duration);
    }
    @Override
    protected Vec4 add(@NotNull Vec4 first, @NotNull Vec4 second)
    {
        return first.add(second);
    }
    @Override
    protected Vec4 sub(@NotNull Vec4 first, @NotNull Vec4 second)
    {
        return first.sub(second);
    }
    private final Vec4 result = new Vec4(0,0,0,0); //Avoid creating new object every frame
    @Override
    protected Vec4 calcFrame(@NotNull Vec4 from, @NotNull Vec4 delta, double normalizedProgress)
    {
        result.x = from.x + delta.x * normalizedProgress;
        result.y = from.y + delta.y * normalizedProgress;
        result.z = from.z + delta.z * normalizedProgress;
        result.w = from.w + delta.w * normalizedProgress;
        return result;
    }
}
