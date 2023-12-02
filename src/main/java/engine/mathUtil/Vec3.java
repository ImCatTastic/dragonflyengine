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
    public Vec3(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    public Vec3(double x)
    {
        this.x = x;
    }
    public Vec3() {}
    public Vec3(@NotNull Vec2 vec2, double z)
    {
        x = vec2.x;
        y = vec2.y;
        this.z = z;
    }
    public Vec3(@NotNull Vec2 vec2)
    {
        x = vec2.x;
        y = vec2.y;
    }
    public Vec3(@NotNull IVec2 iVec2, double z)
    {
        x = iVec2.x;
        y = iVec2.y;
        this.z = z;
    }
    public Vec3(@NotNull IVec2 iVec2)
    {
        x = iVec2.x;
        y = iVec2.y;
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
    //#region shortcuts
    public Vec2 xx()
    {
        return new Vec2(x, x);
    }
    public Vec2 xy()
    {
        return new Vec2(x, y);
    }
    public Vec2 xz()
    {
        return new Vec2(x, z);
    }
    public Vec2 yx()
    {
        return new Vec2(y, x);
    }
    public Vec2 yy()
    {
        return new Vec2(y, y);
    }
    public Vec2 yz()
    {
        return new Vec2(y, z);
    }
    public Vec2 zx()
    {
        return new Vec2(z, x);
    }
    public Vec2 zy()
    {
        return new Vec2(z, y);
    }
    public Vec2 zz()
    {
        return new Vec2(z, z);
    }
    public Vec3 xxx()
    {
        return new Vec3(x, x, x);
    }
    public Vec3 xxy()
    {
        return new Vec3(x, x, y);
    }
    public Vec3 xxz()
    {
        return new Vec3(x, x, z);
    }
    public Vec3 xyx()
    {
        return new Vec3(x, y, x);
    }
    public Vec3 xyy()
    {
        return new Vec3(x, y, y);
    }
    public Vec3 xyz()
    {
        return new Vec3(x, y, z);
    }
    public Vec3 xzx()
    {
        return new Vec3(x, z, x);
    }
    public Vec3 xzy()
    {
        return new Vec3(x, z, y);
    }
    public Vec3 xzz()
    {
        return new Vec3(x, z, z);
    }
    public Vec3 yxx()
    {
        return new Vec3(y, x, x);
    }
    public Vec3 yxy()
    {
        return new Vec3(y, x, y);
    }
    public Vec3 yxz()
    {
        return new Vec3(y, x, z);
    }
    public Vec3 yyx()
    {
        return new Vec3(y, y, x);
    }
    public Vec3 yyy()
    {
        return new Vec3(y, y, y);
    }
    public Vec3 yyz()
    {
        return new Vec3(y, y, z);
    }
    public Vec3 yzx()
    {
        return new Vec3(y, z, x);
    }
    public Vec3 yzy()
    {
        return new Vec3(y, z, y);
    }
    public Vec3 yzz()
    {
        return new Vec3(y, z, z);
    }
    public Vec3 zxx()
    {
        return new Vec3(z, x, x);
    }
    public Vec3 zxy()
    {
        return new Vec3(z, x, y);
    }
    public Vec3 zxz()
    {
        return new Vec3(z, x, z);
    }
    public Vec3 zyx()
    {
        return new Vec3(z, y, x);
    }
    public Vec3 zyy()
    {
        return new Vec3(z, y, y);
    }
    public Vec3 zyz()
    {
        return new Vec3(z, y, z);
    }
    public Vec3 zzx()
    {
        return new Vec3(z, z, x);
    }
    public Vec3 zzy()
    {
        return new Vec3(z, z, y);
    }
    public Vec3 zzz()
    {
        return new Vec3(z, z, z);
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