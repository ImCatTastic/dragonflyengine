package learnBot;

import learnBot.visuals.CoinVC;
import learnBot.visuals.Config;

class Coin extends Entity implements Collectible
{
    public Coin(int x, int y)
    {
        super(x, y, Direction.UP);

        if(!Config.headlessModeEnabled())
            visualComponent =  new CoinVC(x, y);
    }

    @Override
    public void onCollect()
    {
        if(visualComponent != null)
            visualComponent.hide();
    }
    @Override
    public void onPlace()
    {
        if(visualComponent != null)
            visualComponent.reveal();
    }
}
