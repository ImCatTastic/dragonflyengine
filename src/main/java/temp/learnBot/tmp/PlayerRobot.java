package temp.learnBot.tmp;

import engine.audio.AudioManager;
import temp.learnBot.Direction;
import temp.learnBot.Robot;
import temp.learnBot.RobotFamily;
import temp.learnBot.WorldManager;

public class PlayerRobot extends Robot
{
    public PlayerRobot(int x, int y, Direction direction)
    {
        super(x, y, direction,10, RobotFamily.TRIANGLE_BLUE);
    }

    @Override
    public void collectCoin()
    {
        super.collectCoin();
        //AudioManager.play(WorldManager.COIN_PICKUP_SOUND_ID);
    }

    public void handleKeyInput(final int direction, final boolean shouldPutCoins, final boolean shouldPickCoins) {
        // <solution H1>
        if (shouldPutCoins /*&& !isOnACoin()*/ && hasAnyCoins())
        {
            placeCoin();
        }

        if (shouldPickCoins && isOnCoin() && getNumberOfCoins() < 50)
        {
            collectCoin();
        }

        if (direction >= 0 && direction < 4) {
            // start with direction 0 (UP)
            int dx = 0;
            int dy = 1;
            // rotate direction times by 90 degrees to the right
            for (int i = 0; i < direction; i++) {
                final int tmp = dx;
                dx = dy;
                dy = -tmp;
            }
            while (getDirection().getDx() != dx || getDirection().getDy() != dy) {
                turnLeft();
            }
            if (isFrontClear())
            {
                move();
            }

        }
        // </solution>
    }
}
