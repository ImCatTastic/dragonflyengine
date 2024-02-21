package engine.core;

import engine.util.Config;
import engine.util.math.Vec2;

public abstract class GameManager
{
    public abstract void init(Config config);
    public abstract void start();
    public abstract void update();
}