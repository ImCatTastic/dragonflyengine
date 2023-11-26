package engine.util;

import engine.Engine;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;

public class ShapeBuilder
{
    public static @NotNull Rectangle createRectangle(double width, double height)
    {
        Rectangle rectangle = new Rectangle();
        rectangle.widthProperty().bind(Engine.getUnits().scale().multiply(Engine.getUnits().refUnit() * width));
        rectangle.heightProperty().bind(Engine.getUnits().scale().multiply(Engine.getUnits().refUnit() * height));
        return rectangle;
    }
}
