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
        Robot robot = new Robot(3,4);
        robot.setSpeed(0.5);

        for (int i = 0; i < 100; i++)
        {
            robot.move();
            robot.move();
            robot.turnLeft();
        }


        //robot.move();
        //robot.move();
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