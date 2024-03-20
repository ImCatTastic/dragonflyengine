package temp.learnBot.visual;

import engine.animation.AnimationManager;
import engine.animation.TransitionBuilder;
import engine.animation.Vec3Transition;
import engine.rendering.ShapeRenderer;
import engine.spriteBuilder.RectangleShape;
import engine.spriteBuilder.SpriteBuilder;
import engine.util.Interpolator;
import engine.util.PropertySetter;
import engine.util.math.Vec3;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import temp.learnBot.Block;

public class BlockGameObject extends FOPGameObject
{
    private final static double size = 0.85;
    public BlockGameObject(Block block)
    {
        super(block.getX(), block.getY(), 0);

        var renderer = createRenderComponent(ShapeRenderer.class);
        var _size = size * SpriteBuilder.getModifier();
        var shape = new RectangleShape(0, 0, _size, _size);
        shape.setFill(Colors.PT_NEON_CYAN.fx);
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

        gameOverAnimation = new TransitionBuilder.Vec3(setter, 2)
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
