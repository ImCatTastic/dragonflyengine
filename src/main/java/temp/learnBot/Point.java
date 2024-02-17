package temp.learnBot;

import java.util.Objects;

public class Point
{
    public final int x;
    public final int y;
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Point otherPoint = (Point) obj;
        return x == otherPoint.x && y == otherPoint.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
}
