package engine.core;

import engine.buffering.Matrix4fBuffer;
import engine.rendering.SpriteRenderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Scene
{
    private final NodeTree<GameObject> objectTree = new NodeTree<>(new GameObject());
    private final int zLayerLimit;
    private final ArrayList<ArrayList<SpriteRenderer>> zLayers = new ArrayList<>();
    private final RenderCollection renderCollection = new RenderCollection();
    public Scene(int zLayerLimit)
    {
        this.zLayerLimit = zLayerLimit;
    }
    public void addSpriteRenderer(SpriteRenderer renderer, int zIndex)
    {
        if (zIndex > zLayerLimit)
            throw new IllegalArgumentException("z-index out of bounds");
        if(zLayers.get(zIndex) == null)
            zLayers.set(zIndex, new ArrayList<>());
        zLayers.get(zIndex).add(renderer);
    }
    public void addGameObject(@NotNull GameObject gameObject)
    {
        objectTree.getByIndex(0).addChild(gameObject);
        registerObject(gameObject);
    }
    public void addGameObject(@NotNull GameObject gameObject, @Nullable GameObject parent)
    {
        if(parent != null)
            objectTree.getByElement(parent).addChild(gameObject);
        else
            objectTree.getByIndex(0).addChild(gameObject);
        registerObject(gameObject);
    }
    private void registerObject(GameObject gameObject)
    {
        gameObject.setHost(this);
        var renderer = gameObject.getSpriteRenderer();
        if(renderer != null)
            renderCollection.addElement(gameObject);
    }
    public NodeTree<GameObject> getObjectTree()
    {
        return objectTree;
    }

    public void update()
    {
        objectTree.forEach(object ->
        {
            byte flags = object.getDirtyFlags();
            if((flags & GameObject.FLAG_RENDERER) != 0)
            {
                flags &= ~GameObject.FLAG_RENDERER;
                renderCollection.addElement(object);
            }
        });
    }
}
