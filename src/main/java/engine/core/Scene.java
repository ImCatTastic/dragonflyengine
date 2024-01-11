package engine.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Scene
{
    private final NodeTree<GameObject> objectTree = new NodeTree<>(new GameObject());

    public void addGameObject(@NotNull GameObject gameObject, @Nullable GameObject parent)
    {
        if(parent != null)
            objectTree.getByElement(parent).addChild(gameObject);
        else
            objectTree.getByIndex(0).addChild(gameObject);
    }
}
