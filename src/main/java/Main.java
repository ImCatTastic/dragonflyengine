import learnBot.Robot;
import learnBot.World;

public class Main
{
    public static void main(String[] args)
    {
        World.createWorld(8, 8, Main::entry);
    }

    public static void entry()
    {
        Robot robot = new Robot(2,4);
        robot.setSpeed(10);

        World.placeCoin(0, World.getHeight() - 1);
        World.placeBlock(World.getWidth() - 1, World.getHeight() - 1);
        World.placeWall(0,0, true);
        World.placeWall(0,0, false);

        final boolean teleport = false;

        for (int i = 0; i < 1000; i++)
        {
            if(teleport)
            {
                int x = (int) (Math.random() * (World.getWidth()));
                int y = (int) (Math.random() * (World.getHeight()));
                robot.teleport(x,y);
            }

            else
            {
                if(robot.isOnCoin())
                    robot.collectCoin();

                if(!robot.isFrontClear())
                    robot.turnLeft();

                robot.move();
            }
        }

    }
}