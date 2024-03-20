package engine.util;

import engine.util.math.Vec2;

public class PivotPoint
{
    public final static PivotPoint TOP_LEFT = new PivotPoint(0,0);
    public final static PivotPoint TOP_CENTER = new PivotPoint(0.5,0);
    public final static PivotPoint TOP_RIGHT = new PivotPoint(1,0);
    public final static PivotPoint CENTER_LEFT = new PivotPoint(0,0.5);
    public final static PivotPoint CENTER = new PivotPoint(0.5,0.5);
    public final static PivotPoint CENTER_RIGHT = new PivotPoint(1,0.5);
    public final static PivotPoint BOTTOM_LEFT = new PivotPoint(0,1);
    public final static PivotPoint BOTTOM_CENTER = new PivotPoint(0.5,1);
    public final static PivotPoint BOTTOM_RIGHT = new PivotPoint(1,1);
    private final Vec2 point;
    public PivotPoint(double x, double y)
    {
        if(x < 0 || x > 1) throw new IllegalArgumentException("x out of bounds");
        if(y < 0 || y > 1) throw new IllegalArgumentException("y out of bounds");
        point = new Vec2(x, y);
    }
    public double getX()
    {
        return point.x;
    }
    public double getY()
    {
        return point.y;
    }
    public Vec2 asVec2()
    {
        return point;
    }

    public static PivotPoint deriveFromVec2(Vec2 vec2)
    {
        if(vec2.x >= 0 && vec2.x <= 1 && vec2.y >= 0 && vec2.y <= 1)
            return new PivotPoint(vec2.x, vec2.y);

        throw new IllegalArgumentException("Failed to derive pivotPoint from vec2, not in range 0 - 1: " + vec2);
    }

    @Override
    public String toString()
    {
        return point.toString();
    }
}