package engine;

import engine.mathUtil.Vec2;

public class Transform2D
{
    public Transform2D(Vec2 position, Vec2 scale, double rotation)
    {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }
    public final Vec2 position;
    public final Vec2 scale;
    public double rotation;
}
