package learnBot.visualComponent;

import engine.GameObject;
import engine.util.ShapeBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import learnBot.Field;

public class BoardVC extends GameObject
{
    public BoardVC(int width, int height, double borderSize)
    {
        super(0, 0, 0);

        Rectangle body = ShapeBuilder.createRectangle(
                0,
                0,
                width + (width + 1) * borderSize,
                height + (height + 1) * borderSize);

        body.setFill(new Color(64/255d,64/255d,64/255d,1));
        addShape(body);
    }
}
