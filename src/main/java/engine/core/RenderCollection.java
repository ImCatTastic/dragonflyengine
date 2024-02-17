package engine.core;

import engine.buffering.Matrix4fBuffer;
import engine.geometry.Transform2D;
import engine.rendering.SpriteRenderer;

import java.util.ArrayList;
import java.util.HashSet;

public class RenderCollection
{
    private final HashSet<Integer> registeredObjects = new HashSet<>();
    private final ArrayList<SpriteRenderer> spriteRenderers = new ArrayList<>();
    private final ArrayList<Integer> matrixLocations = new ArrayList<>();
    private final Matrix4fBuffer matrixBuffer = new Matrix4fBuffer(10);
    public void addElement(GameObject object)
    {
        if(registeredObjects.contains(object.getId()))
            return;

        registeredObjects.add(object.getId());
        spriteRenderers.add(object.getSpriteRenderer());
        matrixLocations.add(object.getTransform().setBuffer(matrixBuffer));
    }
}
