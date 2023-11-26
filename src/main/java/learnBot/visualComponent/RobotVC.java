package learnBot.visualComponent;

import engine.collider.BoxCollider2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import learnBot.Config;

public class RobotVC extends VisualComponent
{
    public RobotVC(int x, int y)
    {
        super(x, y,
                Config.borderSize + (1 - Config.innerFieldFactor) * 0.5,
                Config.borderSize + (1 - Config.innerFieldFactor) * 0.5
        );

        double factor = Config.innerFieldFactor;
        double factorOffset = (1 - factor) * 0.5;

        Rectangle body = addRectangle(0,0, factor, factor);
        body.setFill(new Color(128 / 255d,64 / 255d,128 / 255d,1));
        addCollider(new BoxCollider2D(factorOffset, factorOffset, factorOffset + factor, factorOffset + factor));
    }

    public void playMove()
    {
        double initPos = getWorldX();
        double newPos = getWorldX() + 1 + Config.borderSize;
        double progress = 0;

        while (progress < 1)
        {
            progress += 0.02;
            setExplicitX(initPos + (newPos - initPos) * progress);
            pause(5);
        }

        setExplicitX(newPos);
    }

    public void playTurnLeft()
    {
        double initRot = getRotation();
        double newRot = getRotation() - 90;

        double progress = 0;
        while (progress < 1)
        {
            progress += 0.02;
            this.setRotation(initRot + (newRot - initRot) * progress);
            pause(5);
        }

        setRotation((360 + getRotation()) % 360);
    }
}
