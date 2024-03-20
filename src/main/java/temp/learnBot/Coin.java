package temp.learnBot;

import engine.util.math.Vec2;
import javafx.application.Platform;
import temp.learnBot.visual.CoinGameobject;
import temp.learnBot.item.CoinItem;
import temp.learnBot.item.Item;

public class Coin extends Entity<CoinGameobject>
{
    private int coinCount = 0;
    public Coin(int x, int y, CoinItem coin)
    {
        super(x, y, Direction.UP);
        addItem(coin);
    }
    public int getCount()
    {
        return coinCount;
    }
    public CoinItem collectCoin()
    {
        if(items.isEmpty())
            throw new IllegalStateException("unexpected error: no coins to collect?");

        coinCount--;
        gameObject.updateCount(coinCount);

        var coin = (CoinItem) items.pop();
        if(items.isEmpty())
        {
            Platform.runLater(gameObject::destroy);
            destroy();
        }

        return coin;
    }
    @Override
    public void addItem(Item item)
    {
        if(!(item instanceof CoinItem))
            throw new IllegalArgumentException("This entity only accepts coin items.");

        coinCount++;
        if(gameObject != null)
            gameObject.updateCount(coinCount);

        super.addItem(item);
    }
    @Override
    protected CoinGameobject createGameObject(Vec2 position)
    {
        return new CoinGameobject(position);
    }
}