package learnBot;

import learnBot.visualComponent.VisualComponent;

public class Entity
{
    private int x;
    private int y;
    private Direction direction;
    public Entity(int x, int y, Direction direction)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public Direction getDirection()
    {
        return direction;
    }
    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
}
