package engine.ui.util;

import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Insets
{
    public final DoubleProperty left = new SimpleDoubleProperty();
    public final DoubleProperty top = new SimpleDoubleProperty();
    public final DoubleProperty right = new SimpleDoubleProperty();
    public final DoubleProperty bottom = new SimpleDoubleProperty();

    public void setAll(double value)
    {
        if(!left.isBound())
            left.set(value);
        if(!top.isBound())
            top.set(value);
        if(!right.isBound())
            right.set(value);
        if(!bottom.isBound())
            bottom.set(value);
    }
    public void bindAll(DoubleExpression expression)
    {
        if(!left.isBound())
            left.bind(expression);
        if(!top.isBound())
            top.bind(expression);
        if(!right.isBound())
            right.bind(expression);
        if(!bottom.isBound())
            bottom.bind(expression);
    }
}
