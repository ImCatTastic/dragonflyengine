package engine.mathUtil;

import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class Vec2 implements Vector<Vec2>
{
    public double x;
    public double y;
    public Vec2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    public Vec2(double x)
    {
        this.x = x;
    }
    public Vec2() {}
    public Vec2(@NotNull Vec2 vec2)
    {
        x = vec2.x;
        y = vec2.y;
    }
    public Vec2(@NotNull IVec2 iVec2)
    {
        x = iVec2.x;
        y = iVec2.y;
    }
    //#region shortcuts
    public Vec2 xx()
    {
        return new Vec2(x, x);
    }
    public Vec2 xy()
    {
        return new Vec2(x, y);
    }
    public Vec2 yx()
    {
        return new Vec2(y, x);
    }
    public Vec2 yy()
    {
        return new Vec2(y, y);
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

        Vec2 other = (Vec2) obj;

        return
            x == other.x &&
            y == other.y;
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
    public double getDistance(@NotNull Vec2 pos)
    {
        double deltaX = pos.x - x;
        double deltaY = pos.y - y;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
    }
    public double getDistance(@NotNull IVec2 pos)
    {
        double deltaX = pos.x - x;
        double deltaY = pos.y - y;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
    }
    public Vec2 add(@NotNull Vec2 vec2)
    {
        return new Vec2(x + vec2.x, y + vec2.y);
    }
    public Vec2 sub(@NotNull Vec2 vec2)
    {
		return new Vec2(x - vec2.x, y - vec2.y);
    }
    public Vec2 subr(@NotNull Vec2 vec2)
    {
		return new Vec2(vec2.x - x, vec2.y - y);
    }
    public Vec2 mult(@NotNull Vec2 vec2)
    {
		return new Vec2(x * vec2.x, y * vec2.y);
    }
    public Vec2 div(@NotNull Vec2 vec2)
    {
		return new Vec2(x / vec2.x, y / vec2.y);
    }
    public Vec2 divr(@NotNull Vec2 vec2)
    {
		return new Vec2(vec2.x / x, vec2.y / y);
    }
    public Vec2 add(@NotNull IVec2 iVec2)
    {
		return new Vec2(x + iVec2.x, y + iVec2.y);
    }
    public Vec2 sub(@NotNull IVec2 iVec2)
    {
		return new Vec2(x - iVec2.x, y - iVec2.y);
    }
    public Vec2 subr(@NotNull IVec2 iVec2)
    {
		return new Vec2(iVec2.x - x, iVec2.y - y);
    }
    public Vec2 mult(@NotNull IVec2 iVec2)
    {
		return new Vec2(x * iVec2.x, y * iVec2.y);
    }
    public Vec2 div(@NotNull IVec2 iVec2)
    {
		return new Vec2(x / iVec2.x, y / iVec2.y);
    }
    public Vec2 divr(@NotNull IVec2 iVec2)
    {
		return new Vec2(iVec2.x / x, iVec2.y / y);
    }
    @Override
    public Vec2 add(double value)
    {
		return new Vec2(x + value, y + value);
    }
    @Override
    public Vec2 sub(double value)
    {
		return new Vec2(x - value, y - value);
    }
    @Override
    public Vec2 subr(double value)
    {
		return new Vec2(value - x, value - y);
    }
    @Override
    public Vec2 mult(double value)
    {
		return new Vec2(x * value, y * value);
    }
    @Override
    public Vec2 div(double value)
    {
		return new Vec2(x / value, y / value);
    }
    @Override
    public Vec2 divr(double value)
    {
		return new Vec2(value / x, value / y);
    }
    @Override
    public Vec2 add(int value)
    {
		return new Vec2(x + value, y + value);
    }
    @Override
    public Vec2 sub(int value)
    {
		return new Vec2(x - value, y - value);
    }
    @Override
    public Vec2 subr(int value)
    {
		return new Vec2(value - x, value - y);
    }
    @Override
    public Vec2 mult(int value)
    {
		return new Vec2(x * value, y * value);
    }
    @Override
    public Vec2 div(int value)
    {
		return new Vec2(x / value, y / value);
    }
    @Override
    public Vec2 divr(int value)
    {
		return new Vec2(value / x, value / y);
    }
}