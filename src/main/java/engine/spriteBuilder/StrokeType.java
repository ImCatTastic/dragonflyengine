package engine.spriteBuilder;

public enum StrokeType
{
    INNER(-0.5),
    CENTER(0),
    OUTER(0.5);
    public final double displacementFactor;
    StrokeType(double displacementFactor)
    {
        this.displacementFactor = displacementFactor;
    }
}
