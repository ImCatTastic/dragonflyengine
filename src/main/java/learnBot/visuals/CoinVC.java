package learnBot.visuals;

import javafx.scene.shape.Circle;

public class CoinVC extends FOPVisualComponent
{
    public CoinVC(int x, int y)
    {
        super(x, y, 0, 0);

        double factor = Config.INNER_FIELD_FACTOR;
        double factorOffset = (1 - factor) * 0.5;
        Circle body = new Circle();
        //bind(factor * 0.5, body.radiusProperty());
        body.centerXProperty().bind(body.radiusProperty().multiply(0.5));




        setX(getOffsetX(x) + factorOffset + Config.BORDER_SIZE_FACTOR);
        setY(getOffsetY(y) + factorOffset + Config.BORDER_SIZE_FACTOR);
        addShape(body);
    }

    @Override
    public void update()
    {
        super.update();
    }

    @Override
    public void fixedUpdate() {

    }
}
