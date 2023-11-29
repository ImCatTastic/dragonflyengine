package learnBot.visualComponent;

import engine.Engine;
import engine.animation.AnimationProperties;
import engine.animation.DoubleTransition;
import engine.animation.TransitionBuilder;
import engine.collider.BoxCollider2D;
import engine.util.Interpolator;
import engine.util.ShapeBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import learnBot.*;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class RobotVC extends VisualComponent
{
    private final static double DEFAULT_TELEPORT_DURATION = 0.225d;
    private final static double DEFAULT_MOVEMENT_DURATION = 0.25d;
    private final static double DEFAULT_ROTATION_DURATION = 0.2d;
    private DoubleTransition teleportAnimation;
    private final DoubleTransition[] movementAnimations = new DoubleTransition[4];
    private DoubleTransition rotationAnimation;
    private final Rectangle body;
    public RobotVC(int x, int y)
    {
        super(x, y,Config.BORDER_SIZE_FACTOR + (1 - Config.INNER_FIELD_FACTOR) * 0.5);

        setSpeed(World.speed);
        double factor = Config.INNER_FIELD_FACTOR;
        double factorOffset = (1 - factor) * 0.5;

        body = ShapeBuilder.createRectangle(0,0, factor, factor);
        body.arcWidthProperty().bind(Engine.getUnits().scale().multiply(Engine.getUnits().refUnit() * 0.33));
        body.arcHeightProperty().bind(Engine.getUnits().scale().multiply(Engine.getUnits().refUnit() * 0.33));

        body.setFill(new Color(210 / 255d,64 / 255d,64 / 255d,1));
        addCollider(new BoxCollider2D(factorOffset, factorOffset, factorOffset + factor, factorOffset + factor));

        Rectangle zt = ShapeBuilder.createRectangle(factor * 0.5,factor * 0.5, factor * 0.25, factor * 0.25);
        zt.xProperty().bind(Engine.getUnits().scale().multiply(Engine.getUnits().refUnit() * factor * 0.25));

        addShape(body);
        addShape(zt);
    }
    public void setSpeed(double speed)
    {
        final double teleportDuration = DEFAULT_TELEPORT_DURATION / speed;
        final double movementDuration = DEFAULT_MOVEMENT_DURATION / speed;
        final double rotationDuration = DEFAULT_ROTATION_DURATION / speed;
        final double offset = 1 + Config.BORDER_SIZE_FACTOR;

        var moveBuilder = new TransitionBuilder.Double()
                .setOnComplete(Sync::giveSignal)
                .setInterpolator(getCustomInterpolator(speed))
                .setDuration(movementDuration)
                .setPropertySetter(this::setY)
                .setFrom(this::getWorldY)
                .setBy(-offset);

        var t = moveBuilder.build();;

        movementAnimations[Direction.UP.ordinal()] = (DoubleTransition) moveBuilder.build();

        //movementAnimations[Direction.DOWN.ordinal()] = (DoubleTransition) moveBuilder.setTo(offset).build();
        //moveBuilder.setPropertySetter(this::setX).setFrom(this::getWorldX);
        //movementAnimations[Direction.RIGHT.ordinal()] = (DoubleTransition) moveBuilder.setTo(offset).build();
        //movementAnimations[Direction.LEFT.ordinal()] = (DoubleTransition) moveBuilder.setTo(-offset).build();


        /*
        teleportAnimation = (DoubleTransition) new DoubleTransition(this::setScale, teleportDuration, true)
                .setFrom(1d)
                .setTo(0.01d)
                .setInterpolator(Interpolator.EASE_IN)
                .setOnComplete(Sync::giveSignal);

        rotationAnimation = (DoubleTransition) new DoubleTransition(this::setRotation, rotationDuration)
                .setFrom(this::getRotation)
                .setBy(-90d)
                .setInterpolator(getCustomInterpolator(speed))
                .setOnComplete(Sync::giveSignal);

         */
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
        //queueAnimation(new Transition(getRotation(), getRotation() + 720, teleportDuration * 2, (t, val) -> setRotation(val), false).setInterpolator(Interpolator.EASE_IN_OUT));
        queueAnimation(teleportAnimation.setOnHalfComplete(() -> setPosition(getOffsetX(x), getOffsetY(y))));
    }
}
