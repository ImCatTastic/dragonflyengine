package learnBot.visualComponent;

import engine.util.ShapeBuilder;
import javafx.scene.shape.Circle;
import learnBot.Config;

public class CoinVC extends VisualComponent
{
    public CoinVC(int x, int y)
    {
        super(x, y, 0, 0);

        double factor = Config.INNER_FIELD_FACTOR;
        double factorOffset = (1 - factor) * 0.5;
        Circle body = ShapeBuilder.createCircle(factor * 0.5);

        setX(getOffsetX(x) + factorOffset + Config.BORDER_SIZE_FACTOR);
        setY(getOffsetY(y) + factorOffset + Config.BORDER_SIZE_FACTOR);
        addShape(body);
    }

    @Override
    public void update()
    {
        super.update();
    }
}
