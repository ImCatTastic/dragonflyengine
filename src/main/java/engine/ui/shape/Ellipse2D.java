package engine.ui.shape;

import engine.ui.UIComponent;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Ellipse2D extends UIComponent
{
    public Ellipse2D()
    {
        Ellipse shape = new Ellipse();
        shape.radiusXProperty().bind(container_background.widthProperty().divide(2));
        shape.radiusYProperty().bind(container_background.heightProperty().divide(2));
        shape.centerXProperty().bind(container_background.widthProperty().divide(2));
        shape.centerYProperty().bind(container_background.heightProperty().divide(2));
        shape.fillProperty().bind(getUserStyle().fillProperty);
        addNodesBackground(shape);
    }
}
