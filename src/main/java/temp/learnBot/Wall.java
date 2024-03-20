package temp.learnBot;

import engine.util.math.Vec2;
import temp.learnBot.visual.WallGameObject;

public class Wall extends Entity<WallGameObject> implements Obstacle
{
    private final boolean isCounterWall;
    Wall(int x, int y, boolean horizontal)
    {
        super(x, y, horizontal ? Direction.UP : Direction.RIGHT);
        int counterWallX = !horizontal ? x + 1 : x;
        int counterWallY = horizontal ? y + 1 : y;
        new Wall(counterWallX, counterWallY, getDirection().getOpposite(), gameObject);
        isCounterWall = false;
    }
    Wall(int x, int y, Direction direction)
    {
        super(x, y, direction, true);
        isCounterWall = false;
    }
    private Wall(int x, int y, Direction direction, WallGameObject gameObject)
    {
        super(x, y, direction, gameObject);
        isCounterWall = true;
    }
    @Override
    public boolean isBlockingPath(Entity<?> entity, boolean teleport)
    {
        if(teleport)
            return false;

        return entity.getDirection().ordinal() == (getDirection().ordinal() + 2) % Direction.values().length;
    }
    @Override
    public void detect(Entity<?> entity)
    {
        if(gameObject == null)
        {
            int i = 0;
        }

        if(gameObject != null)
        {
            gameObject.playDetectedAnimation();
        }
    }
    @Override
    protected void gameOver()
    {
        if(gameObject != null && !isCounterWall)
        {
            gameObject.playGameOverAnimation();
        }
    }
    @Override
    public void collide(Entity<?> entity, double speed)
    {

    }
    @Override
    protected WallGameObject createGameObject(Vec2 position)
    {
        return new WallGameObject(this);
    }
}
