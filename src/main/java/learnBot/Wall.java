package learnBot;

import engine.GameObject;
import engine.collider.BoxCollider2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends GameObject
{
    public Wall(Direction direction)
    {
        super(
                direction == Direction.RIGHT ? World.getWidth() + World.getWidth() * Config.borderSize : 0,
                direction == Direction.DOWN ? World.getHeight() + (World.getHeight()) * Config.borderSize  : 0,
                0);

        double border = Config.borderSize;
        double worldW = World.getWidth() + World.getWidth() * border;
        double worldH = World.getHeight() + World.getHeight() * border;

        double x = direction == Direction.RIGHT ? worldW : 0;
        double y = direction == Direction.DOWN ? worldH : 0;

        double width    = direction.isHorizontal() ? worldW + border : border;
        double height   = direction.isHorizontal() ? border : worldH + border;

        addCollider(new BoxCollider2D(x, y, x + width, y + height));

        Rectangle body = addRectangle(x, y, width, height);
        body.setFill(new Color(255 / 255d,255 / 255d,255 / 255d,1));
    }
}
