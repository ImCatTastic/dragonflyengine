package learnBot.visualComponent;

import engine.util.ShapeBuilder;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import learnBot.Config;
import learnBot.FOPAnimation;

public class BlockVC extends VisualComponent
{
    Rectangle body;
    private final ObjectProperty<Color> fill = new SimpleObjectProperty<>(Color.WHITE);
    public BlockVC(int x, int y)
    {
        super(x, y, 0, 0);

        double factor = Config.INNER_FIELD_FACTOR;
        double factorOffset = (1 - factor) * 0.5;
        body = ShapeBuilder.createRectangle(0, 0, factor, factor);
        body.fillProperty().bind(fill);

        setX(getOffsetX(x) + factorOffset + Config.BORDER_SIZE_FACTOR);
        setY(getOffsetY(y) + factorOffset + Config.BORDER_SIZE_FACTOR);

        addShape(body);

        playCrash();
    }

    public void playCrash()
    {
        playAnimation(new FOPAnimation(0, 1, (t, val) -> fill.set(new Color(1, t, t,1)))
                .setDuration(1)
                .setInterpolator((t) -> 1 - Math.pow(1 - t, 3))
        );
    }
}
