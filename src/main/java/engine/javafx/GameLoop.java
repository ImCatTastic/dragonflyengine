package engine.javafx;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import org.jetbrains.annotations.NotNull;

public class GameLoop
{
    private GameLoop() {}
    private static boolean stopped = false;
    private static boolean paused = false;
    private static boolean running = false;
    private static boolean doResize = false;
    static void requestResize()
    {
        doResize = true;
    }
    public static void start()
    {
        if(running)
            return;

        running = true;

        var manager = Engine.getGameManager();
        manager.start();
        if(SceneManager.getActiveScene() == null)
        {
            throw new IllegalStateException("Cannot start renderer without scene.");
        }
        var renderer = new Renderer();
        new AnimationTimer()
        {
            private long last = System.nanoTime();
            @Override
            public void handle(long now)
            {
                var deltaTime = (now - last) / 1e9;

                if(deltaTime > 0.05)
                    System.out.println(deltaTime);

                last = now;
                var scene = SceneManager.getActiveScene();
                if(scene == null)
                    return;

                if(!paused)
                {
                    manager.update();

                    for (Transform2D transform2D : SceneManager.getActiveScene())
                    {
                        transform2D.getGameObject().performUpdate();
                    }

                    AnimationManager.update(deltaTime);
                    renderer.draw();
                }

                Input.clear();
            }
        }.start();
    }
    public static void stop()
    {
        stopped = true;
    }
    public static void setPaused(boolean paused)
    {
        GameLoop.paused = paused;
    }
}
