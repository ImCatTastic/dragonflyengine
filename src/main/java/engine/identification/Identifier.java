package engine.identification;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Identifier
{
    private static int idCount = 0;
    private final int id;
    private final String name;
    public Identifier(@NotNull String name)
    {
        this.id = idCount++;
        this.name = name;
    }
    public @NotNull String getName()
    {
        return name;
    }
    @Override
    public String toString()
    {
        return id + "#" + name;
    }
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof Identifier identifier &&
                identifier.id == id;
    }
}