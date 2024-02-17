package temp.learnBot.gameobjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import temp.learnBot.Direction;
import org.jetbrains.annotations.NotNull;

public class WallVC extends FOPGameObject
{
    public WallVC(int x, int y, @NotNull Direction direction)
    {
        super(direction.isHorizontal() ? x + 1 : x, y, 0);

        double border = WorldConfig.BORDER_SIZE;

        double width    = direction.isHorizontal() ? border : 1 + border * 2;
        double height   = direction.isHorizontal() ? 1 + border * 2 : border;

        //addCollider(new BoxCollider2D(x, y, x + width + border, y + height + border));

        Rectangle body = new Rectangle();
        //bind(width, body.widthProperty());
        //bind(height, body.heightProperty());

        body.setFill(new Color(255 / 255d,255 / 255d,255 / 255d,1));

        /*
        addShape(body);

         */
    }
}
