package engine.util;

import engine.logging.ansi.IntToColor;
import engine.logging.ansi.LogBackgroundColor;
import engine.logging.ansi.LogTextColor;
import engine.logging.ansi.RandomColor;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static engine.logging.ansi.LogTextColor.*;

public class Namespace
{
    private static int namespaceCounter = 0;
    private final static HashMap<Integer, String> registry = new HashMap<>();
    private final int id;
    private final LogTextColor color;
    public Namespace(String name)
    {
        this(name, getColorFromHash(name));
    }
    public Namespace(String name, LogTextColor color)
    {
        if(registry.containsValue(name))
            throw new IllegalArgumentException("Namespace already registered!");

        this.id = namespaceCounter++;
        this.color = color;
        registry.put(id, name);
    }
    public @Nullable String getName()
    {
        return registry.getOrDefault(id, null);
    }
    public int getID()
    {
        return id;
    }
    public LogTextColor getColor()
    {
        return color;
    }
    public static List<String> getNamespaces()
    {
        return registry.values().stream().toList();
    }
    @Override
    public String toString()
    {
        return getName();
    }
    private static LogTextColor getColorFromHash(String name)
    {
        var list = new LogTextColor[]
                {
                        GREEN, YELLOW, BLUE, PURPLE, CYAN, GRAY, BLACK_BRIGHT, BLUE_BRIGHT, GRAY_BRIGHT, PURPLE_BRIGHT
                };
        int absHash = name.hashCode();
        absHash = absHash >= 0 ? absHash : absHash ^ Integer.MIN_VALUE;
        return list[absHash % list.length];
    }
}
