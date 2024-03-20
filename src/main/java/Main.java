import engine.identification.Identifier;
import engine.logging.Log;
import engine.logging.LogPriority;
import temp.learnBot.*;
import temp.learnBot.tmp.*;

public class Main
{
    public static void main(String[] args)
    {
        World.create(10, 10, Main::entry);
    }

    static Contaminant contaminant1;
    static Contaminant contaminant2;
    static boolean over = false;
    public static void entry()
    {
        Temp.player = new PlayerRobot(0, 0, Direction.UP);

        contaminant1 = new Contaminant1(World.getWidth() - 1, 0, Direction.UP, 400, RobotFamily.SQUARE_ORANGE);
        contaminant2 = new Contaminant2(World.getWidth() - 1, World.getHeight() - 1, Direction.UP, 400, RobotFamily.SQUARE_AQUA);

        UserConfig.sequentialScheduling = false;

        contaminant2.setSpeed(0.8);
        Temp.player.setSpeed(4);

        //dentifier log_id = new Identifier("LOG");
        //og.debug(log_id, "hello world");

        MazeGenerator.generateMaze();

        World.placeBlock(1,1);


        while (!over)
        {
            //TasqueManager.submitIfAvailable(contaminant1, contaminant1::doMove);
            //TasqueManager.submitIfAvailable(contaminant2, contaminant2::doMove);
            //checkWinCondition();

            try
            {
                Thread.sleep(1);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }

            ///TasqueManager.submitIfAvailable(robot3, () -> func(robot3, true));
        }
    }

    public static void checkWinCondition()
    {
        // <solution H3>
        // If all Offenders are turned off, the game is won
        if (contaminant1.isTurnedOff() && contaminant2.isTurnedOff()) {
            System.out.println("Cleaning robot won!");
            //stopGame();
            over = true;
            return;
        }
        // if more than 50% of all fields are dirty, the game is lost
        int dirtyFields = 0;
        for (int x = 0; x < World.getWidth(); x++) {
            for (int y = 0; y < World.getHeight(); y++) {
                if (Utils.getCoinAmount(x, y) > 0) {
                    dirtyFields++;
                }
            }
        }

        var coins = Utils.getCoinAmount(0, World.getHeight() - 1);
        WorldManager.progressA = coins / 50d;

        if (WorldManager.progressA >= 1)
        {
            contaminant1.turnOff();
            contaminant2.turnOff();
            System.out.println("Cleaning robot won!");
            //stopGame();
            WorldManager.displayWinner();
            over = true;
            return;
        }

        double progress = (double) dirtyFields / (World.getWidth() * World.getHeight());
        WorldManager.progressB = progress * 2;

        if (progress >= 0.5)
        {
            //getCleaningRobot().turnOff();
            System.out.println("Contaminants won!");
            WorldManager.displayLooser();
            over = true;
            //stopGame();
        }
        // </solution H3>
    }
}