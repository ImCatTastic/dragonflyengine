package engine.util;

import java.util.function.Function;

public interface Interpolator
{
    Interpolator LINEAR = (t) -> t;
    Interpolator EASE_IN = (t) -> t * t;
    Interpolator EASE_OUT = (t) -> 1.0 - (1.0 - t) * (1.0 - t);
    Interpolator EASE_IN_OUT = (t) -> 3 * t * t - 2 * t * t * t;
    double apply(double t);
}
