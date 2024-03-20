package engine.spriteBuilder;

import engine.core.BoundingBox;
import engine.util.math.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;

public class RectangleShape extends PaintableShape
{
    private final double x;
    private final double y;
    private final double width;
    private final double height;
    public double radius_TL = 0;
    public double radius_TR = 0;
    public double radius_BL = 0;
    public double radius_BR = 0;
    private final double lowerBound;
    public RectangleShape(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.boundingBox = new BoundingBox(x, y, width, height);
        lowerBound = boundingBox.lowerBound;
    }
    public double getRadius_TR()
    {
        return radius_TR;
    }
    public double getRadius_BL()
    {
        return radius_BL;
    }
    public double getRadius_BR()
    {
        return radius_BR;
    }
    public double getRadius_TL()
    {
        return radius_TL;
    }
    public void setRadius_TL(double value)
    {
        radius_TL = calcRadius(value, radius_BL, radius_TR);
        setRadius_BL(radius_BL);
        setRadius_TR(radius_TR);
        dirty = true;
    }
    public void setRadius_TR(double value)
    {
        radius_TR = calcRadius(value, radius_TL, radius_BR);
        setRadius_TL(radius_TL);
        setRadius_BR(radius_BR);
        dirty = true;
    }
    public void setRadius_BL(double value)
    {
        radius_BL = calcRadius(value, radius_TL, radius_BR);
        setRadius_TL(radius_TL);
        setRadius_BR(radius_BR);
        dirty = true;
    }
    public void setRadius_BR(double value)
    {
        radius_BR = calcRadius(value, radius_TR, radius_BL);
        setRadius_TR(radius_TR);
        setRadius_BL(radius_BL);
        dirty = true;
    }
    public void setRadius_Top(double value)
    {
        double v = Math.min(lowerBound * 0.5, value);
        radius_TL = v;
        radius_TR = v;
        dirty = true;
    }
    public void setRadius_Bottom(double value)
    {
        double v = Math.min(lowerBound * 0.5, value);
        radius_BL = v;
        radius_BR = v;
        dirty = true;
    }
    public void setRadius_Left(double value)
    {
        double v = Math.min(lowerBound * 0.5, value);
        radius_TL = v;
        radius_BL = v;
        dirty = true;
    }
    public void setRadius_Right(double value)
    {
        double v = Math.min(lowerBound * 0.5, value);
        radius_TR = v;
        radius_BR = v;
        dirty = true;
    }
    public void setRadius(double value)
    {
        double v = Math.min(lowerBound * 0.5, value);
        radius_TL = v;
        radius_TR = v;
        radius_BL = v;
        radius_BR = v;
        dirty = true;
    }
    private record RenderData(double unit, double sx, double sy, double ex, double ey, double r_tr, double r_tl, double r_br, double r_bl) { }
    private RenderData rd;
    private boolean dirty = true;
    @Override
    public void draw(GraphicsContext gc, engine.util.RenderData data, Vec2 dimensions)
    {
        var unit = data.unit / SpriteBuilder.getModifier();
        if(dirty || rd == null || rd.unit != unit)
        {
            calcData(unit, data.pivotOffset);
            dirty = false;
        }

        gc.beginPath();

        gc.moveTo(rd.sx + rd.r_tl, rd.sy);

        //Line Top-Left -> Top-Right
        gc.lineTo(rd.ex - rd.r_tr, rd.sy);

        //Arc Top-Right
        gc.arc(rd.ex - rd.r_tr, rd.sy + rd.r_tr,
               rd.r_tr, rd.r_tr,
               90, -90
        );

        //Line Top-Right -> Bottom-Right
        gc.lineTo(rd.ex, rd.ey - rd.r_br);

        //Arc Bottom-Right
        gc.arc(rd.ex - rd.r_br, rd.ey - rd.r_br,
               rd.r_br, rd.r_br,
               0, -90
        );

        //Line Bottom-Right -> Bottom-Left
        gc.lineTo(rd.sx + rd.r_bl, rd.ey);

        //Arc Bottom-Left
        gc.arc(rd.sx + rd.r_bl, rd.ey - rd.r_bl,
               rd.r_bl, rd.r_bl,
               -90, -90
        );

        //Line Bottom-Left -> Top-Left
        gc.lineTo(rd.sx, rd.sy + rd.r_tl);

        //Arc Top-Left
        gc.arc(rd.sx + rd.r_tl, rd.sy + rd.r_tl,
               rd.r_tl, rd.r_tl,
               -180, -90
        );

        gc.closePath();

        if(getFill() != null)
        {
            gc.setGlobalBlendMode(BlendMode.SRC_OVER);
            gc.setFill(getFill());
            gc.fill();
        }

        if(getStroke() != null && getStrokeThickness() != 0)
        {
            gc.setStroke(getStroke());
            gc.setLineWidth(getStrokeThickness() * unit);
            gc.stroke();
        }
    }
    private void calcData(double unit, Vec2 pivotOffset)
    {
        var scaledX = x * unit;
        var scaledY = y * unit;
        var scaledW = width * unit;
        var scaledH = height * unit;
        double sx = x * unit - pivotOffset.x;
        double sy = y * unit - pivotOffset.y;
        double ex = scaledX + scaledW - pivotOffset.x;
        double ey = scaledY + scaledH - pivotOffset.y;
        double r_tr = radius_TL * unit;
        double r_tl = radius_TR * unit;
        double r_br = radius_BL * unit;
        double r_bl = radius_BR * unit;
        rd = new RenderData(unit, sx, sy, ex, ey, r_tr, r_tl, r_br, r_bl);
    }
    private double calcRadius(double value, double dep1, double dep2)
    {
        var bound = lowerBound;
        var hb = bound * 0.5;
        var res = Math.max(value, bound);

        if(res > hb)
        {
            var overflow = res - hb;
            var limit = Math.min(overflow, Math.max(hb - Math.max(dep1, dep2), 0));
            res += limit;
        }

        return res;
    }
}