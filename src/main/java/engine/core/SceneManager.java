package engine.core;

import java.util.ArrayList;

public class SceneManager
{
    private Scene activeScene = null;
    private final ArrayList<Scene> scenes = new ArrayList<>();

    public void addScene(Scene scene)
    {
        if(scenes.contains(scene))
            throw new IllegalArgumentException("Scene already registered");

        scenes.add(scene);
    }
    public void setActiveScene(int index)
    {
        this.activeScene = scenes.get(index);
    }
    public Scene getActiveScene()
    {
        return activeScene;
    }
}
