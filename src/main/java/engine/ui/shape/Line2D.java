package engine.ui.shape;

import engine.ui.UIComponent;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class Line2D extends UIComponent
{

    public Line2D()
    {
        var hProperty = getHeightProperty().multiply(0.5);

        Line shape = new Line();
        shape.startYProperty().bind(hProperty);
        shape.startXProperty().bind(hProperty);
        shape.endYProperty().bind(hProperty);
        shape.endXProperty().bind(getWidthProperty().subtract(getHeightProperty().multiply(0.5)));
        shape.strokeWidthProperty().bind(getHeightProperty());
        shape.fillProperty().bind(getUserStyle().fillProperty);
        shape.strokeProperty().bind(getUserStyle().strokeProperty.get().paintProperty);

        shape.strokeLineCapProperty().set(StrokeLineCap.ROUND);

        addNodesBackground(shape);
    }

    public void setThickness()
    {

    }
}
