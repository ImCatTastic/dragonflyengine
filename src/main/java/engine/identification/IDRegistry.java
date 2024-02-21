package engine.identification;

import java.util.*;

public class IDRegistry
{
    public static final IDRegistry SPRITE = new IDRegistry();
    public static final IDRegistry LOGGER = new IDRegistry();
    public static final IDRegistry GAMEOBJECT = new IDRegistry();
    public static final IDRegistry SCENE = new IDRegistry();
    private final HashSet<Identifier> identifiers = new HashSet<>();
    private final static List<IDRegistry> registries = new ArrayList<>(List.of(SPRITE, LOGGER));
    private IDRegistry() {}
    public static void register(IDRegistry registry, Identifier identifier)
    {
        if(registries.stream().anyMatch(r -> r.identifiers.contains(identifier)))
            throw new IllegalArgumentException("Identifier: " + identifier + " already registered.");
        registry.identifiers.add(identifier);
    }
    public Set<Identifier> getIdentifiers()
    {
        return Collections.unmodifiableSet(identifiers);
    }
}
