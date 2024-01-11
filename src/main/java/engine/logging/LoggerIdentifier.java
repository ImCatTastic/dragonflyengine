package engine.logging;

import engine.util.Identifier;
import engine.util.Namespace;
import org.jetbrains.annotations.NotNull;

public class LoggerIdentifier extends Identifier
{
    public LoggerIdentifier(@NotNull Namespace namespace, @NotNull String name)
    {
        super(namespace, name);
    }
}
