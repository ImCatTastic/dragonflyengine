package learnBot.visualComponent;

import engine.Engine;
import engine.util.Time;
import engine.collider.BoxCollider2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import learnBot.Config;
import learnBot.Direction;
import learnBot.Sync;
import learnBot.World;

import java.util.function.Function;

import static engine.Engine.frameTime;

public class RobotVC extends VisualComponent
{
    private enum RobotAnimation
    {
        NONE,
        MOVE_H,
        MOVE_V,
        ROTATE
    }
    public RobotVC(int x, int y)
    {
        super(x, y,
                Config.borderSize + (1 - Config.innerFieldFactor) * 0.5,
                Config.borderSize + (1 - Config.innerFieldFactor) * 0.5
        );

        speed = World.speed;
        double factor = Config.innerFieldFactor;
        double factorOffset = (1 - factor) * 0.5;

        Rectangle body = addRectangle(0,0, factor, factor);

        body.arcWidthProperty().bind(Engine.getUnits().scale().multiply(Engine.getUnits().refUnit() * 0.33));
        body.arcHeightProperty().bind(Engine.getUnits().scale().multiply(Engine.getUnits().refUnit() * 0.33));

        body.setFill(new Color(210 / 255d,64 / 255d,64 / 255d,1));
        addCollider(new BoxCollider2D(factorOffset, factorOffset, factorOffset + factor, factorOffset + factor));
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    private double speed;
    private double startValue;
    private double endValue;
    private double progress;
    private RobotAnimation currentAnimation = RobotAnimation.NONE;
    private Function<Double, Double> interpolator;

    public void playMove(Direction direction)
    {
        if(currentAnimation != RobotAnimation.NONE)
            return;

        interpolator = getCustomInterpolator(speed);

        if(direction.isHorizontal())
        {
            startValue = getWorldX();
            endValue = getWorldX() + (1 + Config.borderSize) * direction.getDx();
            currentAnimation = RobotAnimation.MOVE_H;
        }

        else
        {
            startValue = getWorldY();
            endValue = getWorldY() + (1 + Config.borderSize) * direction.getDy();
            currentAnimation = RobotAnimation.MOVE_V;
        }

        progress = 0;
    }

    public void playTurnLeft()
    {
        if(currentAnimation != RobotAnimation.NONE)
            return;

        interpolator = getCustomInterpolator(speed);

        startValue = getRotation();
        endValue = getRotation() - 90;
        currentAnimation = RobotAnimation.ROTATE;
        progress = 0;
    }

    private final static double moveSpeed = 0.0185d;
    private final static double rotationSpeed = 0.025d;
    @Override
    public void update()
    {
        if(currentAnimation != RobotAnimation.NONE)
        {
            double normSpeed = (0.1 * Time.deltaTime) * speed;
            progress += currentAnimation == RobotAnimation.ROTATE ? rotationSpeed * normSpeed : moveSpeed * normSpeed;
            progress = progress > 1 ? 1 : progress;

            double val = startValue + (endValue - startValue) * interpolator.apply(progress);

            switch (currentAnimation)
            {
                case MOVE_H -> setX(val);
                case MOVE_V -> setY(val);
                case ROTATE -> setRotation(val);
            }

            if(progress >= 1)
            {
                progress = 0;
                currentAnimation = RobotAnimation.NONE;
                Sync.giveSignal();
            }
        }
    }

    //setRotation((360 + getRotation()) % 360);
}
