package engine.util.math;

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
    public IVec4(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public IVec4(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public IVec4(int x)
    {
        this.x = x;
    }
    public IVec4() {}
    public IVec4(@NotNull Vec2 vec2, int z, int w)
    {
        x = (int) vec2.x;
        y = (int) vec2.y;
        this.z = z;
        this.w = w;
    }
    public IVec4(@NotNull Vec2 vec2, int z)
    {
        x = (int) vec2.x;
        y = (int) vec2.y;
        this.z = z;
    }
    public IVec4(@NotNull Vec2 vec2)
    {
        x = (int) vec2.x;
        y = (int) vec2.y;
    }
    public IVec4(@NotNull IVec2 iVec2, int z, int w)
    {
        x = iVec2.x;
        y = iVec2.y;
        this.z = z;
        this.w = w;
    }
    public IVec4(@NotNull IVec2 iVec2, int z)
    {
        x = iVec2.x;
        y = iVec2.y;
        this.z = z;
    }
    public IVec4(@NotNull IVec2 iVec2)
    {
        x = iVec2.x;
        y = iVec2.y;
    }
    public IVec4(@NotNull Vec3 vec3, int w)
    {
        x = (int) vec3.x;
        y = (int) vec3.y;
        z = (int) vec3.z;
        this.w = w;
    }
    public IVec4(@NotNull Vec3 vec3)
    {
        x = (int) vec3.x;
        y = (int) vec3.y;
        z = (int) vec3.z;
    }
    public IVec4(@NotNull IVec3 iVec3, int w)
    {
        x = iVec3.x;
        y = iVec3.y;
        z = iVec3.z;
        this.w = w;
    }
    public IVec4(@NotNull IVec3 iVec3)
    {
        x = iVec3.x;
        y = iVec3.y;
        z = iVec3.z;
    }
    public IVec4(@NotNull Vec4 vec4)
    {
        x = (int) vec4.x;
        y = (int) vec4.y;
        z = (int) vec4.z;
        w = (int) vec4.w;
    }
    public IVec4(@NotNull IVec4 iVec4)
    {
        x = iVec4.x;
        y = iVec4.y;
        z = iVec4.z;
        w = iVec4.w;
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
    public IVec2 xw() 
    {
        return new IVec2(x, w);
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
    public IVec2 yw() 
    {
        return new IVec2(y, w);
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
    public IVec2 zw() 
    {
        return new IVec2(z, w);
    }
    public IVec2 wx()
    {
        return new IVec2(w, x);
    }
    public IVec2 wy() 
    {
        return new IVec2(w, y);
    }
    public IVec2 wz()
    {
        return new IVec2(w, z);
    }
    public IVec2 ww() 
    {
        return new IVec2(w, w);
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
    public IVec3 xxw()
    {
        return new IVec3(x, x, w);
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
    public IVec3 xyw()
    {
        return new IVec3(x, y, w);
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
    public IVec3 xzw()
    {
        return new IVec3(x, z, w);
    }
    public IVec3 xwx()
    {
        return new IVec3(x, w, x);
    }
    public IVec3 xwy()
    {
        return new IVec3(x, w, y);
    }
    public IVec3 xwz()
    {
        return new IVec3(x, w, z);
    }
    public IVec3 xww()
    {
        return new IVec3(x, w, w);
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
    public IVec3 yxw()
    {
        return new IVec3(y, x, w);
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
    public IVec3 yyw()
    {
        return new IVec3(y, y, w);
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
    public IVec3 yzw()
    {
        return new IVec3(y, z, w);
    }
    public IVec3 ywx()
    {
        return new IVec3(y, w, x);
    }
    public IVec3 ywy()
    {
        return new IVec3(y, w, y);
    }
    public IVec3 ywz()
    {
        return new IVec3(y, w, z);
    }
    public IVec3 yww()
    {
        return new IVec3(y, w, w);
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
    public IVec3 zxw()
    {
        return new IVec3(z, x, w);
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
    public IVec3 zyw()
    {
        return new IVec3(z, y, w);
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
    public IVec3 zzw()
    {
        return new IVec3(z, z, w);
    }
    public IVec3 zwx()
    {
        return new IVec3(z, w, x);
    }
    public IVec3 zwy()
    {
        return new IVec3(z, w, y);
    }
    public IVec3 zwz()
    {
        return new IVec3(z, w, z);
    }
    public IVec3 zww()
    {
        return new IVec3(z, w, w);
    }
    public IVec3 wxx()
    {
        return new IVec3(w, x, x);
    }
    public IVec3 wxy()
    {
        return new IVec3(w, x, y);
    }
    public IVec3 wxz()
    {
        return new IVec3(w, x, z);
    }
    public IVec3 wxw()
    {
        return new IVec3(w, x, w);
    }
    public IVec3 wyx()
    {
        return new IVec3(w, y, x);
    }
    public IVec3 wyy()
    {
        return new IVec3(w, y, y);
    }
    public IVec3 wyz()
    {
        return new IVec3(w, y, z);
    }
    public IVec3 wyw()
    {
        return new IVec3(w, y, w);
    }
    public IVec3 wzx()
    {
        return new IVec3(w, z, x);
    }
    public IVec3 wzy()
    {
        return new IVec3(w, z, y);
    }
    public IVec3 wzz()
    {
        return new IVec3(w, z, z);
    }
    public IVec3 wzw()
    {
        return new IVec3(w, z, w);
    }
    public IVec3 wwx()
    {
        return new IVec3(w, w, x);
    }
    public IVec3 wwy()
    {
        return new IVec3(w, w, y);
    }
    public IVec3 wwz()
    {
        return new IVec3(w, w, z);
    }
    public IVec3 www()
    {
        return new IVec3(w, w, w);
    }
    public IVec4 xxxx()
    {
        return new IVec4(x, x, x, x);
    }
    public IVec4 xxxy()
    {
        return new IVec4(x, x, x, y);
    }
    public IVec4 xxxz()
    {
        return new IVec4(x, x, x, z);
    }
    public IVec4 xxxw()
    {
        return new IVec4(x, x, x, w);
    }
    public IVec4 xxyx()
    {
        return new IVec4(x, x, y, x);
    }
    public IVec4 xxyy()
    {
        return new IVec4(x, x, y, y);
    }
    public IVec4 xxyz()
    {
        return new IVec4(x, x, y, z);
    }
    public IVec4 xxyw()
    {
        return new IVec4(x, x, y, w);
    }
    public IVec4 xxzx()
    {
        return new IVec4(x, x, z, x);
    }
    public IVec4 xxzy()
    {
        return new IVec4(x, x, z, y);
    }
    public IVec4 xxzz()
    {
        return new IVec4(x, x, z, z);
    }
    public IVec4 xxzw()
    {
        return new IVec4(x, x, z, w);
    }
    public IVec4 xxwx()
    {
        return new IVec4(x, x, w, x);
    }
    public IVec4 xxwy()
    {
        return new IVec4(x, x, w, y);
    }
    public IVec4 xxwz()
    {
        return new IVec4(x, x, w, z);
    }
    public IVec4 xxww()
    {
        return new IVec4(x, x, w, w);
    }
    public IVec4 xyxx()
    {
        return new IVec4(x, y, x, x);
    }
    public IVec4 xyxy()
    {
        return new IVec4(x, y, x, y);
    }
    public IVec4 xyxz()
    {
        return new IVec4(x, y, x, z);
    }
    public IVec4 xyxw()
    {
        return new IVec4(x, y, x, w);
    }
    public IVec4 xyyx()
    {
        return new IVec4(x, y, y, x);
    }
    public IVec4 xyyy()
    {
        return new IVec4(x, y, y, y);
    }
    public IVec4 xyyz()
    {
        return new IVec4(x, y, y, z);
    }
    public IVec4 xyyw()
    {
        return new IVec4(x, y, y, w);
    }
    public IVec4 xyzx()
    {
        return new IVec4(x, y, z, x);
    }
    public IVec4 xyzy()
    {
        return new IVec4(x, y, z, y);
    }
    public IVec4 xyzz()
    {
        return new IVec4(x, y, z, z);
    }
    public IVec4 xyzw()
    {
        return new IVec4(x, y, z, w);
    }
    public IVec4 xywx()
    {
        return new IVec4(x, y, w, x);
    }
    public IVec4 xywy()
    {
        return new IVec4(x, y, w, y);
    }
    public IVec4 xywz()
    {
        return new IVec4(x, y, w, z);
    }
    public IVec4 xyww()
    {
        return new IVec4(x, y, w, w);
    }
    public IVec4 xzxx()
    {
        return new IVec4(x, z, x, x);
    }
    public IVec4 xzxy()
    {
        return new IVec4(x, z, x, y);
    }
    public IVec4 xzxz()
    {
        return new IVec4(x, z, x, z);
    }
    public IVec4 xzxw()
    {
        return new IVec4(x, z, x, w);
    }
    public IVec4 xzyx()
    {
        return new IVec4(x, z, y, x);
    }
    public IVec4 xzyy()
    {
        return new IVec4(x, z, y, y);
    }
    public IVec4 xzyz()
    {
        return new IVec4(x, z, y, z);
    }
    public IVec4 xzyw()
    {
        return new IVec4(x, z, y, w);
    }
    public IVec4 xzzx()
    {
        return new IVec4(x, z, z, x);
    }
    public IVec4 xzzy()
    {
        return new IVec4(x, z, z, y);
    }
    public IVec4 xzzz()
    {
        return new IVec4(x, z, z, z);
    }
    public IVec4 xzzw()
    {
        return new IVec4(x, z, z, w);
    }
    public IVec4 xzwx()
    {
        return new IVec4(x, z, w, x);
    }
    public IVec4 xzwy()
    {
        return new IVec4(x, z, w, y);
    }
    public IVec4 xzwz()
    {
        return new IVec4(x, z, w, z);
    }
    public IVec4 xzww()
    {
        return new IVec4(x, z, w, w);
    }
    public IVec4 xwxx()
    {
        return new IVec4(x, w, x, x);
    }
    public IVec4 xwxy()
    {
        return new IVec4(x, w, x, y);
    }
    public IVec4 xwxz()
    {
        return new IVec4(x, w, x, z);
    }
    public IVec4 xwxw()
    {
        return new IVec4(x, w, x, w);
    }
    public IVec4 xwyx()
    {
        return new IVec4(x, w, y, x);
    }
    public IVec4 xwyy()
    {
        return new IVec4(x, w, y, y);
    }
    public IVec4 xwyz()
    {
        return new IVec4(x, w, y, z);
    }
    public IVec4 xwyw()
    {
        return new IVec4(x, w, y, w);
    }
    public IVec4 xwzx()
    {
        return new IVec4(x, w, z, x);
    }
    public IVec4 xwzy()
    {
        return new IVec4(x, w, z, y);
    }
    public IVec4 xwzz()
    {
        return new IVec4(x, w, z, z);
    }
    public IVec4 xwzw()
    {
        return new IVec4(x, w, z, w);
    }
    public IVec4 xwwx()
    {
        return new IVec4(x, w, w, x);
    }
    public IVec4 xwwy()
    {
        return new IVec4(x, w, w, y);
    }
    public IVec4 xwwz()
    {
        return new IVec4(x, w, w, z);
    }
    public IVec4 xwww()
    {
        return new IVec4(x, w, w, w);
    }
    public IVec4 yxxx()
    {
        return new IVec4(y, x, x, x);
    }
    public IVec4 yxxy()
    {
        return new IVec4(y, x, x, y);
    }
    public IVec4 yxxz()
    {
        return new IVec4(y, x, x, z);
    }
    public IVec4 yxxw()
    {
        return new IVec4(y, x, x, w);
    }
    public IVec4 yxyx()
    {
        return new IVec4(y, x, y, x);
    }
    public IVec4 yxyy()
    {
        return new IVec4(y, x, y, y);
    }
    public IVec4 yxyz()
    {
        return new IVec4(y, x, y, z);
    }
    public IVec4 yxyw()
    {
        return new IVec4(y, x, y, w);
    }
    public IVec4 yxzx()
    {
        return new IVec4(y, x, z, x);
    }
    public IVec4 yxzy()
    {
        return new IVec4(y, x, z, y);
    }
    public IVec4 yxzz()
    {
        return new IVec4(y, x, z, z);
    }
    public IVec4 yxzw()
    {
        return new IVec4(y, x, z, w);
    }
    public IVec4 yxwx()
    {
        return new IVec4(y, x, w, x);
    }
    public IVec4 yxwy()
    {
        return new IVec4(y, x, w, y);
    }
    public IVec4 yxwz()
    {
        return new IVec4(y, x, w, z);
    }
    public IVec4 yxww()
    {
        return new IVec4(y, x, w, w);
    }
    public IVec4 yyxx()
    {
        return new IVec4(y, y, x, x);
    }
    public IVec4 yyxy()
    {
        return new IVec4(y, y, x, y);
    }
    public IVec4 yyxz()
    {
        return new IVec4(y, y, x, z);
    }
    public IVec4 yyxw()
    {
        return new IVec4(y, y, x, w);
    }
    public IVec4 yyyx()
    {
        return new IVec4(y, y, y, x);
    }
    public IVec4 yyyy()
    {
        return new IVec4(y, y, y, y);
    }
    public IVec4 yyyz()
    {
        return new IVec4(y, y, y, z);
    }
    public IVec4 yyyw()
    {
        return new IVec4(y, y, y, w);
    }
    public IVec4 yyzx()
    {
        return new IVec4(y, y, z, x);
    }
    public IVec4 yyzy()
    {
        return new IVec4(y, y, z, y);
    }
    public IVec4 yyzz()
    {
        return new IVec4(y, y, z, z);
    }
    public IVec4 yyzw()
    {
        return new IVec4(y, y, z, w);
    }
    public IVec4 yywx()
    {
        return new IVec4(y, y, w, x);
    }
    public IVec4 yywy()
    {
        return new IVec4(y, y, w, y);
    }
    public IVec4 yywz()
    {
        return new IVec4(y, y, w, z);
    }
    public IVec4 yyww()
    {
        return new IVec4(y, y, w, w);
    }
    public IVec4 yzxx()
    {
        return new IVec4(y, z, x, x);
    }
    public IVec4 yzxy()
    {
        return new IVec4(y, z, x, y);
    }
    public IVec4 yzxz()
    {
        return new IVec4(y, z, x, z);
    }
    public IVec4 yzxw()
    {
        return new IVec4(y, z, x, w);
    }
    public IVec4 yzyx()
    {
        return new IVec4(y, z, y, x);
    }
    public IVec4 yzyy()
    {
        return new IVec4(y, z, y, y);
    }
    public IVec4 yzyz()
    {
        return new IVec4(y, z, y, z);
    }
    public IVec4 yzyw()
    {
        return new IVec4(y, z, y, w);
    }
    public IVec4 yzzx()
    {
        return new IVec4(y, z, z, x);
    }
    public IVec4 yzzy()
    {
        return new IVec4(y, z, z, y);
    }
    public IVec4 yzzz()
    {
        return new IVec4(y, z, z, z);
    }
    public IVec4 yzzw()
    {
        return new IVec4(y, z, z, w);
    }
    public IVec4 yzwx()
    {
        return new IVec4(y, z, w, x);
    }
    public IVec4 yzwy()
    {
        return new IVec4(y, z, w, y);
    }
    public IVec4 yzwz()
    {
        return new IVec4(y, z, w, z);
    }
    public IVec4 yzww()
    {
        return new IVec4(y, z, w, w);
    }
    public IVec4 ywxx()
    {
        return new IVec4(y, w, x, x);
    }
    public IVec4 ywxy()
    {
        return new IVec4(y, w, x, y);
    }
    public IVec4 ywxz()
    {
        return new IVec4(y, w, x, z);
    }
    public IVec4 ywxw()
    {
        return new IVec4(y, w, x, w);
    }
    public IVec4 ywyx()
    {
        return new IVec4(y, w, y, x);
    }
    public IVec4 ywyy()
    {
        return new IVec4(y, w, y, y);
    }
    public IVec4 ywyz()
    {
        return new IVec4(y, w, y, z);
    }
    public IVec4 ywyw()
    {
        return new IVec4(y, w, y, w);
    }
    public IVec4 ywzx()
    {
        return new IVec4(y, w, z, x);
    }
    public IVec4 ywzy()
    {
        return new IVec4(y, w, z, y);
    }
    public IVec4 ywzz()
    {
        return new IVec4(y, w, z, z);
    }
    public IVec4 ywzw()
    {
        return new IVec4(y, w, z, w);
    }
    public IVec4 ywwx()
    {
        return new IVec4(y, w, w, x);
    }
    public IVec4 ywwy()
    {
        return new IVec4(y, w, w, y);
    }
    public IVec4 ywwz()
    {
        return new IVec4(y, w, w, z);
    }
    public IVec4 ywww()
    {
        return new IVec4(y, w, w, w);
    }
    public IVec4 zxxx()
    {
        return new IVec4(z, x, x, x);
    }
    public IVec4 zxxy()
    {
        return new IVec4(z, x, x, y);
    }
    public IVec4 zxxz()
    {
        return new IVec4(z, x, x, z);
    }
    public IVec4 zxxw()
    {
        return new IVec4(z, x, x, w);
    }
    public IVec4 zxyx()
    {
        return new IVec4(z, x, y, x);
    }
    public IVec4 zxyy()
    {
        return new IVec4(z, x, y, y);
    }
    public IVec4 zxyz()
    {
        return new IVec4(z, x, y, z);
    }
    public IVec4 zxyw()
    {
        return new IVec4(z, x, y, w);
    }
    public IVec4 zxzx()
    {
        return new IVec4(z, x, z, x);
    }
    public IVec4 zxzy()
    {
        return new IVec4(z, x, z, y);
    }
    public IVec4 zxzz()
    {
        return new IVec4(z, x, z, z);
    }
    public IVec4 zxzw()
    {
        return new IVec4(z, x, z, w);
    }
    public IVec4 zxwx()
    {
        return new IVec4(z, x, w, x);
    }
    public IVec4 zxwy()
    {
        return new IVec4(z, x, w, y);
    }
    public IVec4 zxwz()
    {
        return new IVec4(z, x, w, z);
    }
    public IVec4 zxww()
    {
        return new IVec4(z, x, w, w);
    }
    public IVec4 zyxx()
    {
        return new IVec4(z, y, x, x);
    }
    public IVec4 zyxy()
    {
        return new IVec4(z, y, x, y);
    }
    public IVec4 zyxz()
    {
        return new IVec4(z, y, x, z);
    }
    public IVec4 zyxw()
    {
        return new IVec4(z, y, x, w);
    }
    public IVec4 zyyx()
    {
        return new IVec4(z, y, y, x);
    }
    public IVec4 zyyy()
    {
        return new IVec4(z, y, y, y);
    }
    public IVec4 zyyz()
    {
        return new IVec4(z, y, y, z);
    }
    public IVec4 zyyw()
    {
        return new IVec4(z, y, y, w);
    }
    public IVec4 zyzx()
    {
        return new IVec4(z, y, z, x);
    }
    public IVec4 zyzy()
    {
        return new IVec4(z, y, z, y);
    }
    public IVec4 zyzz()
    {
        return new IVec4(z, y, z, z);
    }
    public IVec4 zyzw()
    {
        return new IVec4(z, y, z, w);
    }
    public IVec4 zywx()
    {
        return new IVec4(z, y, w, x);
    }
    public IVec4 zywy()
    {
        return new IVec4(z, y, w, y);
    }
    public IVec4 zywz()
    {
        return new IVec4(z, y, w, z);
    }
    public IVec4 zyww()
    {
        return new IVec4(z, y, w, w);
    }
    public IVec4 zzxx()
    {
        return new IVec4(z, z, x, x);
    }
    public IVec4 zzxy()
    {
        return new IVec4(z, z, x, y);
    }
    public IVec4 zzxz()
    {
        return new IVec4(z, z, x, z);
    }
    public IVec4 zzxw()
    {
        return new IVec4(z, z, x, w);
    }
    public IVec4 zzyx()
    {
        return new IVec4(z, z, y, x);
    }
    public IVec4 zzyy()
    {
        return new IVec4(z, z, y, y);
    }
    public IVec4 zzyz()
    {
        return new IVec4(z, z, y, z);
    }
    public IVec4 zzyw()
    {
        return new IVec4(z, z, y, w);
    }
    public IVec4 zzzx()
    {
        return new IVec4(z, z, z, x);
    }
    public IVec4 zzzy()
    {
        return new IVec4(z, z, z, y);
    }
    public IVec4 zzzz()
    {
        return new IVec4(z, z, z, z);
    }
    public IVec4 zzzw()
    {
        return new IVec4(z, z, z, w);
    }
    public IVec4 zzwx()
    {
        return new IVec4(z, z, w, x);
    }
    public IVec4 zzwy()
    {
        return new IVec4(z, z, w, y);
    }
    public IVec4 zzwz()
    {
        return new IVec4(z, z, w, z);
    }
    public IVec4 zzww()
    {
        return new IVec4(z, z, w, w);
    }
    public IVec4 zwxx()
    {
        return new IVec4(z, w, x, x);
    }
    public IVec4 zwxy()
    {
        return new IVec4(z, w, x, y);
    }
    public IVec4 zwxz()
    {
        return new IVec4(z, w, x, z);
    }
    public IVec4 zwxw()
    {
        return new IVec4(z, w, x, w);
    }
    public IVec4 zwyx()
    {
        return new IVec4(z, w, y, x);
    }
    public IVec4 zwyy()
    {
        return new IVec4(z, w, y, y);
    }
    public IVec4 zwyz()
    {
        return new IVec4(z, w, y, z);
    }
    public IVec4 zwyw()
    {
        return new IVec4(z, w, y, w);
    }
    public IVec4 zwzx()
    {
        return new IVec4(z, w, z, x);
    }
    public IVec4 zwzy()
    {
        return new IVec4(z, w, z, y);
    }
    public IVec4 zwzz()
    {
        return new IVec4(z, w, z, z);
    }
    public IVec4 zwzw()
    {
        return new IVec4(z, w, z, w);
    }
    public IVec4 zwwx()
    {
        return new IVec4(z, w, w, x);
    }
    public IVec4 zwwy()
    {
        return new IVec4(z, w, w, y);
    }
    public IVec4 zwwz()
    {
        return new IVec4(z, w, w, z);
    }
    public IVec4 zwww()
    {
        return new IVec4(z, w, w, w);
    }
    public IVec4 wxxx()
    {
        return new IVec4(w, x, x, x);
    }
    public IVec4 wxxy()
    {
        return new IVec4(w, x, x, y);
    }
    public IVec4 wxxz()
    {
        return new IVec4(w, x, x, z);
    }
    public IVec4 wxxw()
    {
        return new IVec4(w, x, x, w);
    }
    public IVec4 wxyx()
    {
        return new IVec4(w, x, y, x);
    }
    public IVec4 wxyy()
    {
        return new IVec4(w, x, y, y);
    }
    public IVec4 wxyz()
    {
        return new IVec4(w, x, y, z);
    }
    public IVec4 wxyw()
    {
        return new IVec4(w, x, y, w);
    }
    public IVec4 wxzx()
    {
        return new IVec4(w, x, z, x);
    }
    public IVec4 wxzy()
    {
        return new IVec4(w, x, z, y);
    }
    public IVec4 wxzz()
    {
        return new IVec4(w, x, z, z);
    }
    public IVec4 wxzw()
    {
        return new IVec4(w, x, z, w);
    }
    public IVec4 wxwx()
    {
        return new IVec4(w, x, w, x);
    }
    public IVec4 wxwy()
    {
        return new IVec4(w, x, w, y);
    }
    public IVec4 wxwz()
    {
        return new IVec4(w, x, w, z);
    }
    public IVec4 wxww()
    {
        return new IVec4(w, x, w, w);
    }
    public IVec4 wyxx()
    {
        return new IVec4(w, y, x, x);
    }
    public IVec4 wyxy()
    {
        return new IVec4(w, y, x, y);
    }
    public IVec4 wyxz()
    {
        return new IVec4(w, y, x, z);
    }
    public IVec4 wyxw()
    {
        return new IVec4(w, y, x, w);
    }
    public IVec4 wyyx()
    {
        return new IVec4(w, y, y, x);
    }
    public IVec4 wyyy()
    {
        return new IVec4(w, y, y, y);
    }
    public IVec4 wyyz()
    {
        return new IVec4(w, y, y, z);
    }
    public IVec4 wyyw()
    {
        return new IVec4(w, y, y, w);
    }
    public IVec4 wyzx()
    {
        return new IVec4(w, y, z, x);
    }
    public IVec4 wyzy()
    {
        return new IVec4(w, y, z, y);
    }
    public IVec4 wyzz()
    {
        return new IVec4(w, y, z, z);
    }
    public IVec4 wyzw()
    {
        return new IVec4(w, y, z, w);
    }
    public IVec4 wywx()
    {
        return new IVec4(w, y, w, x);
    }
    public IVec4 wywy()
    {
        return new IVec4(w, y, w, y);
    }
    public IVec4 wywz()
    {
        return new IVec4(w, y, w, z);
    }
    public IVec4 wyww()
    {
        return new IVec4(w, y, w, w);
    }
    public IVec4 wzxx()
    {
        return new IVec4(w, z, x, x);
    }
    public IVec4 wzxy()
    {
        return new IVec4(w, z, x, y);
    }
    public IVec4 wzxz()
    {
        return new IVec4(w, z, x, z);
    }
    public IVec4 wzxw()
    {
        return new IVec4(w, z, x, w);
    }
    public IVec4 wzyx()
    {
        return new IVec4(w, z, y, x);
    }
    public IVec4 wzyy()
    {
        return new IVec4(w, z, y, y);
    }
    public IVec4 wzyz()
    {
        return new IVec4(w, z, y, z);
    }
    public IVec4 wzyw()
    {
        return new IVec4(w, z, y, w);
    }
    public IVec4 wzzx()
    {
        return new IVec4(w, z, z, x);
    }
    public IVec4 wzzy()
    {
        return new IVec4(w, z, z, y);
    }
    public IVec4 wzzz()
    {
        return new IVec4(w, z, z, z);
    }
    public IVec4 wzzw()
    {
        return new IVec4(w, z, z, w);
    }
    public IVec4 wzwx()
    {
        return new IVec4(w, z, w, x);
    }
    public IVec4 wzwy()
    {
        return new IVec4(w, z, w, y);
    }
    public IVec4 wzwz()
    {
        return new IVec4(w, z, w, z);
    }
    public IVec4 wzww()
    {
        return new IVec4(w, z, w, w);
    }
    public IVec4 wwxx()
    {
        return new IVec4(w, w, x, x);
    }
    public IVec4 wwxy()
    {
        return new IVec4(w, w, x, y);
    }
    public IVec4 wwxz()
    {
        return new IVec4(w, w, x, z);
    }
    public IVec4 wwxw()
    {
        return new IVec4(w, w, x, w);
    }
    public IVec4 wwyx()
    {
        return new IVec4(w, w, y, x);
    }
    public IVec4 wwyy()
    {
        return new IVec4(w, w, y, y);
    }
    public IVec4 wwyz()
    {
        return new IVec4(w, w, y, z);
    }
    public IVec4 wwyw()
    {
        return new IVec4(w, w, y, w);
    }
    public IVec4 wwzx()
    {
        return new IVec4(w, w, z, x);
    }
    public IVec4 wwzy()
    {
        return new IVec4(w, w, z, y);
    }
    public IVec4 wwzz()
    {
        return new IVec4(w, w, z, z);
    }
    public IVec4 wwzw()
    {
        return new IVec4(w, w, z, w);
    }
    public IVec4 wwwx()
    {
        return new IVec4(w, w, w, x);
    }
    public IVec4 wwwy()
    {
        return new IVec4(w, w, w, y);
    }
    public IVec4 wwwz()
    {
        return new IVec4(w, w, w, z);
    }
    public IVec4 wwww()
    {
        return new IVec4(w, w, w, w);
    }
    //#endregion
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
        return getDistance(new IVec4(pos));
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