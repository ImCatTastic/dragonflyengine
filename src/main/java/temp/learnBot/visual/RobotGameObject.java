package temp.learnBot.visual;

import engine.animation.DoubleTransition;
import engine.animation.TransitionBuilder;
import engine.animation.AnimationManager;
import engine.audio.AudioManager;
import engine.core.GameObject;
import engine.core.ModOperation;
import engine.rendering.ShapeRenderer;
import engine.spriteBuilder.*;
import engine.util.Alignment;
import engine.util.Interpolator;
import engine.util.RGBColor;
import engine.util.math.Vec2;
import engine.util.math.Vec3;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import temp.learnBot.*;
import org.jetbrains.annotations.NotNull;

public class RobotGameObject extends FOPGameObject
{
    private final static double DEFAULT_TELEPORT_DURATION = 0.25d;
    private final static double DEFAULT_MOVEMENT_DURATION = 0.25d;
    private final static double DEFAULT_ROTATION_DURATION = 0.175d;
    private DoubleTransition teleportAnimation;
    private DoubleTransition teleportRotationAnimation;
    private final DoubleTransition[] movementAnimations = new DoubleTransition[4];
    private DoubleTransition rotationAnimation;
    private final static double size = 0.80;
    private final Robot robot;
    private final GameObject robotVisual;
    private final GameObject arrowHandler;
    public RobotGameObject(Robot robot)
    {
        super(robot.getX(), robot.getY(), ZDistribution.ROBOT.get() + 100);
        this.robot = robot;

        robotVisual = new GameObject();
        arrowHandler = new GameObject();
        robotVisual.getTransform().setRotation(robot.getDirection().ordinal() * 90);
        robotVisual.setParent(this);
        arrowHandler.setParent(this);
        setSpeed(WorldManager.getSpeed());
    }
    public void createSprite()
    {
        Color prim1;
        Color prim2;
        Color sec;

        prim1 = new Color(0.1,0.3,0.65, 1);
        prim2 = new Color(0.2,0.5,0.9, 1);
        sec =   new Color(0.5,0.7,1, 1);

        switch (robot.getRobotFamily())
        {
            case SQUARE_AQUA ->
            {
                prim1 = Color.rgb(0,204,102);
                prim2 = Color.rgb(127,230,178);
                sec =   Color.rgb(166,224,194);
            }
            case SQUARE_BLUE ->
            {
                prim1 = new Color(1,0,0.1, 1);
                prim2 = new Color(1,0.49,0.54, 1);
                sec =   new Color(0.98,0.7,0.64, 1);
            }
            case SQUARE_GREEN ->
            {
                prim1 = Color.rgb(0,204,102);
                prim2 = Color.rgb(127,230,178);
                sec =   Color.rgb(166,224,194);
            }
            case SQUARE_ORANGE ->
            {
                prim1 = Color.rgb(255,153,0);
                prim2 = Color.rgb(255,204,127);
                sec =   Color.rgb(253,214,145);
            }
            case SQUARE_PURPLE ->
            {
                prim1 = new Color(1,0,0.1, 1);
                prim2 = new Color(1,0.49,0.54, 1);
                sec =   new Color(0.98,0.7,0.64, 1);
            }
            case SQUARE_RED ->
            {
                prim1 = new Color(1,0,0.1, 1);
                prim2 = new Color(1,0.49,0.54, 1);
                sec =   new Color(0.98,0.7,0.64, 1);
            }
            case SQUARE_YELLOW ->
            {
                prim1 = new Color(1,0,0.1, 1);
                prim2 = new Color(1,0.49,0.54, 1);
                sec =   new Color(0.98,0.7,0.64, 1);
            }
            case SQUARE_BLACK ->
            {
                prim1 = new Color(1,0,0.1, 1);
                prim2 = new Color(1,0.49,0.54, 1);
                sec =   new Color(0.98,0.7,0.64, 1);
            }
            case SQUARE_WHITE ->
            {
                prim1 = new Color(1,0,0.1, 1);
                prim2 = new Color(1,0.49,0.54, 1);
                sec =   new Color(0.98,0.7,0.64, 1);
            }
        }


        // Define the gradient stops
        Stop[] stops = new Stop[]{
                new Stop(0, prim2),
                new Stop(1, prim1)
        };

        // Create a linear gradient for the fill
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);



