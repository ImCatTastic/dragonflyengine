package engine.ui;

import javafx.beans.property.Property;

public class PropertyDistributor<T extends Property<T>>
{
    private final T primary;
    private final T secondary;
    public PropertyDistributor(T outputHolder, T primary, T secondary)
    {
        this.primary = primary;
        this.secondary = secondary;
    }
}
