package engine.ui.shape;

import engine.core.SceneManager;
import engine.ui.Anchor;
import engine.ui.UIComponent;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Rectangle2D extends UIComponent
{
    private final Rectangle rectangle = new Rectangle(); //TODO: set to private
    public void setFill(Color fill)
    {
        rectangle.setFill(fill);
    }
    public void setArcWidth(double value, boolean scale)
    {

    }
    public void setArcHeight(double value, boolean scale)
    {

    }
    public Rectangle2D()
    {
        rectangle.setArcWidth(500);
        rectangle.setArcHeight(50);

        rectangle.widthProperty().bind(container.widthProperty());
        rectangle.heightProperty().bind(container.heightProperty());
        addNodes(rectangle);
    }
}