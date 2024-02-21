package engine.core;

import engine.animation.AnimationManager;
import engine.rendering.Renderer;
import engine.logging.Log;
import javafx.animation.AnimationTimer;

public class GameLoop
{
    private GameLoop() {}
    private static int nextPauseState = 0;
    private static boolean paused = false;
    private static boolean running = false;
    public static void start()
    {
        if(running)
            return;

        running = true;

        var manager = Engine.getGameManager();
        if(SceneManager.getActiveScene() == null)
        {
            throw new IllegalStateException("Cannot start renderer without scene.");
        }
        new AnimationTimer()
        {
            private long last = System.nanoTime();
            @Override
            public void handle(long now)
            {
                var deltaTime = (now - last) / 1e9;

                last = now;
                var scene = SceneManager.getActiveScene();
                if(scene == null)
                    return;

                if(nextPauseState == 1)
                {
                    paused = true;
                    nextPauseState = 0;
                }
                else if(nextPauseState == -1)
                {
                    paused = false;
                    nextPauseState = 0;
                }

                if(!paused)
                {
                    manager.update();

                    for (Transform2D transform2D : SceneManager.getActiveScene())
                    {
                        transform2D.getGameObject().performUpdate();
                    }

                    AnimationManager.update(deltaTime);
                    Renderer.draw();
                }

                Log.update();
                Input.clear();
            }
        }.start();
    }
    public static void setPaused(boolean paused)
    {
        nextPauseState = paused ? 1 : -1;
    }
}
