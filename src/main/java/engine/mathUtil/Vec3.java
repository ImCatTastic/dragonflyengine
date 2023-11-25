package engine.mathUtil;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Vec3 implements Vector<Vec3>
{
    public double x;
    public double y;
    public double z;
    public Vec3(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vec3(@NotNull Vec3 vec3)
    {
        this.x = vec3.x;
        this.y = vec3.y;
        this.z = vec3.z;
    }

    public Vec3(@NotNull Vec2 vec2, double value)
    {
        this.x = vec2.x;
        this.y = vec2.y;
        this.z = value;
    }

    public Vec3(@NotNull Vec4 vec4)
    {
        this.x = vec4.x;
        this.y = vec4.y;
        this.z = vec4.z;
    }
    public Vec2 getXY()
    {
        return new Vec2(x, y);
    }
    public @NotNull String toString()
    {
        return "(" + x + "|" + y + "|" + z +")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Vec3 other = (Vec3) obj;

        return this.x == other.x &&
            this.y == other.y &&
            this.z == other.z;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z);
    }

    public float getDistance(@NotNull Vec3 pos)
    {
        double deltaX = pos.x - x;
        double deltaY = pos.y - y;
        double deltaZ = pos.z - z;

        return (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }

    public @NotNull Vec3 add(@NotNull Vec3 vec)
    {
        double resX = this.x + vec.x;
        double resY = this.y + vec.y;
        double resZ = this.z + vec.z;
        return new Vec3(resX, resY, resZ);
    }

    public @NotNull Vec3 sub(@NotNull Vec3 vec)
    {
       double resX =  this.x - vec.x;
       double resY =  this.y - vec.y;
       double resZ =  this.z - vec.z;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 subr(@NotNull Vec3 vec)
    {
        double resX = vec.x - this.x;
        double resY = vec.y - this.y;
        double resZ = vec.z - this.z;
        return new Vec3(resX, resY, resZ);
    }

    public @NotNull Vec3 mult(@NotNull Vec3 vec)
    {
        double resX = this.x * vec.x;
        double resY = this.y * vec.y;
        double resZ = this.z * vec.z;
        return new Vec3(resX, resY, resZ);
    }

    public @NotNull Vec3 div(@NotNull Vec3 vec)
    {
        double resX = this.x / vec.x;
        double resY = this.y / vec.y;
        double resZ = this.z / vec.z;
        return new Vec3(resX, resY, resZ);
    }

    public @NotNull Vec3 divr(@NotNull Vec3 vec)
    {
        double resX = vec.x / this.x;
        double resY = vec.y / this.y;
        double resZ = vec.z / this.z;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 add(int value)
    {
        double resX = this.x + value;
        double resY = this.y + value;
        double resZ = this.z + value;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 sub(int value)
    {
        double resX = this.x - value;
        double resY = this.y - value;
        double resZ = this.z - value;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 subr(int value)
    {
        double resX = value - this.x;
        double resY = value - this.y;
        double resZ = value - this.z;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 mult(int value)
    {
        double resX = this.x * value;
        double resY = this.y * value;
        double resZ = this.z * value;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 div(int value)
    {
        double resX = this.x / value;
        double resY = this.y / value;
        double resZ = this.z / value;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 divr(int value)
    {
        double resX = value / this.x;
        double resY = value / this.y;
        double resZ = value / this.z;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 add(float value)
    {
        double resX = this.x + value;
        double resY = this.y + value;
        double resZ = this.z + value;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 sub(float value)
    {
        double resX = this.x - value;
        double resY = this.y - value;
        double resZ = this.z - value;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 subr(float value)
    {
        double resX =  value - this.x;
        double resY =  value - this.y;
        double resZ = value - this.z;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 mult(float value)
    {
        double resX = this.x * value;
        double resY = this.y * value;
        double resZ = this.z * value;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 div(float value)
    {
        double resX = this.x / value;
        double resY = this.y / value;
        double resZ = this.z / value;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 divr(float value)
    {
        double resX = value / this.x;
        double resY = value / this.y;
        double resZ = value / this.z;
        return new Vec3(resX, resY, resZ);
    }
}
