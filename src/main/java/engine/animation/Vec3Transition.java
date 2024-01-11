package engine.animation;

import engine.mathUtil.Vec2;
import engine.mathUtil.Vec3;
import engine.util.Interpolator;
import engine.util.PropertyGetter;
import engine.util.PropertySetter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Vec3Transition extends Transition<Vec3Transition, Vec3>
{
    protected Vec3Transition(
            PropertyGetter<Vec3> getFrom, PropertyGetter<Vec3> getDelta,
            PropertySetter<Vec3> propertySetter, double duration, boolean reverse)
    {
        super(getFrom,getDelta, propertySetter, duration, reverse);
    }
    private final Vec3 result = new Vec3(0,0,0); //Avoid creating new object every frame
    @Override
    protected @NotNull Vec3 calcFrame(@NotNull Vec3 from, @NotNull Vec3 delta, double normalizedProgress)
    {
        result.x = from.x + delta.x * normalizedProgress;
        result.y = from.y + delta.y * normalizedProgress;
        result.z = from.z + delta.z * normalizedProgress;
        return result;
    }
}