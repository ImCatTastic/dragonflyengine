package learnBot.visualComponent;

import engine.collider.BoxCollider2D;
import engine.util.ShapeBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import learnBot.Config;
import learnBot.Direction;
import org.jetbrains.annotations.NotNull;

public class WallVC extends VisualComponent
{
    public WallVC(int x, int y, @NotNull Direction direction)
    {
        super(direction.isHorizontal() ? x + 1 : x, y, 0);

        double border = Config.BORDER_SIZE_FACTOR;

        double width    = direction.isHorizontal() ? border : 1 + border * 2;
        double height   = direction.isHorizontal() ? 1 + border * 2 : border;

        addCollider(new BoxCollider2D(x, y, x + width + border, y + height + border));

        Rectangle body = ShapeBuilder.createRectangle(0, 0, width, height);
        body.setFill(new Color(255 / 255d,255 / 255d,255 / 255d,1));

        addShape(body);
    }
}
