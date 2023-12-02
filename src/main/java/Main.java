import engine.BoundingBox2D;
import engine.collider.BoxCollider2D;
import engine.collider.Collider2D;
import learnBot.Robot;
import learnBot.World;

public class Main
{
    public static void main(String[] args)
    {
        World.speed = 2;
        World.createWorld(8, 8, Main::entry);
    }

    public static void entry()
    {
        Robot robot = new Robot(2,4);
        robot.setSpeed(0.5);

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

                //robot.move();
            }
        }

    }

    private static boolean checkCollision(Collider2D collider, Collider2D collider2)
    {
        if(collider instanceof BoxCollider2D boxCollider && collider2 instanceof BoxCollider2D boxCollider2)
        {
            BoundingBox2D bBox = boxCollider.getBoundingBox();
            BoundingBox2D bBox2 = boxCollider2.getBoundingBox();

            boolean horizontalCollision = (bBox2.x1 >= bBox.x1 && bBox2.x1 <= bBox.x2) || (bBox2.x2 >= bBox.x1 && bBox2.x2 <= bBox.x2);
            boolean verticalCollision = (bBox2.y1 >= bBox.y1 && bBox2.y1 <= bBox.y2) || (bBox2.y2 >= bBox.y1 && bBox2.y2 <= bBox.y2);
            return horizontalCollision && verticalCollision;
        }

        return false;
    }
}