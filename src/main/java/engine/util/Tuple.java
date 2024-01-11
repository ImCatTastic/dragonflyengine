package engine.util;

public class Tuple<F, S>
{
    public Tuple(F first, S second)
    {
        this.first = first;
        this.second = second;
    }
    public final F first;
    public final S second;
}
