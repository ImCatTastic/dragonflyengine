package temp.learnBot;


import javafx.scene.paint.Color;
import temp.learnBot.gameobjects.FieldGameObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public final class Field
{
    private final int x;
    private final int y;
    private Color fieldColor = null;
    private FieldGameObject gameObject;
    private int entityCount = 0;
    private final HashMap<Class<? extends Entity>, ArrayList<Entity<?>>> entityBuckets = new HashMap<>();
    private final ArrayList<Class<? extends Entity>> obstacleRegisters = new ArrayList<>();
    Field(int x, int y)
    {
        this.x = x;
        this.y = y;

        //vc = new FieldVC(x + Config.BORDER_SIZE_FACTOR * (x + 1), y + Config.BORDER_SIZE_FACTOR * (y + 1));

        //if(!WorldConfig.headlessModeEnabled()) TODO: Enable
            //vc = new FieldVC(x, y);
    }
    void addEntity(Entity entity)
    {
        registerEntity(entity);

        if(entity instanceof Obstacle && !obstacleRegisters.contains(entity.getClass()))
            obstacleRegisters.add(entity.getClass());

        entityCount++;
    }
    @NotNull
    Entity<?> removeEntity(@NotNull Entity<?> entity)
    {
        entityBuckets.get(entity.getClass()).remove(entity);
        entityCount--;

        return entity;
    }
    private void registerEntity(@NotNull Entity entity)
    {
        Class<? extends Entity> type = entity.getClass();
        if(!entityBuckets.containsKey(type))
            entityBuckets.put(type, new ArrayList<>());

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

    <T extends Entity<?>> boolean hasEntityOfType(Class<T> type)
    {
        return entityBuckets.containsKey(type) && !entityBuckets.get(type).isEmpty();
    }

    @SuppressWarnings("unchecked")
    <T extends Entity<?>> List<T> getEntitiesByType(Class<T> type)
    {
        return (List<T>) Collections.unmodifiableList(entityBuckets.get(type));
    }

    public List<Entity> getEntities()
    {
        return entityBuckets.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public void setFieldColor(final @Nullable Color fieldColor)
    {
        this.fieldColor = fieldColor;

        if(gameObject != null)
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
}
