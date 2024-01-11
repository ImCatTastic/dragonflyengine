package learnBot;

import learnBot.visuals.Config;
import learnBot.visuals.WallVC;

class Wall extends Entity implements Obstacle
{
    public Wall(int x, int y, boolean horizontal)
    {
        super(x, y, horizontal ? Direction.UP : Direction.RIGHT);

        int counterWallX = !horizontal && x + 1 < World.getWidth() - 1 ? x + 1 : 0;
        int counterWallY = horizontal && y + 1 < World.getHeight() - 1 ? y + 1 : 0;

        if(!Config.headlessModeEnabled())
            new WallVC(x, y, horizontal ? Direction.UP : Direction.RIGHT);

        new Wall(counterWallX, counterWallY, getDirection().getOpposite());
    }

    private Wall(int x, int y, Direction direction)
    {
        super(x, y, direction);
    }

    @Override
    public boolean isBlockingPath(Entity entity, boolean teleport)
    {
        if(teleport)
            return false;

        return entity.getDirection().ordinal() == (getDirection().ordinal() + 2) % Direction.values().length;
    }
    @Override
    public void collide(Entity entity, double speed)
    {

    }
}
