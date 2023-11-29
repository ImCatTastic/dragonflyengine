package learnBot;

public interface Obstacle
{
    boolean isBlockingPath(Entity entity, boolean teleport);
    void collide(Entity entity, double speed);
}
