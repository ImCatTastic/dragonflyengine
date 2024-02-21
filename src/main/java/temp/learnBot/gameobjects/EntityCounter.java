package temp.learnBot.gameobjects;

import engine.core.GameObject;
import engine.rendering.ShapeRenderer;
import engine.util.math.Vec2;

public class EntityCounter extends GameObject
{
    private int count = 0;
    public EntityCounter(Vec2 position)
    {
        transform.setPosition(position);
        var renderer = new ShapeRenderer(1, 1);
        this.setCanvasRenderer(renderer);
    }
}