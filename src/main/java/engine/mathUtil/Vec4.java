package engine.mathUtil;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Vec4 implements Vector<Vec4>
{
    public double x;
    public double y;
    public double z;
    public double w;
    public Vec4(double x, double y, double z, double w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    public Vec4(@NotNull Vec4 vec4)
    {
        this.x = vec4.x;
        this.y = vec4.y;
        this.z = vec4.z;
        this.w = vec4.z;
    }

    public Vec4(@NotNull Vec3 vec3, double value)
    {
        this.x = vec3.x;
        this.y = vec3.y;
        this.z = vec3.z;
        this.w = value;
    }

    public Vec3 getXYZ()
    {
        return new Vec3(x, y, z);
    }

    public Vec2 getXY()
    {
        return new Vec2(x, y);
    }

    public @NotNull String toString()
    {
        return "(" + x + "|" + y + "|" + z + "|" + w +")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Vec4 other = (Vec4) obj;

        return this.x == other.x &&
            this.y == other.y &&
            this.z == other.z &&
            this.w == other.w;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z, w);
    }

    public float getDistance(@NotNull Vec4 pos)
    {
        double deltaX = pos.x - x;
        double deltaY = pos.y - y;
        double deltaZ = pos.z - z;
        double deltaW = pos.w - w;

        return (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ) + (deltaW * deltaW));
    }

    public @NotNull Vec4 add(@NotNull Vec4 vec)
    {
        double resX = this.x + vec.x;
        double resY = this.y + vec.y;
        double resZ = this.z + vec.z;
        double resW = this.w + vec.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    public @NotNull Vec4 sub(@NotNull Vec4 vec)
    {
        double resX = this.x - vec.x;
        double resY = this.y - vec.y;
        double resZ = this.z - vec.z;
        double resW = this.w - vec.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 subr(@NotNull Vec4 vec)
    {
        double resX = vec.x - this.x;
        double resY = vec.y - this.y;
        double resZ = vec.z - this.z;
        double resW = vec.w - this.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    public @NotNull Vec4 mult(@NotNull Vec4 vec)
    {
        double resX = this.x * vec.x;
        double resY = this.y * vec.y;
        double resZ = this.z * vec.z;
        double resW = this.w * vec.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    public @NotNull Vec4 div(@NotNull Vec4 vec)
    {
        double resX = this.x / vec.x;
        double resY = this.y / vec.y;
        double resZ = this.z / vec.z;
        double resW = this.w / vec.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    public @NotNull Vec4 divr(@NotNull Vec4 vec)
    {
        double resX = vec.x / this.x;
        double resY = vec.y / this.y;
        double resZ = vec.z / this.z;
        double resW = vec.w / this.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 add(int value)
    {
        double resX = this.x + value;
        double resY = this.y + value;
        double resZ = this.z + value;
        double resW = this.w + value;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 sub(int value)
    {
        double resX = this.x - value;
        double resY = this.y - value;
        double resZ = this.z - value;
        double resW = this.w - value;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 subr(float value)
    {
        double resX = value - this.x;
        double resY = value - this.y;
        double resZ = value - this.z;
        double resW = value - this.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 mult(int value)
    {
        double resX = this.x * value;
        double resY = this.y * value;
        double resZ = this.z * value;
        double resW = this.w * value;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 div(int value)
    {
        double resX = this.x / value;
        double resY = this.y / value;
        double resZ = this.z / value;
        double resW = this.w / value;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 divr(int value)
    {
        double resX = value / this.x;
        double resY = value / this.y;
        double resZ = value / this.z;
        double resW = value / this.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 add(float value)
    {
        double resX = this.x + value;
        double resY = this.y + value;
        double resZ = this.z + value;
        double resW = this.w + value;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 sub(float value)
    {
        double resX = this.x - value;
        double resY = this.y - value;
        double resZ = this.z - value;
        double resW = this.w - value;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 subr(int value)
    {
        double resX = value - this.x;
        double resY = value - this.y;
        double resZ = value - this.z;
        double resW = value - this.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 mult(float value)
    {
        double resX = this.x * value;
        double resY = this.y * value;
        double resZ = this.z * value;
        double resW = this.w * value;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 div(float value)
    {
        double resX = this.x / value;
        double resY = this.y / value;
        double resZ = this.z / value;
        double resW = this.w / value;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 divr(float value)
    {
        double resX = value / this.x;
        double resY = value / this.y;
        double resZ = value / this.z;
        double resW = value / this.w;
        return new Vec4(resX, resY, resZ, resW);
    }
}
