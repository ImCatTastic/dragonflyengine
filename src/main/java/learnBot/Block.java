package learnBot;

import learnBot.visuals.BlockVC;
import learnBot.visuals.Config;

class Block extends Entity implements Obstacle
{
    private BlockVC vc;
    public Block(int x, int y)
    {
        super(x, y, Direction.UP);

        if(!Config.headlessModeEnabled())
            new BlockVC(x, y);
    }

    @Override
    public boolean isBlockingPath(Entity entity, boolean teleport)
    {
        return true;
    }

    @Override
    public void collide(Entity entity, double speed)
    {
        vc.playCrash();
    }
}
