package engine.ui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberExpression;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.*;
import org.jetbrains.annotations.NotNull;

public class UIBoundingBox
{
    public final ObjectProperty<Double> minXProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Double> minYProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Double> maxXProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Double> maxYProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Double> widthProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Double> heightProperty = new SimpleObjectProperty<>(null);
    public UIBoundingBox()
    {
        maxXProperty.bind(Bindings.createObjectBinding(() ->
        {
            if(minXProperty.get() == null|| widthProperty.get() == null)
                return null;

            return minXProperty.get() + widthProperty.get();

        }, minXProperty, widthProperty));

        maxYProperty.bind(Bindings.createObjectBinding(() ->
        {
            if(minYProperty.get() == null|| heightProperty.get() == null)
                return null;

            return minYProperty.get() + heightProperty.get();
        }, minYProperty, heightProperty));
    }
    public void reset()
    {
        minXProperty.unbind();
        minXProperty.set(0d);

        minYProperty.unbind();
        minYProperty.set(0d);

        widthProperty.unbind();
        widthProperty.set(0d);

        heightProperty.unbind();
        heightProperty.set(0d);
    }
    public void bind(@NotNull UIBoundingBox other)
    {
        minXProperty.bind(other.minXProperty);
        minYProperty.bind(other.minYProperty);
        widthProperty.bind(other.widthProperty);
        heightProperty.bind(other.heightProperty);
    }
    public void bind(@NotNull ObjectExpression<Double> minXBinding, @NotNull ObjectExpression<Double> minYBinding,
                     @NotNull ObjectExpression<Double> widthBinding, @NotNull ObjectExpression<Double> heightBinding)
    {
        minXProperty.bind(minXBinding);
        minYProperty.bind(minYBinding);
        widthProperty.bind(widthBinding);
        heightProperty.bind(heightBinding);
    }
}
