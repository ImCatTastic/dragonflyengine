package temp.learnBot.gameobjects;

import engine.core.GameObject;
import engine.rendering.ShapeRenderer;
import engine.shapePainter.RectangleShape;
import engine.util.math.Vec2;
import javafx.scene.paint.Color;

public class FieldGameObject extends GameObject
{
    private final RectangleShape shape;
    public FieldGameObject(Vec2 position)
    {
        super();
        transform.setPosition(position);

        var color = new Color(255/255d,255/255d,255/255d,1); //TODO: color palette
        var renderer = new ShapeRenderer(1, 1);
        shape = new RectangleShape(0, 0, 1, 1, Color.BLACK);
        renderer.addShape(new RectangleShape(0, 0, 1, 1, Color.BLACK));
        this.setCanvasRenderer(renderer);
    }
    public RectangleShape getShape()
    {
        return shape;
    }
}