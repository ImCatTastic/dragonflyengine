package engine.ui;

import javafx.beans.property.SimpleDoubleProperty;
import org.jetbrains.annotations.NotNull;

public class OwnableDoubleProperty extends SimpleDoubleProperty
{
    private Object owner;
    public OwnableDoubleProperty(@NotNull Object owner, double initialValue)
    {
        this.owner = owner;
        setValue(initialValue);
    }
    public void setOwner(Object owner)
    {
        this.owner = owner;
    }
    public Object getOwner()
    {
        return owner;
    }
}