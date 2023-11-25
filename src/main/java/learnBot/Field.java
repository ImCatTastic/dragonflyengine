package learnBot;

import engine.GameObject;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Field extends GameObject
{
    public Field(double x, double y)
    {
        super(x, y, 0);

        Rectangle body = addRectangle(x, y);
        body.setFill(Color.BLUE);
    }
}
