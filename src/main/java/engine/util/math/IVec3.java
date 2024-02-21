package engine.util.math;

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
    public IVec3(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public IVec3(int x)
    {
        this.x = x;
    }
    public IVec3() {}
    public IVec3(@NotNull IVec2 iVec2, int z)
    {
        x = iVec2.x;
        y = iVec2.y;
        this.z = z;
    }
    public IVec3(@NotNull IVec2 iVec2)
    {
        x = iVec2.x;
        y = iVec2.y;
    }
    public IVec3(@NotNull Vec2 vec2, int z)
    {
        x = (int) vec2.x;
        y = (int) vec2.y;
        this.z = z;
    }
    public IVec3(@NotNull Vec2 vec2)
    {
        x = (int) vec2.x;
        y = (int) vec2.y;
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
    //#region shortcuts
    public IVec2 xx()
    {
        return new IVec2(x, x);
    }
    public IVec2 xy()
    {
        return new IVec2(x, y);
    }
    public IVec2 xz()
    {
        return new IVec2(x, z);
    }
    public IVec2 yx()
    {
        return new IVec2(y, x);
    }
    public IVec2 yy()
    {
        return new IVec2(y, y);
    }
    public IVec2 yz()
    {
        return new IVec2(y, z);
    }
    public IVec2 zx()
    {
        return new IVec2(z, x);
    }
    public IVec2 zy()
    {
        return new IVec2(z, y);
    }
    public IVec2 zz()
    {
        return new IVec2(z, z);
    }
    public IVec3 xxx()
    {
        return new IVec3(x, x, x);
    }
    public IVec3 xxy()
    {
        return new IVec3(x, x, y);
    }
    public IVec3 xxz()
    {
        return new IVec3(x, x, z);
    }
    public IVec3 xyx()
    {
        return new IVec3(x, y, x);
    }
    public IVec3 xyy()
    {
        return new IVec3(x, y, y);
    }
    public IVec3 xyz()
    {
        return new IVec3(x, y, z);
    }
    public IVec3 xzx()
    {
        return new IVec3(x, z, x);
    }
    public IVec3 xzy()
    {
        return new IVec3(x, z, y);
    }
    public IVec3 xzz()
    {
        return new IVec3(x, z, z);
    }
    public IVec3 yxx()
    {
        return new IVec3(y, x, x);
    }
    public IVec3 yxy()
    {
        return new IVec3(y, x, y);
    }
    public IVec3 yxz()
    {
        return new IVec3(y, x, z);
    }
    public IVec3 yyx()
    {
        return new IVec3(y, y, x);
    }
    public IVec3 yyy()
    {
        return new IVec3(y, y, y);
    }
    public IVec3 yyz()
    {
        return new IVec3(y, y, z);
    }
    public IVec3 yzx()
    {
        return new IVec3(y, z, x);
    }
    public IVec3 yzy()
    {
        return new IVec3(y, z, y);
    }
    public IVec3 yzz()
    {
        return new IVec3(y, z, z);
    }
    public IVec3 zxx()
    {
        return new IVec3(z, x, x);
    }
    public IVec3 zxy()
    {
        return new IVec3(z, x, y);
    }
    public IVec3 zxz()
    {
        return new IVec3(z, x, z);
    }
    public IVec3 zyx()
    {
        return new IVec3(z, y, x);
    }
    public IVec3 zyy()
    {
        return new IVec3(z, y, y);
    }
    public IVec3 zyz()
    {
        return new IVec3(z, y, z);
    }
    public IVec3 zzx()
    {
        return new IVec3(z, z, x);
    }
    public IVec3 zzy()
    {
        return new IVec3(z, z, y);
    }
    public IVec3 zzz()
    {
        return new IVec3(z, z, z);
    }
    //#endregion
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