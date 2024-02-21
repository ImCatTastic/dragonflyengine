package temp.learnBot;

import engine.util.math.Vec2;
import temp.learnBot.gameobjects.BlockGameObject;
import temp.learnBot.gameobjects.WorldConfig;

class Block extends Entity<BlockGameObject> implements Obstacle
{
    public Block(int x, int y)
    {
        super(x, y, Direction.UP);

        if(!WorldConfig.headlessModeEnabled())
            new BlockGameObject(x, y);
    }

    @Override
    public boolean isBlockingPath(Entity entity, boolean teleport)
    {
        return true;
    }
    @Override
    public void collide(Entity entity, double speed)
    {
        //vc.playCrash();
    }

    @Override
    protected BlockGameObject createGameObject(Vec2 position)
    {
        return new BlockGameObject(0, 0);
    }
}
