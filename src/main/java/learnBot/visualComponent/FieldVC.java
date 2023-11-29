package learnBot.visualComponent;

import engine.GameObject;
import engine.util.ShapeBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FieldVC extends GameObject
{
    public FieldVC(double x, double y)
    {
        super(x, y, 0);

        Rectangle body = ShapeBuilder.createRectangle(1, 1);
        body.setFill(new Color(32 / 255d,32 / 255d,32 / 255d,1));

        Rectangle overlay = ShapeBuilder.createRectangle(1, 1);
        overlay.setFill(new Color(0 / 255d,0 / 255d,0 / 255d,0));

        addShape(body);
    }
}
