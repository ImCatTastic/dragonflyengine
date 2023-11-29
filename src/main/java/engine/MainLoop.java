package engine;

import engine.util.Time;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CopyOnWriteArrayList;

import static engine.Engine.frameTime;

public class MainLoop
{
    private CopyOnWriteArrayList<UpdateableGameObject> objects = new CopyOnWriteArrayList<>();
    private boolean stopped = false;
    private boolean paused = false;
    private boolean running = false;
    public void addObject(UpdateableGameObject object)
    {
        objects.add(object);
    }
    public void removeObject(UpdateableGameObject object)
    {
        objects.remove(object);
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
                    Time.iDeltaTime = (int) elapsedTime;
                    Time.deltaTime = elapsedTime * 0.001d;

                    animationHandler.update();
                    if(!paused)
                    {
                        manager.onUpdate();
                        for (UpdateableGameObject object : objects)
                            object.update();
                    }

                    lastTime = now;
                }

                Time.pause(1);
            }
        }).start();

        running = false;
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
