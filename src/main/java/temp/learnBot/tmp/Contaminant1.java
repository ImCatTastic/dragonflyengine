package temp.learnBot.tmp;

import temp.learnBot.Direction;
import temp.learnBot.RobotFamily;

public class Contaminant1 extends Contaminant
{
    public Contaminant1(int x, int y, Direction direction, int numberOfCoins, RobotFamily robotFamily)
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
        // lay random amount of coins between 1 and 5
        if (!isOnCoin() || Utils.getCoinAmount(getX(), getY()) < 20) {
            final int amount = Utils.getRandomInteger(1, 5);

            for (int i = 0; i < amount; i++)
            {
                if (!hasAnyCoins() || Utils.getCoinAmount(getX(), getY()) >= 20)
                    break;

                placeCoin();
            }
        }

        // get valid paths
        Direction path0 = null;
        Direction path1 = null;
        Direction path2 = null;
        Direction path3 = null;
        int validPathsCount = 0;

        for (int i = 0; i < 4; i++) {
            turnLeft();
            if (isFrontClear()) {
                validPathsCount++;
                if (path0 == null) {
                    path0 = getDirection();
                } else if (path1 == null) {
                    path1 = getDirection();
                } else if (path2 == null) {
                    path2 = getDirection();
                } else {
                    path3 = getDirection();
                }
            }
        }
        // get random path
        if (path0 == null && path1 == null && path2 == null && path3 == null)
        {
            return;
        }

        final int randomPathIndex = Utils.getRandomInteger(0, validPathsCount - 1);
        Direction path = null;
        if (randomPathIndex == 0) {
            path = path0;
        } else if (randomPathIndex == 1) {
            path = path1;
        } else if (randomPathIndex == 2) {
            path = path2;
        } else {
            path = path3;
        }
        while (getDirection() != path) {
            turnLeft();
        }
        move();
    }
}
