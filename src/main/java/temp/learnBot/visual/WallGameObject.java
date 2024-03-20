package temp.learnBot.visual;

import engine.animation.AnimationManager;
import engine.animation.TransitionBuilder;
import engine.animation.Vec3Transition;
import engine.rendering.ShapeRenderer;
import engine.spriteBuilder.PaintableShape;
import engine.spriteBuilder.RectangleShape;
import engine.spriteBuilder.SpriteBuilder;
import engine.spriteBuilder.StrokeType;
import engine.util.Interpolator;
import engine.util.PropertySetter;
import engine.util.math.Vec3;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import temp.learnBot.Wall;

public class WallGameObject extends FOPGameObject
{
    private final RectangleShape shape;
    public WallGameObject(Wall wall)
    {
        super(
                wall.getDirection().isHorizontal() ? wall.getX() + 0.5 * wall.getDirection().getDx() : wall.getX(),
                wall.getDirection().isHorizontal() ? wall.getY(): wall.getY() + 0.5 * wall.getDirection().getDy()
                , 0);

        double border = VisualConstants.BORDER_SIZE;

        boolean isHorizontal = wall.getDirection().isHorizontal();
        double width    = isHorizontal ? border : 1 + border * 2;
        double height   = isHorizontal ? VisualConstants.FIELD_SIZE + border * 2 : border;

        var renderer = createRenderComponent(ShapeRenderer.class);
        //shape = new RectangleShape(0, 0, width, height, Colors.NEON_YELLOW);

        shape = new RectangleShape(0, 0, width * SpriteBuilder.getModifier(), height* SpriteBuilder.getModifier());
        shape.setFill(Colors.PT_NEON_CYAN.fx);
        shape.setRadius(100);
        shape.setStrokeType(StrokeType.OUTER);
        renderer.addShape(shape);


        var endColor = Colors.PT_NEON_CYAN;
        var startColor = Colors.PT_NEON_PINK;

        PropertySetter<Vec3> setter = (vec3) -> shape.setFill(new Color(vec3.x, vec3.y, vec3.z, 1));
        detectAnimationBuilder = new TransitionBuilder.Vec3(setter, 3)
                .setFrom(() -> startColor.vec3)
                .setTo(() -> endColor.vec3)
                .setInterpolator(Interpolator.EASE_OUT)
                .setOnComplete(() -> getTransform().setZ(getTransform().getLocalZ() - 1))
                .build();

        var gameOverColorStart = Colors.PT_NEON_RED.vec3;
        var gameOverColorEnd = Colors.PT_NEON_CYAN.vec3;

        gameOverAnimation = new TransitionBuilder.Vec3(setter, 3)
                .setFrom(() -> gameOverColorStart)
                .setTo(() -> gameOverColorEnd)
                .setInterpolator(Interpolator.EASE_OUT)
                .build();
    }

    private final Vec3Transition detectAnimationBuilder;
    private final Vec3Transition gameOverAnimation;
    public void playDetectedAnimation()
    {
        Platform.runLater(() ->
        {
            getTransform().setZ(getTransform().getLocalZ() + 1);
            AnimationManager.queueAnimation(detectAnimationBuilder);
        });
    }

    public void playGameOverAnimation()
    {
        Platform.runLater(() ->
        {
            AnimationManager.queueAnimation(gameOverAnimation);
        });
    }
}
