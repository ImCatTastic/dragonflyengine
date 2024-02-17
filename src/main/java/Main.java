import engine.javafx.Screen;
import temp.learnBot.*;

import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        World.create(10, 10, Main::entry);
    }

    public static void func(Robot robot, boolean collect)
    {
        while(!robot.isFrontClear())
        {
            robot.turnLeft();
        }

        if(!collect)
        {
            if(robot.hasAnyCoins())
                robot.placeCoin();
        }

        else if(robot.isOnCoin())
        {
            robot.collectCoin();
        }

        robot.move();
    }

    public static void entry()
    {
        final Robot robot = new Robot(5,8, Direction.UP, 400);
        final Robot robot2 = new Robot(5,3, Direction.UP, 0);
        //final Robot robot3 = new Robot(7,3, Direction.RIGHT, 10);
        robot.setSpeed(2);
        robot2.setSpeed(1);
        //robot3.setSpeed(5);

        FopConfig.sequentialScheduling = false;

        int u = 0;
        while (u == 0)
        {
            TasqueManager.submitIfAvailable(robot, () -> func(robot, false));
            TasqueManager.submitIfAvailable(robot2, () -> func(robot2, true));
            ///TasqueManager.submitIfAvailable(robot3, () -> func(robot3, true));
        }

        if(true)
            return;

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