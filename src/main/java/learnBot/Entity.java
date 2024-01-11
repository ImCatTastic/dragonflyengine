package learnBot;

import learnBot.visuals.FOPVisualComponent;

public class Entity
{
    protected int x;
    protected int y;
    protected Direction direction;
    protected FOPVisualComponent visualComponent;
    public Entity(int x, int y, Direction direction)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
        World.registerEntity(this);
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
