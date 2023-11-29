package learnBot;

import engine.GameObject;
import engine.collider.BoxCollider2D;
import engine.util.ShapeBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Border extends GameObject
{
    public Border(Direction direction)
    {
        super(0,0);

        double border = Config.BORDER_SIZE_FACTOR;
        double worldW = World.getWidth() + World.getWidth() * border;
        double worldH = World.getHeight() + World.getHeight() * border;

        double x = direction == Direction.RIGHT ? worldW : 0;
        double y = direction == Direction.DOWN ? worldH : 0;
        setX(x);
        setY(y);

        double width    = !direction.isHorizontal() ? worldW + border : border;
        double height   = !direction.isHorizontal() ? border : worldH + border;

        Rectangle body = ShapeBuilder.createRectangle(0, 0, width, height);
        body.setFill(new Color(255 / 255d,255 / 255d,255 / 255d,1));
        addShape(body);

        addCollider(new BoxCollider2D(0, 0, width, height));
    }
}
