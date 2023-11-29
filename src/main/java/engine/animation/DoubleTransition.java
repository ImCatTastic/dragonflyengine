package engine.animation;

import engine.Engine;
import engine.util.PropertyGetter;
import engine.util.PropertySetter;

import java.util.function.Consumer;
import java.util.function.Function;

public class DoubleTransition extends Transition<Double>
{
    private double dx;
    public DoubleTransition(PropertySetter<Double> setter, double duration, boolean reverse)
    {
        super(setter, duration, reverse);
    }
    public DoubleTransition(PropertySetter<Double> setter, double duration)
    {
        super(setter, duration, false);
    }
    public DoubleTransition(PropertySetter<Double> setter, boolean reverse)
    {
        super(setter, Transition.DEFAULT_DURATION, reverse);
    }
    public DoubleTransition(PropertySetter<Double> setter)
    {
        super(setter, Transition.DEFAULT_DURATION, false);
    }
    @Override
    protected Double[] createLookUpTable(int frames)
    {
        Double[] table = new Double[frames];

        double reciprocalNum = 1 / Engine.targetUPS;
        for (int i = 0; i < table.length; i++)
            table[i] = dx * getInterpolator().apply(i * reciprocalNum);

        return table;
    }
    protected void load()
    {
        dx = to - from;
    }
    @Override
    protected Double calcFrame()
    {
        return from + dx * normalizedProgress;
    }
}
