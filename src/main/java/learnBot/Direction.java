package learnBot;

import engine.mathUtil.IVec2;

public enum Direction
{
    UP(new IVec2(0, 1)),
    RIGHT(new IVec2(1, 0)),
    DOWN(new IVec2(0, -1)),
    LEFT(new IVec2(-1, 0));
    final IVec2 delta;
    Direction(final IVec2 delta)
    {
        this.delta = delta;
    }
    public IVec2 getDelta()
    {
        return delta;
    }
    public int getDx()
    {
        return delta.x;
    }
    public int getDy()
    {
        return delta.y;
    }
    public boolean isHorizontal() {
        return delta.x != 0;
    }
    public boolean isVertical() {
        return delta.x == 0;
    }
}
