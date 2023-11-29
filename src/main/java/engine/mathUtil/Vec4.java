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
    public Vec4(@NotNull Vec2 vec2)
    {
        x = vec2.x;
        y = vec2.y;
        z = 0;
        w = 0;
    }
    public Vec4(@NotNull IVec2 iVec2)
    {
        x = iVec2.x;
        y = iVec2.y;
        z = 0;
        w = 0;
    }
    public Vec4(@NotNull Vec3 vec3)
    {
        x = vec3.x;
        y = vec3.y;
        z = vec3.z;
        w = 0;
    }
    public Vec4(@NotNull IVec3 iVec3)
    {
        x = iVec3.x;
        y = iVec3.y;
        z = iVec3.z;
        w = 0;
    }
    public Vec4(@NotNull Vec4 vec4)
    {
        x = vec4.x;
        y = vec4.y;
        z = vec4.z;
        w = vec4.w;
    }
    public Vec4(@NotNull IVec4 iVec4)
    {
        x = iVec4.x;
        y = iVec4.y;
        z = iVec4.z;
        w = iVec4.w;
    }
    @Override
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

        return x == other.x &&
                y == other.y &&
                z == other.z &&
                w == other.w;
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z, w);
    }
    public double getDistance(@NotNull Vec4 pos)
    {
        double deltaX = pos.x - x;
        double deltaY = pos.y - y;
        double deltaZ = pos.z - z;
        double deltaW = pos.w - w;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ) + (deltaW * deltaW));
    }
    public double getDistance(@NotNull IVec4 pos)
    {
        double deltaX = pos.x - x;
        double deltaY = pos.y - y;
        double deltaZ = pos.z - z;
        double deltaW = pos.w - w;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ) + (deltaW * deltaW));
    }
    public Vec4 add(@NotNull Vec4 vec4)
    {
		return new Vec4(x + vec4.x, y + vec4.y, z + vec4.z, w + vec4.w);
    }
    public Vec4 sub(@NotNull Vec4 vec4)
    {
		return new Vec4(x - x - vec4.x, y - y - vec4.y, z - z - vec4.z, w - w - vec4.w);
    }
    public Vec4 subr(@NotNull Vec4 vec4)
    {
		return new Vec4(vec4.x - x, vec4.y - y, vec4.z - z, vec4.w - w);
    }
    public Vec4 mult(@NotNull Vec4 vec4)
    {
		return new Vec4(x * vec4.x, y * vec4.y, z * vec4.z, w * vec4.w);
    }
    public Vec4 div(@NotNull Vec4 vec4)
    {
		return new Vec4(x / vec4.x, y / vec4.y, z / vec4.z, w / vec4.w);
    }
    public Vec4 divr(@NotNull Vec4 vec4)
    {
		return new Vec4(vec4.x / x, vec4.y / y, vec4.z / z, vec4.w / w);
    }
    public Vec4 add(@NotNull IVec4 iVec4)
    {
		return new Vec4(x + iVec4.x, y + iVec4.y, z + iVec4.z, w + iVec4.w);
    }
    public Vec4 sub(@NotNull IVec4 iVec4)
    {
		return new Vec4(x - iVec4.x, y - iVec4.y, z - iVec4.z, w - iVec4.w);
    }
    public Vec4 subr(@NotNull IVec4 iVec4)
    {
		return new Vec4(iVec4.x - x, iVec4.y - y, iVec4.z - z, iVec4.w - w);
    }
    public Vec4 mult(@NotNull IVec4 iVec4)
    {
		return new Vec4(x * iVec4.x, y * iVec4.y, z * iVec4.z, w * iVec4.w);
    }
    public Vec4 div(@NotNull IVec4 iVec4)
    {
		return new Vec4(x / iVec4.x, y / iVec4.y, z / iVec4.z, w / iVec4.w);
    }
    public Vec4 divr(@NotNull IVec4 iVec4)
    {
		return new Vec4(iVec4.x / x, iVec4.y / y, iVec4.z / z, iVec4.w / w);
    }
    @Override
    public Vec4 add(double value)
    {
		return new Vec4(x + value, y + value, z + value, w + value);
    }
    @Override
    public Vec4 sub(double value)
    {
		return new Vec4(x - value, y - value, z - value, w - value);
    }
    @Override
    public Vec4 subr(double value)
    {
		return new Vec4(value - x, value - y, value - y, value - w);
    }
    @Override
    public Vec4 mult(double value)
    {
		return new Vec4(x * value, y * value, z * value, w * value);
    }
    @Override
    public Vec4 div(double value)
    {
		return new Vec4(x / value, y / value, z / value, w / value);
    }
    @Override
    public Vec4 divr(double value)
    {
		return new Vec4(value / x, value / y, value / y, value / w);
    }
    @Override
    public Vec4 add(int value)
    {
		return new Vec4(x + value, y + value, z + value, w + value);
    }
    @Override
    public Vec4 sub(int value)
    {
		return new Vec4(x - value, y - value, z - value, w - value);
    }
    @Override
    public Vec4 subr(int value)
    {
		return new Vec4(value - x, value - y, value - z, value - w);
    }
    @Override
    public Vec4 mult(int value)
    {
		return new Vec4(x * value, y * value, z * value, w * value);
    }
    @Override
    public Vec4 div(int value)
    {
		return new Vec4(x / value, y / value, z / value, w / value);
    }
    @Override
    public Vec4 divr(int value)
    {
		return new Vec4(value / x, value / y, value / z, value / w);
    }
}