package learnBot.visuals;

import engine.animation.DoubleTransition;
import engine.animation.TransitionBuilder;
import engine.physics.collision.BoxCollider2D;
import engine.util.Interpolator;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import learnBot.*;
import org.jetbrains.annotations.NotNull;

public class RobotVC extends FOPVisualComponent
{
    private final static double DEFAULT_TELEPORT_DURATION = 0.25d;
    private final static double DEFAULT_MOVEMENT_DURATION = 0.25d;
    private final static double DEFAULT_ROTATION_DURATION = 0.2d;
    private DoubleTransition teleportAnimation;
    private DoubleTransition teleportRotationAnimation;
    private final DoubleTransition[] movementAnimations = new DoubleTransition[4];
    private DoubleTransition rotationAnimation;
    private final Rectangle body;
    public RobotVC(int x, int y)
    {
        super(x, y,Config.BORDER_SIZE_FACTOR + (1 - Config.INNER_FIELD_FACTOR) * 0.5);

        setSpeed(WorldEngine.getSpeed());
        final double factor = Config.INNER_FIELD_FACTOR;
        final double factorOffset = (1 - factor) * 0.5;
        final double arcFactor = 0.33d;

        body = new Rectangle();
        //bind(factor, body.widthProperty(), body.heightProperty());
        //bind(arcFactor, body.arcWidthProperty(), body.arcHeightProperty());

        body.setFill(new Color(210 / 255d,64 / 255d,64 / 255d,1));
        addCollider(new BoxCollider2D(factorOffset, factorOffset, factorOffset + factor, factorOffset + factor));

        Rectangle zt = new Rectangle();
        zt.setX(factor * 0.5d);
        zt.setY(factor * 0.5d);
        //bind(factor * 0.25d, zt.widthProperty(), zt.heightProperty());

        //bind(factor, body.widthProperty(), body.heightProperty());
        //bind(factor * 0.25d, zt.xProperty());

        addShape(body);
        addShape(zt);
    }
    public void setSpeed(double speed)
    {
        final double teleportDuration = DEFAULT_TELEPORT_DURATION / speed;
        final double movementDuration = DEFAULT_MOVEMENT_DURATION / speed;
        final double rotationDuration = DEFAULT_ROTATION_DURATION / speed;
        final double offset = 1 + Config.BORDER_SIZE_FACTOR;

        var moveBuilder = new TransitionBuilder.Double(this::setY, movementDuration)
                .setFrom(this::getWorldY)
                .setDelta(-offset)
                .setInterpolator(getCustomInterpolator(speed))
                .setOnComplete(Sync::giveSignal);

        movementAnimations[Direction.UP.ordinal()] = moveBuilder.build();

        movementAnimations[Direction.DOWN.ordinal()] = moveBuilder.setDelta(offset).build();
        moveBuilder
                .setPropertySetter(this::setX)
                .setFrom(this::getWorldX);
        movementAnimations[Direction.RIGHT.ordinal()] = moveBuilder.build();
        movementAnimations[Direction.LEFT.ordinal()] = moveBuilder.setDelta(-offset).build();

        teleportAnimation = new TransitionBuilder.Double(this::setScale, teleportDuration)
                .setFrom(1d)
                .setTo(0.01d)
                .setReverse(true)
                .setInterpolator(Interpolator.EASE_IN)
                .setOnComplete(Sync::giveSignal)
                .build();

        teleportRotationAnimation = new TransitionBuilder.Double(this::setRotation, teleportDuration * 2)
                .setFrom(this::getWorldRotation)
                .setDelta(720d)
                .setInterpolator(Interpolator.EASE_IN_OUT)
                .build();

        rotationAnimation = new TransitionBuilder.Double(this::setRotation, rotationDuration)
                .setFrom(this::getWorldRotation)
                .setDelta(-90d)
                .setInterpolator(getCustomInterpolator(speed))
                .setOnComplete(Sync::giveSignal)
                .build();
    }
    public void playMove(@NotNull Direction direction)
    {
        queueAnimation(movementAnimations[direction.ordinal()]);
    }
    public void playTurnLeft()
    {
        queueAnimation(rotationAnimation);
    }
    public void playTeleport(int x, int y)
    {
        teleportAnimation.setOnHalfComplete(() -> setPosition(getOffsetX(x), getOffsetY(y)));
        queueAnimations(teleportRotationAnimation, teleportAnimation);
    }
    public void playCrash()
    {

    }

    @Override
    public void fixedUpdate() {

    }
}
