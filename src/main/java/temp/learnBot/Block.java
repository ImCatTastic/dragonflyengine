package temp.learnBot;

import engine.util.math.Vec2;
import temp.learnBot.visual.BlockGameObject;

public class Block extends Entity<BlockGameObject> implements Obstacle
{
    Block(int x, int y)
    {
        super(x, y, Direction.UP);
    }
    @Override
    public boolean isBlockingPath(Entity<?> entity, boolean teleport)
    {
        return true;
    }
    @Override
    public void collide(Entity<?> entity, double speed)
    {
        //vc.playCrash();
    }
    @Override
    public void detect(Entity<?> entity)
    {
        if(gameObject != null)
        {
            gameObject.playDetectedAnimation();
        }
    }
    @Override
    protected void gameOver()
    {
        if(gameObject != null)
        {
            gameObject.playGameOverAnimation();
        }
    }
    @Override
    protected BlockGameObject createGameObject(Vec2 position)
    {
        return new BlockGameObject(this);
    }
}