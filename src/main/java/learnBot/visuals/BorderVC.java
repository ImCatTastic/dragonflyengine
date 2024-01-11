package learnBot.visuals;

import engine.core.GameObject;
import engine.physics.collision.BoxCollider2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import learnBot.Direction;
import learnBot.World;

public class BorderVC extends FOPVisualComponent
{
    public BorderVC(Direction direction)
    {
        super(0,0, 0);

        double border = Config.BORDER_SIZE_FACTOR;
        double worldW = World.getWidth() + World.getWidth() * border;
        double worldH = World.getHeight() + World.getHeight() * border;

        double x = direction == Direction.RIGHT ? worldW : 0;
        double y = direction == Direction.DOWN ? worldH : 0;
        setX(x);
        setY(y);

        double width    = !direction.isHorizontal() ? worldW + border : border;
        double height   = !direction.isHorizontal() ? border : worldH + border;

        Rectangle body = new Rectangle();
        //bind(width, body.widthProperty());
        //bind(height, body.heightProperty());

        body.setFill(new Color(255 / 255d,255 / 255d,255 / 255d,1));
        addShape(body);

        addCollider(new BoxCollider2D(0, 0, width, height));
    }

    @Override
    public void fixedUpdate() {

    }
}
