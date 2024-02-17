package temp.learnBot;

import java.util.HashMap;

public class TasqueManager
{
    private final static HashMap<Entity<?>, TaskScheduler> schedulers = new HashMap<>();
    public static boolean isEntityBusy(Entity<?> entity)
    {
        return schedulers.get(entity).isRunning();
    }

    /**
     * Submits a block of instructions that are scheduled for execution if the entity isn't executing a task already
     * @param entity the entity to submit instructions to
     * @param instruction instruction to execute
     */
    public static void submitIfAvailable(Entity<?> entity, Runnable instruction)
    {
        if(!isEntityBusy(entity))
        {
            instruction.run();
            schedulers.get(entity).execute();
        }
    }
    static void scheduleTask(Entity<?> entity, Runnable task)
    {
        schedulers.get(entity).schedule(task);
    }
    public static void executeTasks(Entity<?> entity)
    {
        schedulers.get(entity).execute();
    }
    public static void completeTask(Entity<?> entity)
    {
        schedulers.get(entity).notifyCompletion();
    }
    static void register(Entity<?> entity)
    {
        if(!schedulers.containsKey(entity))
            schedulers.put(entity, new TaskScheduler());
    }
    static void remove(Entity<?> entity)
    {
        schedulers.remove(entity);
    }
}