package engine.ui.shape;

import engine.core.Window;
import engine.ui.UIComponent;
import engine.ui.Unit;
import engine.ui.UIUtils;
import engine.util.Tuple;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.*;

public class Rectangle2D extends UIComponent
{
    private final SimpleDoubleProperty actualAAProperty = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty actualABProperty  = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty actualBAProperty  = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty actualBBProperty  = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty minProperty = new SimpleDoubleProperty(0);
    public Rectangle2D(double width, double height)
    {
        userStyle.widthProperty.set(new Tuple<>(width, Unit.PIXEL));
        userStyle.heightProperty.set(new Tuple<>(height, Unit.PIXEL));

        minProperty.bind(Bindings.createDoubleBinding(() -> Math.min(bounds.widthProperty.get(), bounds.heightProperty.get()), bounds.widthProperty, bounds.heightProperty));
    }

    @Override
    protected void load()
    {
        super.load();
        var cornerAAProperty = new SimpleDoubleProperty(0);
        var cornerABProperty = new SimpleDoubleProperty(0);
        var cornerBAProperty = new SimpleDoubleProperty(0);
        var cornerBBProperty = new SimpleDoubleProperty(0);

        cornerAAProperty.bind(UIUtils.createNUCBinding(null, minDimensionProperty, getUserStyle().cornerRadiusTopLeftProperty, getUserStyle().cornerRadiusProperty));
        cornerABProperty.bind(UIUtils.createNUCBinding(null, minDimensionProperty, getUserStyle().cornerRadiusTopRightProperty, getUserStyle().cornerRadiusProperty));
        cornerBAProperty.bind(UIUtils.createNUCBinding(null, minDimensionProperty, getUserStyle().cornerRadiusBottomLeftProperty, getUserStyle().cornerRadiusProperty));
        cornerBBProperty.bind(UIUtils.createNUCBinding(null, minDimensionProperty, getUserStyle().cornerRadiusBottomRightProperty, getUserStyle().cornerRadiusProperty));

        createCornerBinding(actualAAProperty, cornerAAProperty, cornerBAProperty, cornerABProperty);
        createCornerBinding(actualABProperty, cornerABProperty, cornerAAProperty, cornerBBProperty);
        createCornerBinding(actualBAProperty, cornerBAProperty, cornerAAProperty, cornerBBProperty);
        createCornerBinding(actualBBProperty, cornerBBProperty, cornerABProperty, cornerBAProperty);

        var shape = createShape();
        shape.fillProperty().bind(getUserStyle().fillProperty);
        shape.setStroke(null);

        var shape_stroke = createShape();
        shape_stroke.strokeProperty().bind(getUserStyle().strokeProperty.get().paintProperty);
        shape_stroke.strokeWidthProperty().bind(getUserStyle().strokeProperty.get().thicknessProperty);

        addNodesBackground(shape);
        addNodesForeground(shape_stroke);
    }

    public Rectangle2D()
    {
        this(100, 100);
    }
    private Path createShape()
    {
        var wProperty = widthProperty; //  bounds.widthProperty().multiply(getUnitProperty());
        var hProperty = heightProperty; //bounds.heightProperty().multiply(getUnitProperty());
        
        var startPos = new MoveTo();
        startPos.xProperty().bind(actualAAProperty);

        //Top Line
        var lineTop = new LineTo();
        lineTop.xProperty().bind(wProperty.subtract(actualBAProperty));

        //Top-Right Arc
        var arcTR = new ArcTo();
        arcTR.setSweepFlag(true);
        arcTR.radiusXProperty().bind(actualBAProperty);
        arcTR.radiusYProperty().bind(actualBAProperty);
        arcTR.xProperty().bind(wProperty);
        arcTR.yProperty().bind(actualBAProperty);

        //Right Line
        var lineRight = new LineTo();
        lineRight.xProperty().bind(wProperty);
        lineRight.yProperty().bind(hProperty.subtract(actualBBProperty));

        //Bottom-Right Arc
        var arcBR = new ArcTo();
        arcBR.setSweepFlag(true);
        arcBR.radiusXProperty().bind(actualBBProperty);
        arcBR.radiusYProperty().bind(actualBBProperty);
        arcBR.xProperty().bind(wProperty.subtract(actualBBProperty));
        arcBR.yProperty().bind(hProperty);

        //Bottom Line
        var lineBottom = new LineTo();
        lineBottom.xProperty().bind(actualABProperty);
        lineBottom.yProperty().bind(hProperty);

        //Bottom-Left Arc
        var arcBL = new ArcTo();
        arcBL.setSweepFlag(true);
        arcBL.radiusXProperty().bind(actualABProperty);
        arcBL.radiusYProperty().bind(actualABProperty);
        arcBL.yProperty().bind(hProperty.subtract(actualABProperty));

        //Left Line
        var lineLeft = new LineTo();
        lineLeft.yProperty().bind(actualAAProperty);

        //Top-Left Arc
        var arcTL = new ArcTo();
        arcTL.setSweepFlag(true);
        arcTL.radiusXProperty().bind(actualAAProperty);
        arcTL.radiusYProperty().bind(actualAAProperty);
        arcTL.xProperty().bind(actualAAProperty);


        return new Path(startPos,
                lineTop,
                arcTR,
                lineRight,
                arcBR,
                lineBottom,
                arcBL,
                lineLeft,
                arcTL,
                new ClosePath()
        );
    }

    /**
     * Creates necessary bindings for an actualCornerProperty
     * @param property the property which will be bound
     * @param valueHolder property that holds the input value
     * @param dependency1 dependency property 1 if overflow occurs
     * @param dependency2
     */
    private void createCornerBinding(
            SimpleDoubleProperty property, SimpleDoubleProperty valueHolder,
            SimpleDoubleProperty dependency1, SimpleDoubleProperty dependency2)
    {
        property.bind(Bindings.createDoubleBinding(() ->
                        {
                            var min = minProperty.get();
                            var unit = Window.getPixelConversionFactorProperty().get();  //TODO: fix,getUnitProperty().get();
                            var val = Math.min(valueHolder.get() * unit, min);
                            var hfMin = min * 0.5;

                            double overflow = val - hfMin;
                            if(overflow > 0)
                            {
                                //If corner exceeds 50% of shapes smaller bound we check for neighbour
                                //corners to limit its overflow to the remaining available space
                                var dep1 = dependency1.get() * unit;
                                var dep2 = dependency2.get() * unit;
                                var limit = hfMin - Math.min(Math.max(dep1, dep2), hfMin);
                                overflow = Math.min(overflow, limit);
                                return hfMin + overflow;
                            }

                            return val;
                        },
                        minProperty, valueHolder, dependency1, dependency2));
    }
}
