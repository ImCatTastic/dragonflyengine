package temp.learnBot;

import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class Tag
{
    public enum Type
    {
        BOOLEAN(s -> s.equals("true") || s.equals("false")),
        INTEGER(s -> s.matches("-?\\d+")),
        FLOAT(s -> s.matches("-?\\d+(\\.\\d+)?")),
        ANY(s -> true);
        final Predicate<String> predicate;
        Type(Predicate<String> predicate)
        {
            this.predicate = predicate;
        }
    }
    private final Type type;
    public final String name;
    private String value;
    public Tag(@NotNull String name, @NotNull String value, Type type)
    {
        this.name = name;
        this.type = type;
        setValue(value);
    }
    public void setValue(@NotNull String value)
    {
        if(!type.predicate.test(value))
            throw new IllegalArgumentException("Illegal input for type: " + type);

        this.value = value;
    }
    public String getValue()
    {
        return value;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Tag otherTag = (Tag) obj;
        return name.equals(otherTag.name);
    }
    @Override
    public int hashCode()
    {
        return name.hashCode();
    }
}
