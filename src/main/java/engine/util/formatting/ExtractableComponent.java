package engine.util.formatting;

public interface ExtractableComponent<T>
{
    String extract(T data);
}
