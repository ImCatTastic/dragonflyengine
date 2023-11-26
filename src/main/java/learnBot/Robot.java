package learnBot;

import learnBot.visualComponent.RobotVC;

public class Robot extends Entity
{
    private Direction direction = Direction.UP;
    private RobotVC vc;

    public void setSpeed(double speed)
    {
        if(!Config.headlessModeEnabled())
            vc.setSpeed(speed);
    }

    public Robot(int x, int y)
    {
        super(x, y, Direction.UP);

        if(!Config.headlessModeEnabled())
        {
            this.vc = new RobotVC(x, y);
        }
    }

    public void move()
    {
        if(!Config.headlessModeEnabled())
        {
            vc.playMove(direction);
            Sync.waitForSignal();
        }
    }

    public void turnLeft()
    {
        direction = Direction.values()[(direction.ordinal() + 1) % Direction.values().length];
        if(!Config.headlessModeEnabled())
        {
            vc.playTurnLeft();
            Sync.waitForSignal();
        }
    }
}
