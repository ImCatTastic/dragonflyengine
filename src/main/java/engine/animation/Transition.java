package engine.animation;

import engine.Engine;
import engine.util.PropertyGetter;
import engine.util.PropertySetter;

import java.util.function.Consumer;

public abstract class Transition<T> extends Animation
{
    protected final static double DEFAULT_DURATION = 1;
    protected T fromValue;
    protected T byValue;
    protected T toValue;
    protected PropertyGetter<T> dynamicFromValue;
    protected PropertyGetter<T> dynamicByValue;
    protected PropertyGetter<T> dynamicToValue;
    protected final PropertySetter<T> propertySetter;
    protected T from;
    protected T to;
    private T[] table;
    private boolean useLookUpTable;
    public void enableLookUpTable()
    {
        useLookUpTable = true;
    }
    protected abstract T[] createLookUpTable(int numFrames);
    Transition(PropertySetter<T> propertySetter, double duration, boolean reverse)
    {
        super(duration, reverse);
        this.propertySetter = propertySetter;
    }
    Transition(PropertySetter<T> propertySetter, double duration)
    {
        super(duration, false);
        this.propertySetter = propertySetter;
    }
    Transition(PropertySetter<T> propertySetter, boolean reverse)
    {
        super(DEFAULT_DURATION, reverse);
        this.propertySetter = propertySetter;
    }
    Transition(PropertySetter<T> propertySetter)
    {
        super(DEFAULT_DURATION, false);
        this.propertySetter = propertySetter;
    }
    @Override
    public final void init()
    {
        super.init();

        if((fromValue == null && dynamicFromValue == null ) && (toValue == null  && dynamicToValue == null))
            throw new IllegalArgumentException("Not enough arguments provided for Transition.");

        else if((fromValue == null || dynamicFromValue == null ) && (byValue == null  && dynamicByValue == null))
            throw new IllegalArgumentException("Not enough arguments provided for Transition.");

        else if((toValue == null || dynamicToValue == null ) && (byValue == null  && dynamicByValue == null))
            throw new IllegalArgumentException("Not enough arguments provided for Transition.");

        if(useLookUpTable)
        {
            table = createLookUpTable((int) Math.round(duration / Engine.targetUPS));
        }

        else
        {
            load();
            from = dynamicFromValue == null ? fromValue == null ? dynamicByValue == null ? byValue : dynamicByValue.get() : fromValue : dynamicFromValue.get();
            to = dynamicToValue == null ? toValue == null ? dynamicByValue == null ? byValue : dynamicByValue.get() : toValue : dynamicToValue.get();
        }
    }
    protected abstract void load();
    protected abstract T calcFrame();
    public Transition<T> setFrom(T fromValue)
    {
        this.fromValue = fromValue;
        return this;
    }
    public Transition<T> setBy(T byValue)
    {
        this.byValue = byValue;
        return this;
    }
    public Transition<T> setTo(T toValue)
    {
        this.toValue = toValue;
        return this;
    }
    public Transition<T> setFrom(PropertyGetter<T> fromValue)
    {
        this.dynamicFromValue = fromValue;
        return this;
    }
    public Transition<T> setBy(PropertyGetter<T> byValue)
    {
        this.dynamicByValue = byValue;
        return this;
    }
    public Transition<T> setTo(PropertyGetter<T> toValue)
    {
        this.dynamicToValue = toValue;
        return this;
    }
    @Override
    protected final void onUpdate()
    {
        if(useLookUpTable)
            propertySetter.set(table[(int) (Engine.targetUPS * normalizedProgress)]);
        else
            propertySetter.set(calcFrame());
    }
}