        var renderer = robotVisual.createRenderComponent(ShapeRenderer.class);

        var body = new RectangleShape(0, 0, size * 100, size * 100);
        body.setFill(gradient);
        body.setStroke(sec);
        body.setStrokeThickness(5);
        body.setRadius(10);
        renderer.addShape(body);

        var outerEye = new OvalShape(0,12,37.5,37.5);
        outerEye.setFill(Color.WHITE);
        outerEye.setStroke(sec);
        outerEye.setStrokeThickness(3);
        outerEye.setStrokeType(StrokeType.OUTER);
        outerEye.setAlignment(Alignment.TOP_CENTER);
        renderer.addShape(outerEye);

        var pupil = new OvalShape(0,17,15,15);
        pupil.setFill(Color.BLACK);
        pupil.setStrokeType(StrokeType.OUTER);
        pupil.setAlignment(Alignment.TOP_CENTER);
        renderer.addShape(pupil);

        WorldManager.getInstance().addObject(arrowIndicator);
        var arrowRenderer = arrowIndicator.createRenderComponent(ShapeRenderer.class);
        arrow = new OvalShape(0, 0,20,20);
        arrow.setFill(null);
        arrowRenderer.addShape(arrow);
    }
    private final GameObject arrowIndicator = new GameObject();
    private OvalShape arrow;

    public void setSpeed(double speed)
    {
        speed = Math.max(0, Math.min(speed, WorldManager.speedLimit));
        final double teleportDuration = DEFAULT_TELEPORT_DURATION / speed;
        final double movementDuration = DEFAULT_MOVEMENT_DURATION / speed;
        final double rotationDuration = DEFAULT_ROTATION_DURATION / speed;
        final double offset = 1 + VisualConstants.BORDER_SIZE;

        var moveBuilder = new TransitionBuilder.Double(transform::setY, movementDuration)
                .setFrom(() -> transform.getPosition().y)
                .setDelta(offset)
                .setInterpolator(getCustomInterpolator(speed))
                .setOnComplete(() ->
                               {
                                   TasqueManager.completeTask(robot);
                                   arrow.setFill(null);
                               });

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

        teleportRotationAnimation = new TransitionBuilder.Double(robotVisual.getTransform()::setRotation, teleportDuration * 2)
                .setFrom(robotVisual.getTransform()::getRotation)
                .setDelta(720d)
                .setInterpolator(Interpolator.EASE_IN_OUT)
                .build();

        rotationAnimation = new TransitionBuilder.Double(robotVisual.getTransform()::setRotation, rotationDuration)
                .setFrom(robotVisual.getTransform()::getRotation)
                .setDelta(-90d)
                .setInterpolator(getCustomInterpolator(speed))
                .setOnComplete(() -> TasqueManager.completeTask(robot))
                .build();
    }
    public void markDirection(@NotNull Direction direction)
    {
        Platform.runLater(() ->
                          {
                              double x = direction.getDx() * (VisualConstants.FIELD_SIZE + VisualConstants.BORDER_SIZE);
                              double y = direction.getDy() * (VisualConstants.FIELD_SIZE + VisualConstants.BORDER_SIZE);
                              arrowIndicator.getTransform().setPosition(transform.getPosition().add(new Vec3(x, y)));
                              arrow.setFill(Colors.PT_NEON_GREEN.fx);
                          });
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
            //AudioManager.play(WorldManager.COIN_PICKUP_SOUND_ID);
        });
    }

    public void playCrash()
    {
        Platform.runLater(() ->
        {

        });
    }

    @Override
    protected void update()
    {
        //transform.modRotation(1, ModOperation.ADD);
    }
}
