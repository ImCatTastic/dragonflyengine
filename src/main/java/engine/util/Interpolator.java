package engine.util;

import java.util.function.Function;

public class Interpolator
{
    public final static Function<Double, Double> EASE_IN = (t) -> t * t;
    public final static Function<Double, Double> EASE_OUT = (t) -> 1.0 - (1.0 - t) * (1.0 - t);
    public final static Function<Double, Double> EASE_IN_OUT = (t) -> 3 * t * t - 2 * t * t * t;
}
