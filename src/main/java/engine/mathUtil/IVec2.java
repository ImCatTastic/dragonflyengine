package engine.mathUtil;

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
    public IVec2(IVec2 IVec2)
    {
        this.x = IVec2.x;
        this.y = IVec2.y;
    }
    public IVec2(@NotNull Vec3 vec3)
    {
        this.x = (int) vec3.x;
        this.y = (int) vec3.y;
    }
    public IVec2(@NotNull Vec4 vec4)
    {
        this.x = (int) vec4.x;
        this.y = (int) vec4.y;
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

    public float getDistance(@NotNull IVec2 pos)
    {
        double deltaX = pos.x - x;
        double deltaY = pos.y - y;

        return (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
    }

    public @NotNull IVec2 add(@NotNull IVec2 vec)
    {
        int resX = this.x + vec.x;
        int resY = this.y + vec.y;
        return new IVec2(resX, resY);
    }

    public @NotNull IVec2 sub(@NotNull IVec2 vec)
    {
        int resX =  this.x - vec.x;
        int resY =  this.y - vec.y;
        return new IVec2(resX, resY);
    }

    public @NotNull IVec2 subr(@NotNull IVec2 vec)
    {
        int resX =  vec.x - this.x;
        int resY =  vec.y - this.y;
        return new IVec2(resX, resY);
    }

    public @NotNull IVec2 mult(@NotNull IVec2 vec)
    {
        int resX = this.x * vec.x;
        int resY = this.y * vec.y;
        return new IVec2(resX, resY);
    }

    public @NotNull IVec2 div(@NotNull IVec2 vec)
    {
        int resX = this.x / vec.x;
        int resY = this.y / vec.y;
        return new IVec2(resX, resY);
    }

    public @NotNull IVec2 divr(@NotNull IVec2 vec)
    {
        int resX = vec.x / this.x;
        int resY = vec.y / this.y;
        return new IVec2(resX, resY);
    }

    @Override
    public @NotNull IVec2 add(int value)
    {
        int resX = this.x + value;
        int resY = this.y + value;
        return new IVec2(resX, resY);
    }

    @Override
    public @NotNull IVec2 sub(int value)
    {
        int resX = this.x - value;
        int resY = this.y - value;
        return new IVec2(resX, resY);
    }

    public @NotNull IVec2 subr(int value)
    {
        int resX =  value - this.x;
        int resY =  value - this.y;
        return new IVec2(resX, resY);
    }

    @Override
    public @NotNull IVec2 mult(int value)
    {
        int resX = this.x * value;
        int resY = this.y * value;
        return new IVec2(resX, resY);
    }

    @Override
    public @NotNull IVec2 div(int value)
    {
        int resX = this.x / value;
        int resY = this.y / value;
        return new IVec2(resX, resY);
    }

    @Override
    public @NotNull IVec2 divr(int value)
    {
        int resX = value / this.x;
        int resY = value / this.y;
        return new IVec2(resX, resY);
    }

    @Override
    public @NotNull IVec2 add(float value)
    {
        int resX = (int)(this.x + value);
        int resY = (int)(this.y + value);
        return new IVec2(resX, resY);
    }

    @Override
    public @NotNull IVec2 sub(float value)
    {
        int resX = (int)(this.x - value);
        int resY = (int)(this.y - value);
        return new IVec2(resX, resY);
    }

    @Override
    public @NotNull IVec2 subr(float value)
    {
        int resX =  (int)(value - this.x);
        int resY =  (int)(value - this.y);
        return new IVec2(resX, resY);
    }

    @Override
    public @NotNull IVec2 mult(float value)
    {
        int resX = (int)(this.x * value);
        int resY = (int)(this.y * value);
        return new IVec2(resX, resY);
    }

    @Override
    public @NotNull IVec2 div(float value)
    {
        int resX = (int)(this.x / value);
        int resY = (int)(this.y / value);
        return new IVec2(resX, resY);
    }

    @Override
    public @NotNull IVec2 divr(float value)
    {
        int resX = (int)(value / this.x);
        int resY = (int)(value / this.y);
        return new IVec2(resX, resY);
    }
}

