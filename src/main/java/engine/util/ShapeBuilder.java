package engine.util;

import engine.Engine;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

public class ShapeBuilder
{
    public static @NotNull Rectangle createRectangle(double x, double y, double width, double height)
    {
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(Engine.getUnits().scale().multiply(Engine.getUnits().refUnit() * width));
        rectangle.heightProperty().bind(Engine.getUnits().scale().multiply(Engine.getUnits().refUnit() * height));
        rectangle.setX(x);
        rectangle.setY(y);
        return rectangle;
    }

    public static @NotNull Rectangle createRectangle(double width, double height)
    {
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(Engine.getUnits().scale().multiply(Engine.getUnits().refUnit() * width));
        rectangle.heightProperty().bind(Engine.getUnits().scale().multiply(Engine.getUnits().refUnit() * height));
        return rectangle;
    }

    public static @NotNull Circle createCircle(double radius)
    {
        Circle circle = new Circle();
        circle.radiusProperty().bind(Engine.getUnits().scale().multiply(Engine.getUnits().refUnit() * radius));
        circle.centerXProperty().bind(circle.radiusProperty().multiply(0.5));

        return circle;
    }

}
