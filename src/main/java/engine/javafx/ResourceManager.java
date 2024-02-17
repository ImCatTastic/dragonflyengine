package engine.javafx;

import engine.identification.Identifier;
import engine.identification.IDRegistry;
import javafx.scene.image.Image;

import java.util.HashMap;

public class ResourceManager
{
    private final static HashMap<Identifier, Image> sprites = new HashMap<>();
    public static void loadResources()
    {
        var ids= IDRegistry.SPRITE.getIdentifiers();
        ids.forEach(id ->
        {
            var path = "sprites/" + id.getName();
            if(validatePath(path))
                sprites.put(id, new Image(path));
            else
            {
                sprites.put(id, null);
                //TODO: log failed resource
            }
        });
    }
    private static boolean validatePath(String path)
    {
        return Thread.currentThread().getContextClassLoader().getResource(path) != null;
    }
    public static Image getSpriteById(Identifier identifier)
    {
        if(!sprites.containsKey(identifier))
            throw new IllegalArgumentException("no resource associated with identifier.");

        return sprites.getOrDefault(identifier, null);
    }
}
