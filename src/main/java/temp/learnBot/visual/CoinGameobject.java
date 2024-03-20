package temp.learnBot.visual;

import engine.core.GameObject;
import engine.rendering.SpriteRenderer;
import engine.rendering.TextRenderer;
import engine.util.PivotPoint;
import engine.util.math.Vec2;
import engine.util.math.Vec3;

import static temp.learnBot.WorldManager.COIN_SPRITE_ID;

public class CoinGameobject extends GameObject
{
    private final TextRenderer textRenderer;
    public CoinGameobject(Vec2 position)
    {
        transform.setPosition(new Vec3(position, 1));
        var renderer = createRenderComponent(SpriteRenderer.class);
        renderer.setSprite(COIN_SPRITE_ID);
        renderer.setDimensions(0.75, 0.75);

        var textHolder = new GameObject();
        textHolder.setParent(this);
        textHolder.getTransform().setPosition(0,0);
        textHolder.getTransform().setZ(0);

        textRenderer = textHolder.createRenderComponent(TextRenderer.class);
        textRenderer.setPivotPoint(PivotPoint.CENTER);
        textRenderer.setFontSize(0.44d);
    }

    public void updateCount(int count)
    {
        textRenderer.setText(String.valueOf(count));
    }
}