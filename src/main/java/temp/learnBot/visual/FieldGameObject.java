package temp.learnBot.visual;

import engine.core.GameObject;
import engine.rendering.ShapeRenderer;
import engine.spriteBuilder.RectangleShape;
import engine.spriteBuilder.SpriteBuilder;
import engine.util.math.Vec2;

public class FieldGameObject extends GameObject
{
    private final RectangleShape shape;
    public FieldGameObject(Vec2 position)
    {
        transform.setPosition(position);

        var fs = VisualConstants.FIELD_SIZE * SpriteBuilder.getModifier();
        var renderer = createRenderComponent(ShapeRenderer.class);
        shape = new RectangleShape(0, 0, fs, fs);
        shape.setFill(Colors.FIELD);

        renderer.addShape(shape);
    }

    public RectangleShape getShape()
    {
        return shape;
    }
}