package engine.geometry;

import engine.mathUtil.Vec2;
import org.jetbrains.annotations.NotNull;

public class Transform2D
{
    public Transform2D(Vec2 position, Vec2 scale, double rotation)
    {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }
    public final Vec2 position;
    public double rotation;
    public final Vec2 scale;
}
