package engine.core;

import engine.mathUtil.Vec2;
import engine.util.Tuple;

import java.util.ArrayList;

public class TextureBaker
{
    private final ArrayList<Tuple<Vec2, Texture2D>> textures;
    public TextureBaker(ArrayList<Tuple<Vec2, Texture2D>> textures)
    {
        this.textures = textures;
    }

    public static Texture2D bake()
    {
        return null;
    }
}
