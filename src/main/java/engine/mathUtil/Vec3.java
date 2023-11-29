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
    public Vec3(@NotNull Vec2 vec2)
    {
        x = vec2.x;
        y = vec2.y;
        z = 0;
    }
    public Vec3(@NotNull IVec2 iVec2)
    {
        x = iVec2.x;
        y = iVec2.y;
        z = 0;
    }
    public Vec3(@NotNull Vec3 vec3)
    {
        x = vec3.x;
        y = vec3.y;
        z = vec3.z;
    }
    public Vec3(@NotNull IVec3 iVec3)
    {
        x = iVec3.x;
        y = iVec3.y;
        z = iVec3.z;
    }
    public Vec3(@NotNull Vec4 vec4)
    {
        x = vec4.x;
        y = vec4.y;
        z = vec4.z;
    }
    public Vec3(@NotNull IVec4 iVec4)
    {
        x = iVec4.x;
        y = iVec4.y;
        z = iVec4.z;
    }
    @Override
    public @NotNull String toString()
    {
        return "(" + x + "|" + y + "|" + z + ")";
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Vec3 other = (Vec3) obj;

        return x == other.x &&
                y == other.y &&
                z == other.z;
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z);
    }
    public double getDistance(@NotNull Vec3 vec3)
    {
        double deltaX = vec3.x - x;
        double deltaY = vec3.y - y;
        double deltaZ = vec3.z - z;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }
    public double getDistance(@NotNull IVec3 iVec3)
    {
        double deltaX = iVec3.x - x;
        double deltaY = iVec3.y - y;
        double deltaZ = iVec3.z - z;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }
    public Vec3 add(@NotNull Vec3 vec3)
    {
		return new Vec3(x + vec3.x, y + vec3.y, z + vec3.z);
    }
    public Vec3 sub(@NotNull Vec3 vec3)
    {
		return new Vec3(x - vec3.x, y - vec3.y, z - vec3.z);
    }
    public Vec3 subr(@NotNull Vec3 vec3)
    {
		return new Vec3(vec3.x - x, vec3.y - y, vec3.z - z);
    }
    public Vec3 mult(@NotNull Vec3 vec3)
    {
		return new Vec3(x * vec3.x, y * vec3.y, z * vec3.z);
    }
    public Vec3 div(@NotNull Vec3 vec3)
    {
		return new Vec3(x / vec3.x, y / vec3.y, z / vec3.z);
    }
    public Vec3 divr(@NotNull Vec3 vec3)
    {
		return new Vec3(vec3.x / x, vec3.y / y, vec3.z / z);
    }
    public Vec3 add(@NotNull IVec3 iVec3)
    {
		return new Vec3(x + iVec3.x, y + iVec3.y, z + iVec3.z);
    }
    public Vec3 sub(@NotNull IVec3 iVec3)
    {
		return new Vec3(x - iVec3.x, y - iVec3.y, z - iVec3.z);
    }
    public Vec3 subr(@NotNull IVec3 iVec3)
    {
		return new Vec3(iVec3.x - x, iVec3.y - y, iVec3.z - z);
    }
    public Vec3 mult(@NotNull IVec3 iVec3)
    {
		return new Vec3(x * iVec3.x, y * iVec3.y, z * iVec3.z);
    }
    public Vec3 div(@NotNull IVec3 iVec3)
    {
		return new Vec3(x / iVec3.x, y / iVec3.y, z / iVec3.z);
    }
    public Vec3 divr(@NotNull IVec3 iVec3)
    {
		return new Vec3(iVec3.x / x, iVec3.y / y, iVec3.z / z);
    }
    @Override
    public Vec3 add(double value)
    {
		return new Vec3(x + value, y + value, z + value);
    }
    @Override
    public Vec3 sub(double value)
    {
		return new Vec3(x - value, y - value, z - value);
    }
    @Override
    public Vec3 subr(double value)
    {
		return new Vec3(value - x, value - y, value - y);
    }
    @Override
    public Vec3 mult(double value)
    {
		return new Vec3(x * value, y * value, z * value);
    }
    @Override
    public Vec3 div(double value)
    {
		return new Vec3(x / value, y / value, z / value);
    }
    @Override
    public Vec3 divr(double value)
    {
		return new Vec3(value / x, value / y, value / y);
    }
    @Override
    public Vec3 add(int value)
    {
		return new Vec3(x + value, y + value, z + value);
    }
    @Override
    public Vec3 sub(int value)
    {
		return new Vec3(x - value, y - value, z - value);
    }
    @Override
    public Vec3 subr(int value)
    {
		return new Vec3(value - x, value - y, value - z);
    }
    @Override
    public Vec3 mult(int value)
    {
		return new Vec3(x * value, y * value, z * value);
    }
    @Override
    public Vec3 div(int value)
    {
		return new Vec3(x / value, y / value, z / value);
    }
    @Override
    public Vec3 divr(int value)
    {
		return new Vec3(value / x, value / y, value / z);
    }
}