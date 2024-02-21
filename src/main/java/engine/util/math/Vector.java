package engine.util.math;

import org.jetbrains.annotations.NotNull;

public interface Vector<T>
{
    @NotNull String toString();
    double getDistance(@NotNull T pos);
    T add(@NotNull T vec);
    T sub(@NotNull T vec);
    T subr(@NotNull T vec);
    T mult(@NotNull T vec);
    T div(@NotNull T vec);
    T divr(@NotNull T vec);

    T add(double value);
    T sub(double value);
    T subr(double value);
    T mult(double value);
    T div(double value);
    T divr(double value);

    T add(int value);
    T sub(int value);
    T subr(int value);
    T mult(int value);
    T div(int value);
    T divr(int value);
}
