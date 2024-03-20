package temp.learnBot.tmp;

import temp.learnBot.Direction;
import temp.learnBot.RobotFamily;

public class Contaminant2 extends Contaminant
{
    public Contaminant2(int x, int y, Direction direction, int numberOfCoins, RobotFamily robotFamily)
    {
        super(x, y, direction, numberOfCoins, robotFamily);
    }
    @Override
    public void doMove()
    {
        if (getNumberOfCoins() == 0) {
            turnOff();
            return;
        }
        if (isTurnedOff()) {
            return;
        }
        // lay 2 coins
        if (!isOnCoin() || Utils.getCoinAmount(getX(), getY()) < 2) {
            for (int i = 0; i < 2; i++) {
                if (!hasAnyCoins() || Utils.getCoinAmount(getX(), getY()) >= 2) {
                    break;
                }
                placeCoin();
            }
        }

        // get valid paths
        Direction left = null;
        Direction back = null;
        Direction right = null;
        Direction front = null;
        int validPathsCount = 0;
        for (int i = 0; i < 4; i++)
        {
            turnLeft();

            if (isFrontClear())
            {
                if (i == 0)
                    left = getDirection();
                else if (i == 1)
                    back = getDirection();
                else if (i == 2)
                    right = getDirection();
                else
                    front = getDirection();

                validPathsCount++;
            }
        }
        // check if there are any valid paths
        if (validPathsCount == 0)
            return;

        // orient on left wall
        Direction direction = null;
        if (left != null)
            direction = left;
        else if (front != null)
            direction = front;
        else if (right != null)
            direction = right;
        else if (back != null)
            direction = back;

        while (getDirection() != direction)
            turnLeft();

        move();
    }
}
