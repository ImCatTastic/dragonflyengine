package engine.util.formatter;

public interface ExtractableComponent<T>
{
    String extract(T data);
}
