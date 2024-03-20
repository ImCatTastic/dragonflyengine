package engine.ui.shape;

import engine.ui.UIComponent;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.scene.shape.*;

public class Polygon2D extends UIComponent
{
    private final BooleanProperty isRelativeProperty = new SimpleBooleanProperty(false);
    private final DoubleProperty cornerRadiusProperty = new SimpleDoubleProperty(0);
    public Polygon2D(int sides, double angleOffset)
    {
        create(
                Math.max(sides, 3),
                Math.min(Math.max(angleOffset, -360), 360)
        );
    }
    public void setCornerRadiusAbsolute(double value)
    {
        cornerRadiusProperty.set(value);
        isRelativeProperty.set(false);
    }
    public void setCornerRadiusRelative(double value)
    {
        cornerRadiusProperty.set(Math.min(value, 1));
        isRelativeProperty.set(true);
    }

    /**
     * Creates a polygon with n sides offset by a specified angle
     * @param sides num of sides of the polygon
     * @param angleOffset base angleOffset of the final shape
     */
    private void create(int sides, double angleOffset)
    {
        var minBinding = Bindings.createDoubleBinding(() ->
                Math.min(getWidthProperty().get(), getHeightProperty().get()),
                                                      getWidthProperty(), getHeightProperty());

        var centerXProperty = new SimpleDoubleProperty();
        var centerYProperty = new SimpleDoubleProperty();
        var circumRadiusProperty = new SimpleDoubleProperty();
        centerXProperty.bind(getWidthProperty().multiply(0.5));
        centerYProperty.bind(getHeightProperty().multiply(0.5));
        circumRadiusProperty.bind(minBinding.multiply(0.5));

        var cvt = Math.sin(Math.PI / sides);
        var radiusBinding = Bindings.createDoubleBinding(() ->
        {
            if(isRelativeProperty.get())
                return circumRadiusProperty.get() * cvt * cornerRadiusProperty.get() * 0.5;

           return Math.min(cornerRadiusProperty.get(), circumRadiusProperty.get() * cvt);
        }, cornerRadiusProperty, circumRadiusProperty);

        //fac cannot exceed 0.5 aka when radius reaches max, but max would return 1
        var fac = Bindings.createDoubleBinding(() ->
        {
            var rad = radiusBinding.get();
            var max = circumRadiusProperty.get() * cvt;
            return (isRelativeProperty.get() ? cornerRadiusProperty.get() : rad / max) * 0.5;
        }, radiusBinding, circumRadiusProperty, isRelativeProperty, cornerRadiusProperty);

        Path path2 = new Path();

        var verticesX = new DoubleBinding[sides];
        var verticesY = new DoubleBinding[sides];

        for (int i = 0; i < sides; i++)
        {
            double angle = Math.toRadians(i * (360d / sides) + angleOffset);
            verticesX[i] = circumRadiusProperty.multiply(Math.cos(angle)).add(centerXProperty);
            verticesY[i] = circumRadiusProperty.multiply(Math.sin(angle)).add(centerXProperty);
        }

        Path path = new Path();
        for (int i = 0; i < sides; i++)
        {
            var neighbourIndex1 = i > 0 ? i - 1 : sides - 1;
            var neighbourIndex2 = i < sides - 1 ? i + 1 : 0;

            var xBind = interpolatedBinding(verticesX[i], verticesX[neighbourIndex1], fac);
            var yBind = interpolatedBinding(verticesY[i], verticesY[neighbourIndex1], fac);
            if (i == 0)
            {
                var component = new MoveTo();
                component.xProperty().bind(xBind);
                component.yProperty().bind(yBind);
                path.getElements().add(component);
            }
            else
            {
                var component = new LineTo();
                component.xProperty().bind(xBind);
                component.yProperty().bind(yBind);
                path.getElements().add(component);
            }

            var arcXBind = interpolatedBinding(verticesX[i], verticesX[neighbourIndex2], fac);
            var arcYBind = interpolatedBinding(verticesY[i], verticesY[neighbourIndex2], fac);

            var arc = new ArcTo();
            arc.setSweepFlag(true);
            arc.xProperty().bind(arcXBind);
            arc.yProperty().bind(arcYBind);
            arc.radiusXProperty().bind(circumRadiusProperty.multiply(Math.cos(Math.PI / sides)).multiply(2).multiply(fac));
            arc.radiusYProperty().bind(circumRadiusProperty.multiply(Math.cos(Math.PI / sides)).multiply(2).multiply(fac));
            path.getElements().add(arc);
        }

        path.getElements().add(new ClosePath());

        path.fillProperty().bind(getUserStyle().fillProperty);
        path.strokeProperty().bind(getUserStyle().strokeProperty.get().paintProperty);
        path.strokeWidthProperty().bind(getUserStyle().strokeProperty.get().thicknessProperty);
        addNodesBackground(path, path2);
    }

    /**
     * Creates a binding to interpolate between 2 values based on a third property being the interpolation progress/factor
     * @param from the property we start from
     * @param to the property to interpolate to
     * @param factor progress from A to B [0 - 1]
     * @return dynamic binding that updates accordingly if any of the property changes
     */
    private DoubleBinding interpolatedBinding(ObservableDoubleValue from, ObservableDoubleValue to, ObservableDoubleValue factor)
    {
        return Bindings.createDoubleBinding(() ->
                from.get() + (to.get() - from.get()) * factor.get(),
                from, to, factor);
    }
}
