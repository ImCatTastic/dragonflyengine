package engine.animation;

import engine.mathUtil.Vec3;
import engine.util.Interpolator;
import engine.util.PropertySetter;
import org.jetbrains.annotations.NotNull;

public class Vec3Transition extends Transition<Vec3>
{
    public Vec3Transition(@NotNull PropertySetter<Vec3> propertySetter, double duration, boolean reverse, Interpolator interpolator)
    {
        super(propertySetter, duration, reverse, interpolator);
    }
    public Vec3Transition(@NotNull PropertySetter<Vec3> propertySetter, double duration, Interpolator interpolator)
    {
        super(propertySetter, duration, interpolator);
    }
    public Vec3Transition(@NotNull PropertySetter<Vec3> propertySetter, double duration, boolean reverse)
    {
        super(propertySetter, duration, reverse);
    }
    public Vec3Transition(@NotNull PropertySetter<Vec3> propertySetter, double duration)
    {
        super(propertySetter, duration);
    }
    @Override
    protected Vec3 add(@NotNull Vec3 first, @NotNull Vec3 second)
    {
        return first.add(second);
    }
    @Override
    protected Vec3 sub(@NotNull Vec3 first, @NotNull Vec3 second)
    {
        return first.sub(second);
    }
    private final Vec3 result = new Vec3(0,0,0); //Avoid creating new object every frame
    @Override
    protected Vec3 calcFrame(@NotNull Vec3 from, @NotNull Vec3 delta, double normalizedProgress)
    {
        result.x = from.x + delta.x * normalizedProgress;
        result.y = from.y + delta.y * normalizedProgress;
        result.z = from.z + delta.z * normalizedProgress;
        return result;
    }
}