package learnBot;

import engine.collider.BoxCollider2D;

public class Robot extends FOPEntity
{
    public Robot(double x, double y)
    {
        super(x, y, 0);
        addCollider(new BoxCollider2D(0, 0, 50, 50));
    }

    public void move()
    {

        //this.incX(2 * fac);
    }
}
