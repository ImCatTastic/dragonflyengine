package engine.core;

import engine.rendering.VisualComponent;
import engine.util.Updatable;

class Linker
{
    private Linker() {}

    static Scene currentScene;
    static Renderer2 renderer;
    static MainLoop loop;


    public static void registerGameObject(GameObject gameObject, VisualComponent visualComponent)
    {



        if(gameObject instanceof Updatable ugo)
            System.out.println("ok");

        if(visualComponent != null)
            System.out.println("ok");

        //Renderer.getInstance().addGameObject(gameObject, rootNode);
    }
}
