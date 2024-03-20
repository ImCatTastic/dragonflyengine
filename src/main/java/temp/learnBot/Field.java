package temp.learnBot;


import engine.util.RGBColor;
import temp.learnBot.visual.FieldGameObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public final class Field
{
    private final int x;
    private final int y;
    private RGBColor fieldColor = null;
    private FieldGameObject gameObject;
    private int entityCount = 0;
    @SuppressWarnings("rawtypes") //No fucking clue why using Entity<?> breaks shit but oh well...
    private final HashMap<Class<? extends Entity>, ArrayList<Entity<?>>> entityBuckets = new HashMap<>();
    @SuppressWarnings("rawtypes") //Same fucking problem...
    private final ArrayList<Class<? extends Entity>> obstacleRegisters = new ArrayList<>();
    Field(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    void addEntity(Entity<?> entity)
    {
        registerEntity(entity);
        if(entity instanceof Obstacle && !obstacleRegisters.contains(entity.getClass()))
            obstacleRegisters.add(entity.getClass());

        entityCount++;
    }
    void removeEntity(@NotNull Entity<?> entity)
    {
        entityBuckets.get(entity.getClass()).remove(entity);
        entityCount--;
    }
    private void registerEntity(@NotNull Entity<?> entity)
    {
        @SuppressWarnings("rawtypes")
        Class<? extends Entity> type = entity.getClass();
        if(!entityBuckets.containsKey(type))
            entityBuckets.put(entity.getClass(), new ArrayList<>());

        entityBuckets.get(type).add(entity);
    }
    public void setFieldColor(final @Nullable RGBColor fieldColor)
    {
        this.fieldColor = fieldColor;

        if(gameObject != null)
            System.out.println("TODO");
    }
    public @Nullable RGBColor getFieldColor()
    {
        return fieldColor;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    @SuppressWarnings("rawtypes")
    Obstacle obstacleBlockingPath(@NotNull Entity<?> entity, boolean teleport)
    {
        for (Class<? extends Entity> obstacleRegister : obstacleRegisters)
            for (Entity<?> e : entityBuckets.get(obstacleRegister))
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
        if(!entityBuckets.containsKey(type))
            return null;

        return (List<T>) Collections.unmodifiableList(entityBuckets.get(type));
    }
    public List<Entity<?>> getEntities()
    {
        return entityBuckets.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }
    public int getEntityCount()
    {
        return entityCount;
    }
    public <T extends Entity<?>> int getEntityCountByType(Class<T> type)
    {
        if(entityBuckets.containsKey(type))
            return entityBuckets.get(type).size();

        return 0;
    }
}
