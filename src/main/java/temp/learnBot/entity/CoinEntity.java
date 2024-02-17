package temp.learnBot.entity;

import engine.javafx.SceneManager;
import engine.mathUtil.Vec2;
import javafx.application.Platform;
import temp.learnBot.Direction;
import temp.learnBot.Entity;
import temp.learnBot.gameobjects.CoinGameobject;
import temp.learnBot.item.CoinItem;
import temp.learnBot.item.Item;

public class CoinEntity extends Entity<CoinGameobject>
{
    int coinCount = 0;
    public CoinEntity(int x, int y, CoinItem coin)
    {
        super(x, y, Direction.UP);
        addItem(coin);
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
        gameObject.updateCount(coinCount);
        super.addItem(item);
    }
    @Override
    protected CoinGameobject createGameObject(Vec2 position)
    {
        return new CoinGameobject(position);
    }
}