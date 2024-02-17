package engine.javafx;

import engine.identification.IDRegistry;
import engine.identification.Identifier;

import java.util.ArrayList;
import java.util.HashMap;

public class SceneManager
{
    private static GameScene activeScene;
    private final static ArrayList<GameScene> scenes = new ArrayList<>();
    static void addScene(GameScene scene)
    {
        scenes.add(scene);
        if(activeScene == null)
            activeScene = scene;
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