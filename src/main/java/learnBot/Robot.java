package learnBot;

import learnBot.visualComponent.RobotVC;

public class Robot extends Entity
{
    private double speed = -1;
    private Direction direction = Direction.UP;

    public Robot(int x, int y)
    {
        super(x, y, Direction.UP);

        if(!Config.headlessModeEnabled())
        {
            RobotVC robotVC =  new RobotVC(x, y);
            robotVC.playMove();
        }
    }

    public void move()
    {

    }

    public void turnLeft()
    {

    }
}
