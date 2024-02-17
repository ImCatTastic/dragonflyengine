package temp.learnBot.gameobjects;

import engine.javafx.*;
import engine.javafx.shapePainter.TextShape;
import engine.mathUtil.Vec2;

import static temp.learnBot.WorldManager.COIN_SPRITE_ID;

public class CoinGameobject extends GameObject
{
    private final TextShape textShape;
    public CoinGameobject(Vec2 position)
    {
        //super(ZDistribution.COIN.get());
        transform.setPosition(position);
        transform.setScale(0.7);

        var r = new SpriteRenderer(COIN_SPRITE_ID);
        r.setPivotOrientation(Alignment.CENTER);
        this.setCanvasRenderer(r);

        var textRenderer = new ShapeRenderer(1, 1);
        textShape = new TextShape("-", 28);
        textShape.setAlignment(Alignment.BOTTOM_RIGHT);
        textShape.setPosition(new Vec2(-0.15, -0.15));
        textRenderer.addShape(textShape);
        textRenderer.setPivotOrientation(Alignment.CENTER);

        var textHolder = new GameObject();
        textHolder.setCanvasRenderer(textRenderer);
        textHolder.setParent(this);
    }
    public void updateCount(int count)
    {
        textShape.setText(String.valueOf(count));
    }

    @Override
    protected void update()
    {
        transform.setRotation(transform.getRotation() + 1);
    }
}