package engine.mathUtil;

import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class IVec3 implements Vector<IVec3>
{
    public int x;
    public int y;
    public int z;
    public IVec3(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public IVec3(@NotNull IVec2 iVec2)
    {
        x = iVec2.x;
        y = iVec2.y;
        z = 0;
    }
    public IVec3(@NotNull Vec2 vec2)
    {
        x = (int) vec2.x;
        y = (int) vec2.y;
        z = 0;
    }
    public IVec3(@NotNull IVec3 iVec3)
    {
        x = iVec3.x;
        y = iVec3.y;
        z = iVec3.z;
    }
    public IVec3(@NotNull Vec3 vec3)
    {
        x = (int) vec3.x;
        y = (int) vec3.y;
        z = (int) vec3.z;
    }
    public IVec3(@NotNull IVec4 iVec4)
    {
        x = iVec4.x;
        y = iVec4.y;
        z = iVec4.z;
    }
    public IVec3(@NotNull Vec4 vec4)
    {
        x = (int) vec4.x;
        y = (int) vec4.y;
        z = (int) vec4.z;
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

        IVec3 other = (IVec3) obj;

        return x == other.x &&
                y == other.y &&
                z == other.z;
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z);
    }
    public double getDistance(@NotNull IVec3 iVec3)
    {
        double deltaX = iVec3.x - x;
        double deltaY = iVec3.y - y;
        double deltaZ = iVec3.z - z;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }
    public double getDistance(@NotNull Vec3 vec3)
    {
        double deltaX = vec3.x - x;
        double deltaY = vec3.y - y;
        double deltaZ = vec3.z - z;
        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }
    public IVec3 add(@NotNull IVec3 iVec3)
    {
		return new IVec3(x + iVec3.x, y + iVec3.y, z  + iVec3.z);
    }
    public IVec3 sub(@NotNull IVec3 iVec3)
    {
		return new IVec3(x - iVec3.x, y - iVec3.y, z  - iVec3.z);
    }
    public IVec3 subr(@NotNull IVec3 iVec3)
    {
        return new IVec3(iVec3.x - x, iVec3.y - y, iVec3.z - z);
    }
    public IVec3 mult(@NotNull IVec3 iVec3)
    {
		return new IVec3(x * iVec3.x, y * iVec3.y, z  * iVec3.z);
    }
    public IVec3 div(@NotNull IVec3 iVec3)
    {
		return new IVec3(x / iVec3.x, y / iVec3.y, z  / iVec3.z);
    }
    public IVec3 divr(@NotNull IVec3 iVec3)
    {
        return new IVec3(iVec3.x / x, iVec3.y / y, iVec3.z / z);
    }
    public IVec3 add(@NotNull Vec3 vec3)
    {
		return new IVec3(x + (int) vec3.x, y + (int) vec3.y, z  + (int) vec3.z);
    }
    public IVec3 sub(@NotNull Vec3 vec3)
    {
		return new IVec3(x - (int) vec3.x, y - (int) vec3.y, z  - (int) vec3.z);
    }
    public IVec3 subr(@NotNull Vec3 vec3)
    {
        return new IVec3((int) vec3.x - x, (int) vec3.y - y, (int) vec3.z - z);
    }
    public IVec3 mult(@NotNull Vec3 vec3)
    {
		return new IVec3(x * (int) vec3.x, y * (int) vec3.y, z  * (int) vec3.z);
    }
    public IVec3 div(@NotNull Vec3 vec3)
    {
		return new IVec3(x / (int) vec3.x, y / (int) vec3.y, z  / (int) vec3.z);
    }
    public IVec3 divr(@NotNull Vec3 vec3)
    {
        return new IVec3((int) vec3.x / x, (int) vec3.y / y, (int) vec3.z / z);
    }
    @Override
    public IVec3 add(double value)
    {
		return new IVec3(x + (int) value, y + (int) value, z  + (int) value);
    }
    @Override
    public IVec3 sub(double value)
    {
		return new IVec3(x - (int) value, y - (int) value, z  - (int) value);
    }
    @Override
    public IVec3 subr(double value)
    {
        return new IVec3((int) (value - x), (int) (value - y), (int) (value - z));
    }
    @Override
    public IVec3 mult(double value)
    {
		return new IVec3(x * (int) (value), y * (int) (value), z  * (int) (value));
    }
    @Override
    public IVec3 div(double value)
    {
		return new IVec3(x / (int) (value), y / (int) (value), z  / (int) (value));
    }
    @Override
    public IVec3 divr(double value)
    {
        return new IVec3((int) (value / x), (int) (value / y), (int) (value / z));
    }
    @Override
    public IVec3 add(int value)
    {
		return new IVec3(x + value, y + value, z  + value);
    }
    @Override
    public IVec3 sub(int value)
    {
		return new IVec3(x - value, y - value, z  - value);
    }
    @Override
    public IVec3 subr(int value)
    {
        return new IVec3(value - x, value - y, value - z);
    }
    @Override
    public IVec3 mult(int value)
    {
		return new IVec3(x * value, y * value, z  * value);
    }
    @Override
    public IVec3 div(int value)
    {
		return new IVec3(x / value, y / value, z  / value);
    }
    @Override
    public IVec3 divr(int value)
    {
        return new IVec3(value / x, value / y, value / z);
    }
}