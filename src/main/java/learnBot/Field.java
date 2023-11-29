package learnBot;


import javafx.scene.paint.Color;
import learnBot.visualComponent.BoardVC;
import learnBot.visualComponent.FieldVC;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Field
{
    private int x;
    private int y;
    private Color fieldColor = null;
    private FieldVC vc;
    private int entityCount = 0;
    private final HashMap<Class<? extends Entity>, HashSet<Entity>> entityBuckets = new HashMap<>();
    private final ArrayList<Class<? extends Entity>> obstacleRegisters = new ArrayList<>();
    private final LinkedList<Collectible> collectables = new LinkedList<>();
    private final LinkedList<Consumable> consumables = new LinkedList<>();
    protected Field(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    protected Field(int x, int y, @NotNull BoardVC board)
    {
        vc = new FieldVC(x + Config.BORDER_SIZE_FACTOR * (x + 1), y + Config.BORDER_SIZE_FACTOR * (y + 1));
        if(!Config.headlessModeEnabled())
            board.addChild(vc);
    }
    protected void addEntity(Entity entity)
    {
        registerEntity(entity);

        if(entity instanceof Obstacle && !obstacleRegisters.contains(entity.getClass()))
            obstacleRegisters.add(entity.getClass());

        if(entity instanceof Collectible collectible)
        {
            collectible.onPlace();
            collectables.add(collectible);
        }

        if(entity instanceof Consumable consumable)
            consumables.add(consumable);

        entityCount++;
    }
    protected @NotNull Entity removeEntity(@NotNull Entity entity)
    {
        entityBuckets.get(entity.getClass()).remove(entity);

        if(entity instanceof Collectible collectible)
        {
            collectible.onCollect();
            collectables.remove(collectible);
        }

        if(entity instanceof Consumable consumable)
            consumables.remove(consumable);

        entityCount--;

        return entity;
    }
    private void registerEntity(@NotNull Entity entity)
    {
        Class<? extends Entity> type = entity.getClass();
        if(!entityBuckets.containsKey(type))
            entityBuckets.put(type, new HashSet<>());

        entityBuckets.get(type).add(entity);
    }


    protected Obstacle obstacleBlockingPath(@NotNull Entity entity, boolean teleport)
    {
        for (Class<? extends Entity> obstacleRegister : obstacleRegisters)
            for (Entity e : entityBuckets.get(obstacleRegister))
            {
                Obstacle obstacle = (Obstacle) e;
                if(obstacle.isBlockingPath(entity, teleport))
                    return obstacle;
            }

        return null;
    }

    public List<Entity> getEntities()
    {
        return entityBuckets.values().stream().flatMap(Set::stream).collect(Collectors.toList());
    }

    public void setFieldColor(final @Nullable Color fieldColor)
    {
        this.fieldColor = fieldColor;

        if(vc != null)
            System.out.println("TODO");
    }
    public @Nullable Color getFieldColor()
    {
        return fieldColor;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }















    protected int getEntityCount()
    {
        return entityCount;
    }

    protected int getEntityCount(Class<? extends Entity> type)
    {
        if(entityBuckets.containsKey(type))
            return entityBuckets.get(type).size();

        return 0;
    }

    //#region consumable and collectible
    protected boolean hasAnyConsumable()
    {
        return consumables.isEmpty();
    }
    protected boolean hasConsumable(Class<? extends Entity> type)
    {
        return consumables.stream().anyMatch(consumable -> consumable.getClass() == type);
    }
    protected boolean hasConsumable(ArrayList<Class<? extends Entity>> whiteList)
    {
        return consumables.stream().anyMatch(consumable -> whiteList.contains(consumable.getClass()));
    }
    protected boolean hasAnyCollectible()
    {
        return collectables.isEmpty();
    }
    protected boolean hasCollectible(Class<? extends Entity> type)
    {
        return collectables.stream().anyMatch(collectible -> collectible.getClass() == type);
    }
    protected boolean hasCollectible(ArrayList<Class<? extends Entity>> whiteList)
    {
        return collectables.stream().anyMatch(collectible -> whiteList.contains(collectible.getClass()));
    }
    protected Consumable consume()
    {
        if(!consumables.isEmpty())
            return (Consumable) removeEntity((Entity) consumables.get(0));

        return null;
    }
    protected Consumable consume(Class<? extends Entity> type)
    {
        for (Consumable consumable : consumables)
            if(consumable.getClass() == type)
                return consumable;

        return null;
    }
    protected Consumable consume(ArrayList<Class<? extends Entity>> whiteList)
    {
        for (Consumable consumable : consumables)
            if(whiteList.contains(consumable.getClass()))
                return (Consumable) removeEntity((Entity) consumable);

        return null;
    }
    protected Collectible collect()
    {
        if(!collectables.isEmpty())
            return (Collectible) removeEntity((Entity) collectables.get(0));

        return null;
    }
    protected Collectible collect(Class<? extends Entity> type)
    {
        for (Collectible collectible : collectables)
            if(collectible.getClass() == type)
                return (Collectible) removeEntity((Entity) collectible);

        return null;
    }
    protected @Nullable Collectible collect(@NotNull ArrayList<Class<? extends Entity>> whiteList)
    {
        for (Collectible collectible : collectables)
            if(whiteList.contains(collectible.getClass()))
                return (Collectible) removeEntity((Entity) collectible);

        return null;
    }
    //#endregion
}
