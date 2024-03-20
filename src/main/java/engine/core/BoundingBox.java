package engine.core;

import engine.util.math.Vec2;

public class BoundingBox
{
    public final double minX;
    public final double minY;
    public final double maxX;
    public final double maxY;
    public final double width;
    public final double height;
    public final double upperBound;
    public final double lowerBound;
    public BoundingBox(double minX, double minY, double width, double height)
    {
        this.minX = minX;
        this.minY = minY;
        this.maxX = minX + width;
        this.maxY = minY + height;
        this.width = width;
        this.height = height;

        if(width > height)
        {
            upperBound = width;
            lowerBound = height;
        }

        else
        {
            upperBound = height;
            lowerBound = width;
        }
    }
    public Vec2 dimensions()
    {
        return new Vec2(width, height);
    }
}
