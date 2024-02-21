package engine.util;

import engine.util.math.Vec2;

public class PivotPoint
{
    public final static PivotPoint TOP_LEFT = new PivotPoint(0,0);
    public final static PivotPoint TOP_CENTER = new PivotPoint(0.5,0);
    public final static PivotPoint TOP_RIGHT = new PivotPoint(1,0);
    public final static PivotPoint CENTER_LEFT = new PivotPoint(0,0.5);
    public final static PivotPoint CENTER = new PivotPoint(0.5,0.5);
    public final static PivotPoint CENTER_RIGHT = new PivotPoint(1,0);
    public final static PivotPoint BOTTOM_LEFT = new PivotPoint(0,1);
    public final static PivotPoint BOTTOM_CENTER = new PivotPoint(0.5,1);
    public final static PivotPoint BOTTOM_RIGHT = new PivotPoint(1,1);
    private final Vec2 point;
    public PivotPoint(double x, double y)
    {
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
}
