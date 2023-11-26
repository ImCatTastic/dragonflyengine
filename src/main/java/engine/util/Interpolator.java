package engine.util;

import java.util.function.Function;

public class Interpolator
{
    protected Function<Double, Double> getCustomInterpolator(final double blendFactor)
    {
        return (t) ->
        {
            double linear = t;
            double easeInOut = 3 * t * t - 2 * t * t * t;

            return linear + blendFactor * (easeInOut - linear);
        };
    }
}
