package engine.util;

import engine.util.math.Vec3;
import engine.util.math.Vec4;
import javafx.scene.paint.Color;

public class RGBColor
{
    public final double r;
    public final double g;
    public final double b;
    public final double a;
    public final Color fx;
    public final Vec3 vec3;
    public final Vec4 vec4;
    public RGBColor(int r, int g, int b, double a)
    {
        this.r = Math.min(Math.max(r, 0), 255);
        this.g = Math.min(Math.max(g, 0), 255);
        this.b = Math.min(Math.max(b, 0), 255);
        this.a = Math.min(Math.max(a, 0), 1);
        this.fx = new Color(r / 255d, g / 255d, b / 255d, a);
        this.vec3 = new Vec3(r / 255d, g / 255d, b / 255d);
        this.vec4 = new Vec4(r / 255d, g / 255d, b / 255d, a);
    }
    public RGBColor(int r, int g, int b)
    {
        this(r, g, b, 1);
    }
    public RGBColor(int v)
    {
        this(v, v, v, 1);
    }
    public final static RGBColor GREEN = new RGBColor(0, 255, 0);
    public final static RGBColor RED = new RGBColor(255, 0, 0);
    public final static RGBColor BLUE = new RGBColor(0, 0, 255);
    public final static RGBColor YELLOW = new RGBColor(255, 255, 0);
    public final static RGBColor CYAN = new RGBColor(0, 255, 255);
    public final static RGBColor MAGENTA = new RGBColor(255, 0, 255);
    public final static RGBColor ORANGE = new RGBColor(255, 165, 0);
    public final static RGBColor PURPLE = new RGBColor(128, 0, 128);
    public final static RGBColor PINK = new RGBColor(255, 192, 203);
    public final static RGBColor BROWN = new RGBColor(165, 42, 42);
    public final static RGBColor GRAY = new RGBColor(128, 128, 128);
    public final static RGBColor WHITE = new RGBColor(255, 255, 255);
    public final static RGBColor BLACK = new RGBColor(0, 0, 0);
}