package engine.util;

import engine.util.math.Vec2;

public class RenderData
{
    public final Vec2 dimensions;
    public final PivotPoint pivotPoint;
    public final Vec2 pivotOffset;
    public final double unit;
    public RenderData(Vec2 dimensions, PivotPoint pivotPoint, double unit)
    {
        this.dimensions = new Vec2(dimensions).mult(unit);
        this.pivotPoint = pivotPoint;
        this.pivotOffset = pivotPoint.asVec2().mult(dimensions).mult(unit);
        this.unit = unit;
    }
}
