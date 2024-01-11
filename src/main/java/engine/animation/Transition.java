package engine.animation;

import engine.util.PropertyGetter;
import engine.util.PropertySetter;

public abstract class Transition<T, V> extends Animation<T>
{
    private final PropertySetter<V> propertySetter;
    private V from;
    private V delta;
    private final PropertyGetter<V> getFrom;
    private final PropertyGetter<V> getDelta;
    Transition(
            PropertyGetter<V> getFrom, PropertyGetter<V> getDelta,
            PropertySetter<V> propertySetter,
            double duration, boolean reverse)
    {
        super(duration, reverse);
        this.getFrom = getFrom;
        this.getDelta = getDelta;
        this.propertySetter = propertySetter;
    }
    @Override
    public final void onInit()
    {
        delta = getDelta.get();
        from = getFrom.get();
    }
    protected abstract V calcFrame(V from, V delta, double normalizedProgress);
    @Override
    protected final void onUpdate()
    {
        propertySetter.set(calcFrame(from, delta, normalizedProgress));
    }
}