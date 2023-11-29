package engine.mathUtil;

import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class IVec4 implements Vector<IVec4>
{
    public int x;
    public int y;
    public int z;
    public int w;
    public IVec4(int x, int y, int z, int w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    public IVec4(@NotNull IVec2 iVec2)
    {
        x = iVec2.x;
        y = iVec2.y;
        z = 0;
        w = 0;
    }
    public IVec4(@NotNull Vec2 vec2)
    {
        x = (int) vec2.x;
        y = (int) vec2.y;
        z = 0;
        w = 0;
    }
    public IVec4(@NotNull IVec3 iVec3)
    {
        x = iVec3.x;
        y = iVec3.y;
        z = iVec3.z;
        w = 0;
    }
    public IVec4(@NotNull Vec3 vec3)
    {
        x = (int) vec3.x;
        y = (int) vec3.y;
        z = (int) vec3.z;
        w = 0;
    }
    public IVec4(@NotNull IVec4 iVec4)
    {
        x = iVec4.x;
        y = iVec4.y;
        z = iVec4.z;
        w = iVec4.w;
    }
    public IVec4(@NotNull Vec4 vec4)
    {
        x = (int) vec4.x;
        y = (int) vec4.y;
        z = (int) vec4.z;
        w = (int) vec4.w;
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

        IVec4 other = (IVec4) obj;

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
    public double getDistance(@NotNull IVec4 pos)
    {
        double deltaX = pos.x - x;
        double deltaY = pos.y - y;
        double deltaZ = pos.z - z;
        double deltaW = pos.w - w;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ) + (deltaW * deltaW));
    }
    public double getDistance(@NotNull Vec4 pos)
    {
        double deltaX = pos.x - x;
        double deltaY = pos.y - y;
        double deltaZ = pos.z - z;
        double deltaW = pos.w - w;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ) + (deltaW * deltaW));
    }
    public IVec4 add(@NotNull IVec4 iVec4)
    {
		return new IVec4(x + iVec4.x, y + iVec4.y, z + iVec4.z, w  + iVec4.w);
    }
    public IVec4 sub(@NotNull IVec4 iVec4)
    {
		return new IVec4(x - iVec4.x, y - iVec4.y, z - iVec4.z, w - iVec4.w);
    }
    public IVec4 subr(@NotNull IVec4 iVec4)
    {
        return new IVec4(iVec4.x - x, iVec4.y - y, iVec4.z - z, iVec4.w - w);
    }
    public IVec4 mult(@NotNull IVec4 iVec4)
    {
		return new IVec4(x * iVec4.x, y * iVec4.y, z * iVec4.z, w  * iVec4.w);
    }
    public IVec4 div(@NotNull IVec4 iVec4)
    {
		return new IVec4(x / iVec4.x, y / iVec4.y, z / iVec4.z, w  / iVec4.w);
    }
    public IVec4 divr(@NotNull IVec4 iVec4)
    {
        return new IVec4(iVec4.x / x, iVec4.y / y, iVec4.z / z, iVec4.w / w);
    }
    public IVec4 add(@NotNull Vec4 vec4)
    {
		return new IVec4(x + (int) vec4.x, y + (int) vec4.y, z + (int) vec4.z, w  + (int) vec4.w);
    }
    public IVec4 sub(@NotNull Vec4 vec4)
    {
		return new IVec4(x - (int) vec4.x, y - (int) vec4.y, z - (int) vec4.z, w  - (int) vec4.w);
    }
    public IVec4 subr(@NotNull Vec4 vec4)
    {
        return new IVec4((int) vec4.x - x, (int) vec4.y - y, (int) vec4.z - z, (int) vec4.w - w);
    }
    public IVec4 mult(@NotNull Vec4 vec4)
    {
		return new IVec4(x * (int) vec4.x, y * (int) vec4.y, z * (int) vec4.z, w  * (int) vec4.w);
    }
    public IVec4 div(@NotNull Vec4 vec4)
    {
		return new IVec4(x / (int) vec4.x, y / (int) vec4.y, z / (int) vec4.z, w  / (int) vec4.w);
    }
    public IVec4 divr(@NotNull Vec4 vec4)
    {
        return new IVec4((int) vec4.x / x, (int) vec4.y / y, (int) vec4.z / z, (int) vec4.w / w);
    }
    @Override
    public IVec4 add(double value)
    {
		return new IVec4(x + (int) value, y + (int) value, z + (int) value, w  + (int) value);
    }
    @Override
    public IVec4 sub(double value)
    {
		return new IVec4(x - (int) value, y - (int) value, z - (int) value, w  - (int) value);
    }
    @Override
    public IVec4 subr(double value)
    {
        return new IVec4((int)(value - x), (int)(value - y), (int)(value - z), (int)(value - w));
    }
    @Override
    public IVec4 mult(double value)
    {
		return new IVec4(x * (int)(value), y * (int)(value), z * (int)(value), w  * (int)(value));
    }
    @Override
    public IVec4 div(double value)
    {
		return new IVec4(x / (int)(value), y / (int)(value), z / (int)(value), w  / (int)(value));
    }
    @Override
    public IVec4 divr(double value)
    {
        return new IVec4((int)(value / x), (int)(value / y), (int)(value / z), (int)(value / w));
    }
    @Override
    public IVec4 add(int value)
    {
		return new IVec4(x + value, y + value, z + value, w  + value);
    }
    @Override
    public IVec4 sub(int value)
    {
		return new IVec4(x - value, y - value, z - value, w  - value);
    }
    @Override
    public IVec4 subr(int value)
    {
        return new IVec4(value - x, value - y, value - z, value - w);
    }
    @Override
    public IVec4 mult(int value)
    {
		return new IVec4(x * value, y * value, z * value, w  * value);
    }
    @Override
    public IVec4 div(int value)
    {
		return new IVec4(x / value, y / value, z / value, w  / value);
    }
    @Override
    public IVec4 divr(int value)
    {
        return new IVec4(value / x, value / y, value / z, value / w);
    }
}