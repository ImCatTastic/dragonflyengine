package temp.learnBot.gameobjects;

import engine.core.GameObject;
import engine.rendering.ShapeRenderer;
import engine.rendering.SpriteRenderer;
import engine.shapePainter.TextShape;
import engine.util.PivotPoint;
import engine.util.math.Vec2;
import engine.util.math.Vec3;

import static temp.learnBot.WorldManager.COIN_SPRITE_ID;

public class CoinGameobject extends GameObject
{
    private final TextShape textShape;
    private final GameObject textHolder;
    public CoinGameobject(Vec2 position)
    {
        transform.setPosition(new Vec3(position, 1));
        setCanvasRenderer(new SpriteRenderer(COIN_SPRITE_ID, 0.7, 0.7));

        var textRenderer = new ShapeRenderer(0.7, 0.7);
        textShape = new TextShape("-", 0.4);
        textShape.setAlignment(PivotPoint.BOTTOM_RIGHT);
        textShape.setPosition(new Vec2(-0.075, -0.075));
        textRenderer.addShape(textShape);
        textRenderer.setPivotPoint(PivotPoint.CENTER);

        textHolder = new GameObject();
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
        //transform.setRotation(transform.getRotation() + 1);
    }
}