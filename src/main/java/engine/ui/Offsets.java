package engine.ui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Offsets
{
    public final DoubleProperty leftProperty = new SimpleDoubleProperty(0);
    public final DoubleProperty rightProperty = new SimpleDoubleProperty(0);
    public final DoubleProperty topProperty = new SimpleDoubleProperty(0);
    public final DoubleProperty bottomProperty = new SimpleDoubleProperty(0);
}
