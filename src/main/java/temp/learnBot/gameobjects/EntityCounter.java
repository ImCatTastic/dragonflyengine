package temp.learnBot.gameobjects;

import engine.javafx.GameObject;
import engine.javafx.ShapeRenderer;
import engine.javafx.SpriteRenderer;
import engine.mathUtil.Vec2;

import static temp.learnBot.WorldManager.COIN_SPRITE_ID;

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