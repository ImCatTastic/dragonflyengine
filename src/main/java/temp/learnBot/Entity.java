package temp.learnBot;

import engine.core.GameObject;
import engine.util.math.Vec2;
import temp.learnBot.gameobjects.WorldConfig;
import temp.learnBot.item.Item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Entity<T extends GameObject>
{
    protected final HashMap<String, Tag> tags = new HashMap<>();
    protected final LinkedList<Item> items = new LinkedList<>();
    protected int x;
    protected int y;
    protected Direction direction;
    protected final T gameObject;
    public Entity(int x, int y, Direction direction)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;

        World.getInstance().addEntity(this);
        if(!FopConfig.enableGUI)
        {
            this.gameObject = createGameObject(WorldManager.getInstance().convertCoords(x, y));
            WorldManager.getInstance().addObject(gameObject);
        }
        else gameObject = null;

        TasqueManager.register(this);
    }
    public Entity(Entity<T> entity)
    {
        this.x = entity.x;
        this.y = entity.y;
        this.direction = entity.direction;
        this.gameObject = entity.gameObject;
        this.tags.putAll(entity.tags);
        this.items.addAll(entity.items);
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public Direction getDirection()
    {
        return direction;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }
    protected void addTag(Tag tag)
    {
        if(!tags.containsKey(tag.name))
            tags.put(tag.name, tag);
    }
    public void addItem(Item item)
    {
        items.add(item);
    }

    protected void destroy()
    {
        World.getInstance().removeEntity(this);

        if(!WorldConfig.headlessModeEnabled())
        {
            //TODO: remove object from rendered world
        }

        TasqueManager.remove(this);
    }
    protected <I extends Item> I getFirstItemByType(Class<I> type)
    {
        return items.stream()
                .filter(item -> item.getClass().equals(type))
                .map(type::cast)
                .findFirst()
                .orElse(null);
    }
    protected <I extends Item> List<I> getAllItemsByType(Class<I> type)
    {
        return items.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }
    protected <I extends Item> void removeItemsByType(Class<I> type, int amountToRemove)
    {
        var list = getAllItemsByType(type);
        Iterator<I> iterator = list.iterator();

        while (iterator.hasNext() && list.size() > amountToRemove--)
        {
            iterator.next();
            iterator.remove();
        }
    }
    protected abstract T createGameObject(Vec2 position);
    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }
}