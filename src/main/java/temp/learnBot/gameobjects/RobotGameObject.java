package temp.learnBot.gameobjects;

import engine.animation.DoubleTransition;
import engine.animation.TransitionBuilder;
import engine.animation.AnimationManager;
import engine.audio.AudioManager;
import engine.rendering.ShapeRenderer;
import engine.shapePainter.RectangleShape;
import engine.util.Interpolator;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import temp.learnBot.*;
import org.jetbrains.annotations.NotNull;

public class RobotGameObject extends FOPGameObject
{
    private final static double DEFAULT_TELEPORT_DURATION = 0.25d;
    private final static double DEFAULT_MOVEMENT_DURATION = 0.25d;
    private final static double DEFAULT_ROTATION_DURATION = 0.2d;
    private DoubleTransition teleportAnimation;
    private DoubleTransition teleportRotationAnimation;
    private final DoubleTransition[] movementAnimations = new DoubleTransition[4];
    private DoubleTransition rotationAnimation;
    private final static double size = 0.85;
    private final Robot robot;
    public RobotGameObject(Robot robot)
    {
        super(robot.getX(), robot.getY(), ZDistribution.ROBOT.get());
        this.robot = robot;

        setSpeed(WorldManager.getSpeed());
        final double factor = WorldConfig.INNER_FIELD;
        final double factorOffset = (1 - factor) * 0.5;
        final double arcFactor = 0.33d;

        var renderer = new ShapeRenderer(size, size);
        var shape = new RectangleShape(0, 0, size, size, Color.RED);
        renderer.addShape(shape);
        this.setCanvasRenderer(renderer);
    }
    public void setSpeed(double speed)
    {
        speed = Math.max(0, Math.min(speed, WorldManager.speedLimit));
        final double teleportDuration = DEFAULT_TELEPORT_DURATION / speed;
        final double movementDuration = DEFAULT_MOVEMENT_DURATION / speed;
        final double rotationDuration = DEFAULT_ROTATION_DURATION / speed;
        final double offset = 1 + WorldConfig.BORDER_SIZE;

        var moveBuilder = new TransitionBuilder.Double(transform::setY, movementDuration)
                .setFrom(() -> transform.getPosition().y)
                .setDelta(offset)
                .setInterpolator(getCustomInterpolator(speed))
                .setOnComplete(() -> TasqueManager.completeTask(robot));

        movementAnimations[Direction.UP.ordinal()] = moveBuilder.build();
        movementAnimations[Direction.DOWN.ordinal()] = moveBuilder.setDelta(-offset).build();
        moveBuilder
                .setPropertySetter(transform::setX)
                .setFrom(() -> transform.getPosition().x);
        movementAnimations[Direction.LEFT.ordinal()] = moveBuilder.build();
        movementAnimations[Direction.RIGHT.ordinal()] = moveBuilder.setDelta(offset).build();

        teleportAnimation = new TransitionBuilder.Double(transform::setScale, teleportDuration)
                .setFrom(1d)
                .setTo(0.01d)
                .setReverse(true)
                .setInterpolator(Interpolator.EASE_IN)
                .setOnComplete(() -> TasqueManager.completeTask(robot))
                .build();

        teleportRotationAnimation = new TransitionBuilder.Double(transform::setRotation, teleportDuration * 2)
                .setFrom(transform::getRotation)
                .setDelta(720d)
                .setInterpolator(Interpolator.EASE_IN_OUT)
                .build();

        rotationAnimation = new TransitionBuilder.Double(transform::setRotation, rotationDuration)
                .setFrom(transform::getRotation)
                .setDelta(-90d)
                .setInterpolator(getCustomInterpolator(speed))
                .setOnComplete(() -> TasqueManager.completeTask(robot))
                .build();
    }
    public void playMove(@NotNull Direction direction)
    {
        Platform.runLater(() -> AnimationManager.queueAnimation(movementAnimations[direction.ordinal()]));
    }
    public void playTurnLeft()
    {
        Platform.runLater(() -> AnimationManager.queueAnimation(rotationAnimation));
    }
    public void playTeleport(int x, int y)
    {
        Platform.runLater(() ->
        {
            teleportAnimation.setOnHalfComplete(() -> transform.setPosition(convertPoint(x, y)));
            AnimationManager.queueAnimations(teleportRotationAnimation, teleportAnimation);
        });
    }
    public void playCollectCoin()
    {
        Platform.runLater(() ->
        {
            AudioManager.play(WorldManager.COIN_PICKUP_SOUND_ID);
        });
    }

    public void playCrash()
    {
        Platform.runLater(() ->
        {

        });
    }
}
