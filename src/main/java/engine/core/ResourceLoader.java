package engine.core;

import java.io.InputStream;

public class ResourceLoader
{
    //private final static Namespace NAMESPACE = new Namespace("resource_loader");
    //private final static String TEXTURE_PATH = "textures";

    private static boolean complete = false;
    public static void load()
    {
        if(complete)
            return;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        loadTextures(classLoader);

        complete = true;
    }

    private static void loadTextures(ClassLoader loader)
    {
        InputStream inputStream = loader.getResourceAsStream("sprites/pickachu.jpg");
    }
    public static boolean isComplete()
    {
        return complete;
    }
}
