package engine.logging.formatting;

import engine.logging.LogMessage;
import engine.util.Tuple;
import engine.util.formatting.Formatter;

import java.util.function.Function;

public class LogFormatter extends Formatter<LogComponent, LogComponentData, LogMessage, Tuple<LogMessage, LogComponentData>>
{
    private final int spacing;
    public LogFormatter(Object... components)
    {
        this(2, components);
    }
    public LogFormatter(int spacing, Object... components)
    {
        super(components);
        this.spacing = spacing;
    }
    @Override
    protected Function<LogComponent, String> mappingFunction(Tuple<LogMessage, LogComponentData> data)
    {
        return x -> x.extract(data) + " ".repeat(spacing);
    }
    @Override
    protected Tuple<Class<LogComponent>, Class<LogComponentData>> getClassTypes()
    {
        return new Tuple<>(LogComponent.class, LogComponentData.class);
    }
    @Override
    protected LogComponentData getDefaultData()
    {
        return new LogComponentData();
    }
    @Override
    protected Tuple<LogMessage, LogComponentData> convertData(LogMessage input, LogComponentData data)
    {
        return new Tuple<>(input, data);
    }
}
