package learnBot;

import engine.GameObject;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends GameObject
{
    private final Field[][] fields;
    public Board(int width, int height, double borderSize)
    {
        super(0, 0, 0);

        Rectangle body = addRectangle(
                0,
                0,
                width + (width + 1) * borderSize,
                height + (height + 1) * borderSize);

        body.setFill(new Color(64/255d,64/255d,64/255d,1));

        fields = new Field[width][height];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                fields[x][y] = (Field) addChild(new Field(x + borderSize * (x + 1),y + borderSize * (y + 1)));
    }
}
