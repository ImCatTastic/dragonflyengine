package engine.core;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class SceneManager
{
    private static GameScene activeScene;
    private final static ArrayList<GameScene> scenes = new ArrayList<>();
    private static Pane root;
    static void init(Pane root)
    {
        SceneManager.root = root;
    }
    static Pane addScene(GameScene scene)
    {
        scenes.add(scene);
        if(activeScene == null)
            activeScene = scene;
        return root;
    }
    public static GameScene getActiveScene()
    {
        return activeScene;
    }
    public static void setActiveScene(GameScene scene)
    {
        SceneManager.activeScene = scene;
    }
    public static void setActiveScene(int id)
    {
        //TODO: transition animations between scenes.
        SceneManager.activeScene = getScene(id);
    }
    public static GameScene getScene(int id)
    {
        return scenes.get(id);
    }
}