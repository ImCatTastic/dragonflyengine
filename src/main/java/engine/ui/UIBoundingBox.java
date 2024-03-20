package engine.ui;

import javafx.beans.binding.NumberExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import org.jetbrains.annotations.NotNull;

public class UIBoundingBox
{
    public final DoubleProperty minXProperty = new SimpleDoubleProperty(0);
    public final DoubleProperty minYProperty = new SimpleDoubleProperty(0);
    public final DoubleProperty maxXProperty = new SimpleDoubleProperty(0);
    public final DoubleProperty maxYProperty = new SimpleDoubleProperty(0);
    public final DoubleProperty widthProperty = new SimpleDoubleProperty(0);
    public final DoubleProperty heightProperty = new SimpleDoubleProperty(0);
    public UIBoundingBox()
    {
        maxXProperty.bind(minXProperty.add(widthProperty));
        maxYProperty.bind(minYProperty.add(heightProperty));
    }
    public void reset()
    {
        minXProperty.unbind();
        minXProperty.set(0);

        minYProperty.unbind();
        minYProperty.set(0);

        widthProperty.unbind();
        widthProperty.set(0);

        heightProperty.unbind();
        heightProperty.set(0);
    }
    public void bind(@NotNull UIBoundingBox other)
    {
        minXProperty.bind(other.minXProperty);
        minYProperty.bind(other.minYProperty);
        widthProperty.bind(other.widthProperty);
        heightProperty.bind(other.heightProperty);
    }
    public void bind(@NotNull NumberExpression minXBinding, @NotNull NumberExpression minYBinding,
                     @NotNull NumberExpression widthBinding, @NotNull NumberExpression heightBinding)
    {
        minXProperty.bind(minXBinding);
        minYProperty.bind(minYBinding);
        widthProperty.bind(widthBinding);
        heightProperty.bind(heightBinding);
    }
}
