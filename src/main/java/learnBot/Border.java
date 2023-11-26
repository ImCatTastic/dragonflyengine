package learnBot;

import engine.GameObject;
import engine.collider.BoxCollider2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Border extends GameObject
{
    public Border(Direction direction)
    {
        super(
                direction == Direction.RIGHT ? World.getWidth() + World.getWidth() * Config.borderSize : 0,
                direction == Direction.DOWN ? World.getHeight() + (World.getHeight()) * Config.borderSize : 0,
                0);

        double border = Config.borderSize;
        double worldW = World.getWidth() + World.getWidth() * border;
        double worldH = World.getHeight() + World.getHeight() * border;

        double x = direction == Direction.RIGHT ? worldW : 0;
        double y = direction == Direction.DOWN ? worldH : 0;

        double width    = direction.isHorizontal() ? worldW + border : border;
        double height   = direction.isHorizontal() ? border : worldH + border;

        Rectangle body = addRectangle(0, 0, width, height);
        body.setFill(new Color(255 / 255d,255 / 255d,255 / 255d,1));
        body.setFill(null);

        addCollider(new BoxCollider2D(0, 0, width, height));
    }
}
