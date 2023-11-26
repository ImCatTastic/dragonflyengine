package engine;

public abstract class UpdateableGameObject extends GameObject
{
    public UpdateableGameObject(double x, double y, double rotation)
    {
        super(x, y, rotation);
    }
    public UpdateableGameObject(double x, double y)
    {
        super(x, y);
    }

    public abstract void update();
}
