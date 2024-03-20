package temp.learnBot.tmp;

import temp.learnBot.Direction;
import temp.learnBot.Robot;
import temp.learnBot.RobotFamily;

public abstract class Contaminant extends Robot
{
    public Contaminant(int x, int y, Direction direction, int numberOfCoins, RobotFamily robotFamily)
    {
        super(x, y, direction, numberOfCoins, robotFamily);
    }
    public abstract void doMove();
}
