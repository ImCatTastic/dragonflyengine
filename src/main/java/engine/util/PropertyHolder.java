package engine.util;

public class PropertyHolder<Number>
{
    private Number value;
    public PropertyHolder(Number value)
    {
        this.value = value;
    }

    public void set(Number value) {
        this.value = value;
    }

    public Number get() {
        return value;
    }
}
