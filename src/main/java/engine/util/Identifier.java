package engine.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;

public class Identifier
{
    private final static HashMap<Namespace, HashMap<Integer, String>> identifierRegistry = new HashMap<>();
    private final static HashMap<Namespace, Integer> idCounter = new HashMap<>();
    private final int id;
    private Object holder;
    private final Namespace namespace;
    public Identifier(@NotNull Namespace namespace, @NotNull String name)
    {
        HashMap<Integer, String> entry = identifierRegistry.computeIfAbsent(namespace, k -> new HashMap<>());

        if (entry.containsValue(name))
            throw new IllegalArgumentException("Name already registered for namespace!");

        int newId = idCounter.compute(namespace, (k, v) -> (v == null) ? 0 : v) + 1;
        idCounter.put(namespace, newId);
        entry.put(newId, name);

        this.namespace = namespace;
        this.id = newId;
    }
    public @Nullable String getName()
    {
        return identifierRegistry.getOrDefault(namespace, new HashMap<>()).getOrDefault(id, null);
    }
    public Namespace getNamespace()
    {
        return namespace;
    }
    public int getId()
    {
        return id;
    }

    public void setHolder(Object user)
    {
        if(this.holder != null)
            throw new IllegalArgumentException("User of ID already defined");

        this.holder = user;
    }
    public Object getHolder()
    {
        return holder;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
