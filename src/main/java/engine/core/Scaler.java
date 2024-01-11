package engine.core;

import engine.mathUtil.Vec2;
import org.jetbrains.annotations.NotNull;

public final class Scaler
{
    private double refUnit;
    private final double defaultScale;
    private final double scaleW;
    private final double scaleH;
    private final double scaleMin;
    private final double scaleMax;
    public Scaler(@NotNull ScalingMode scalingMode, double refUnit, double scaleW, double scaleH, boolean horizontal)
    {
        this.refUnit = refUnit;
        this.scaleW = scaleW;
        this.scaleH = scaleH;
        this.scaleMin = horizontal ? scaleH : scaleW;
        this.scaleMax =  horizontal ? scaleW : scaleH;

        defaultScale = switch (scalingMode)
        {
            case WIDTH -> scaleW;
            case HEIGHT -> scaleH;
            case MIN -> scaleMin;
            case MAX -> scaleMax;
        };
    }

    public double apply(double value)
    {
        return value * refUnit * defaultScale;
    }
    public @NotNull Vec2 apply(@NotNull Vec2 vec2)
    {
        return new Vec2(
                vec2.x * refUnit * defaultScale,
                vec2.y * refUnit * defaultScale);
    }
}
