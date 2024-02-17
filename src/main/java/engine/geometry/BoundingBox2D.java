package engine.geometry;

public class BoundingBox2D
{
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double width;
    private double height;
    public BoundingBox2D(double x1, double y1, double x2, double y2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        calcWidth();
        calcHeight();
    }
    public double getX1()
    {
        return x1;
    }
    public double getX2()
    {
        return x2;
    }
    public double getY1()
    {
        return y1;
    }
    public double getY2()
    {
        return y2;
    }
    public void setX1(double x1)
    {
        this.x1 = x1;
        calcWidth();
    }
    public void setX2(double x2)
    {
        this.x2 = x2;
        calcWidth();
    }
    public void setY1(double y1)
    {
        this.y1 = y1;
        calcHeight();
    }
    public void setY2(double y2)
    {
        this.y2 = y2;
        calcHeight();
    }
    public double getWidth()
    {
        return width;
    }
    public double getHeight()
    {
        return height;
    }
    private void calcWidth()
    {
        width = x2 - x1;
    }
    private void calcHeight()
    {
        height = y2 - y1;
    }
}
