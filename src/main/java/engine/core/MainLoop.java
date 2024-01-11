package engine.core;

import engine.logging.LogHandler;
import engine.util.Time;
import engine.util.Updatable;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainLoop
{
    public double targetUPS = 144;
    public double frameTime = 1000d / targetUPS;
    private CopyOnWriteArrayList<Updatable> updatableObjects = new CopyOnWriteArrayList<>();
    private boolean stopped = false;
    private boolean paused = false;
    private boolean running = false;


    public MainLoop()
    {

    }

    public void setScene(Scene scene)
    {
        //TODO: foreach updatdable object, collect
    }

    public void start(@NotNull final GameManager manager, @NotNull final AnimationHandler animationHandler)
    {
        if(running)
            return;

        running = true;

        new Thread(() ->
        {
            manager.onStart();

            long lastTime = System.currentTimeMillis();

            while (!stopped)
            {
                long now = System.currentTimeMillis();
                long elapsedTime = now - lastTime;

                if (elapsedTime >= frameTime)
                {
                    Time.millisDeltaTime = (int) elapsedTime;
                    Time.deltaTime = elapsedTime * 0.001d;

                    animationHandler.update();
                    if(!paused)
                    {
                        manager.onUpdate();
                        for (Updatable updatableObject : updatableObjects)
                            updatableObject.update();
                    }

                    lastTime = now;
                }

                Time.pause(1);
            }
        }).start();

        running = false;
    }

    public void updateUPS()
    {

    }

    public void stop()
    {
        stopped = true;
    }
    public void setPaused(boolean paused)
    {
        this.paused = paused;
    }
}
