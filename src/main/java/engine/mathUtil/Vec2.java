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
    public Vec2(Vec2 vec2)
    {
        this.x = vec2.x;
        this.y = vec2.y;
    }
    public Vec2(@NotNull Vec3 vec3)
    {
        this.x = vec3.x;
        this.y = vec3.y;
    }
    public Vec2(@NotNull Vec4 vec4)
    {
        this.x = vec4.x;
        this.y = vec4.y;
    }


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
            this.x == other.x &&
            this.y == other.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }

    public float getDistance(@NotNull Vec2 pos)
    {
        double deltaX = pos.x - x;
        double deltaY = pos.y - y;

        return (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
    }

    public @NotNull Vec2 add(@NotNull Vec2 vec)
    {
        double resX = this.x + vec.x;
        double resY = this.y + vec.y;
        return new Vec2(resX, resY);
    }

    public @NotNull Vec2 sub(@NotNull Vec2 vec)
    {
        double resX =  this.x - vec.x;
        double resY =  this.y - vec.y;
        return new Vec2(resX, resY);
    }

    public @NotNull Vec2 subr(@NotNull Vec2 vec)
    {
        double resX =  vec.x - this.x;
        double resY =  vec.y - this.y;
        return new Vec2(resX, resY);
    }

    public @NotNull Vec2 mult(@NotNull Vec2 vec)
    {
        double resX = this.x * vec.x;
        double resY = this.y * vec.y;
        return new Vec2(resX, resY);
    }

    public @NotNull Vec2 div(@NotNull Vec2 vec)
    {
        double resX = this.x / vec.x;
        double resY = this.y / vec.y;
        return new Vec2(resX, resY);
    }

    public @NotNull Vec2 divr(@NotNull Vec2 vec)
    {
        double resX = vec.x / this.x;
        double resY = vec.y / this.y;
        return new Vec2(resX, resY);
    }

    @Override
    public @NotNull Vec2 add(int value)
    {
        double resX = this.x + value;
        double resY = this.y + value;
        return new Vec2(resX, resY);
    }

    @Override
    public @NotNull Vec2 sub(int value)
    {
        double resX = this.x - value;
        double resY = this.y - value;
        return new Vec2(resX, resY);
    }

    public @NotNull Vec2 subr(int value)
    {
        double resX =  value - this.x;
        double resY =  value - this.y;
        return new Vec2(resX, resY);
    }

    @Override
    public @NotNull Vec2 mult(int value)
    {
        double resX = this.x * value;
        double resY = this.y * value;
        return new Vec2(resX, resY);
    }

    @Override
    public @NotNull Vec2 div(int value)
    {
        double resX = this.x / value;
        double resY = this.y / value;
        return new Vec2(resX, resY);
    }

    @Override
    public @NotNull Vec2 divr(int value)
    {
        double resX = value / this.x;
        double resY = value / this.y;
        return new Vec2(resX, resY);
    }

    @Override
    public @NotNull Vec2 add(float value)
    {
        int resX = (int)(this.x + value);
        int resY = (int)(this.y + value);
        return new Vec2(resX, resY);
    }

    @Override
    public @NotNull Vec2 sub(float value)
    {
        int resX = (int)(this.x - value);
        int resY = (int)(this.y - value);
        return new Vec2(resX, resY);
    }

    @Override
    public @NotNull Vec2 subr(float value)
    {
        int resX =  (int)(value - this.x);
        int resY =  (int)(value - this.y);
        return new Vec2(resX, resY);
    }

    @Override
    public @NotNull Vec2 mult(float value)
    {
        int resX = (int)(this.x * value);
        int resY = (int)(this.y * value);
        return new Vec2(resX, resY);
    }

    @Override
    public @NotNull Vec2 div(float value)
    {
        int resX = (int)(this.x / value);
        int resY = (int)(this.y / value);
        return new Vec2(resX, resY);
    }

    @Override
    public @NotNull Vec2 divr(float value)
    {
        int resX = (int)(value / this.x);
        int resY = (int)(value / this.y);
        return new Vec2(resX, resY);
    }
}
