package engine.util;

import engine.core.Engine;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

public class ShapeBuilder
{
    /*
    public static @NotNull Rectangle createRectangle(double x, double y, double width, double height)
    {
        Rectangle rectangle = new Rectangle();
        Engine.bind(rectangle.widthProperty(), width);
        Engine.bind(rectangle.heightProperty(), height);
        rectangle.setX(x);
        rectangle.setY(y);
        return rectangle;
    }

    public static @NotNull Rectangle createRectangle(double width, double height)
    {
        Rectangle rectangle = new Rectangle();
        Engine.bind(rectangle.widthProperty(), width);
        Engine.bind(rectangle.heightProperty(), height);
        return rectangle;
    }

    public static @NotNull Circle createCircle(double radius)
    {
        Circle circle = new Circle();
        Engine.bind(circle.radiusProperty(), radius);
        circle.centerXProperty().bind(circle.radiusProperty().multiply(0.5));

        return circle;
    }
     */
}
