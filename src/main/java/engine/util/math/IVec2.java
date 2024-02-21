package engine.util.math;

import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class IVec2 implements Vector<IVec2>
{
    public int x;
    public int y;
    public IVec2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public IVec2(@NotNull IVec2 iVec2)
    {
        this.x = iVec2.x;
        this.y = iVec2.y;
    }
    public IVec2(@NotNull Vec2 vec2)
    {
        this.x = (int) vec2.x;
        this.y = (int) vec2.y;
    }
    public IVec2(int x)
    {
        this.x = x;
    }
    public IVec2() {}
    //#region shortcuts
    public IVec2 xx()
    {
        return new IVec2(x, x);
    }
    public IVec2 xy()
    {
        return new IVec2(x, y);
    }
    public IVec2 yx()
    {
        return new IVec2(y, x);
    }
    public IVec2 yy()
    {
        return new IVec2(y, y);
    }
    //#endregion
    public @NotNull String toString()
    {
        return "(" + x + "|" + y + ")";
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        IVec2 other = (IVec2) obj;

        return
                this.x == other.x &&
                        this.y == other.y;
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
    public double getDistance(@NotNull IVec2 iVec2)
    {
        double deltaX = iVec2.x - x;
        double deltaY = iVec2.y - y;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
    }
    public double getDistance(@NotNull Vec2 vec2)
    {
        double deltaX = vec2.x - x;
        double deltaY = vec2.y - y;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
    }
    public IVec2 add(@NotNull IVec2 iVec2)
    {
		return new IVec2(x + iVec2.x, y + iVec2.y); 
    }
    public IVec2 sub(@NotNull IVec2 iVec2)
    {
		return new IVec2(x - iVec2.x, y - iVec2.y);
    }
    public IVec2 subr(@NotNull IVec2 iVec2)
    {
        return new IVec2(iVec2.x - x , iVec2.y - y);
    }
    public IVec2 mult(@NotNull IVec2 iVec2)
    {
		return new IVec2(x * iVec2.x, y * iVec2.y); 
    }
    public IVec2 div(@NotNull IVec2 iVec2)
    {
		return new IVec2(x / iVec2.x, y / iVec2.y); 
    }
    public IVec2 divr(@NotNull IVec2 iVec2)
    {
        return new IVec2(iVec2.x / x , iVec2.y / y);
    }
    public IVec2 add(@NotNull Vec2 vec2)
    {
		return new IVec2(x + (int) vec2.x, y + (int) vec2.y); 
    }
    public IVec2 sub(@NotNull Vec2 vec2)
    {
		return new IVec2(x - (int) vec2.x, y - (int) vec2.y); 
    }
    public IVec2 subr(@NotNull Vec2 vec2)
    {
        return new IVec2((int) vec2.x - x , (int) vec2.y - y);
    }
    public IVec2 mult(@NotNull Vec2 vec2)
    {
		return new IVec2(x * (int) vec2.x, y * (int) vec2.y); 
    }
    public IVec2 div(@NotNull Vec2 vec2)
    {
		return new IVec2(x / (int) vec2.x, y / (int) vec2.y); 
    }
    public IVec2 divr(@NotNull Vec2 vec2)
    {
        return new IVec2((int) vec2.x / x , (int) vec2.y / y);
    }
    @Override
    public IVec2 add(double value)
    {
		return new IVec2(x + (int) value, y + (int) value); 
    }
    @Override
    public IVec2 sub(double value)
    {
		return new IVec2(x - (int) value, y - (int) value); 
    }
    @Override
    public IVec2 subr(double value)
    {
        return new IVec2((int) value - x , (int) value - y);
    }
    @Override
    public IVec2 mult(double value)
    {
		return new IVec2(x * (int) value, y * (int) value); 
    }
    @Override
    public IVec2 div(double value)
    {
		return new IVec2(x / (int) value, y / (int) value); 
    }
    @Override
    public IVec2 divr(double value)
    {
        return new IVec2((int) value / x , (int) value / y);
    }
    @Override
    public IVec2 add(int value)
    {
		return new IVec2(x + value, y + value); 
    }
    @Override
    public IVec2 sub(int value)
    {
		return new IVec2(x - value, y - value); 
    }
    @Override
    public IVec2 subr(int value)
    {
        return new IVec2(value - x , value - y);
    }
    @Override
    public IVec2 mult(int value)
    {
		return new IVec2(x * value, y * value); 
    }
    @Override
    public IVec2 div(int value)
    {
		return new IVec2(x / value, y / value); 
    }
    @Override
    public IVec2 divr(int value)
    {
		return new IVec2(value / x , value / y);
    }
}