package engine.logging.formatting;

import engine.util.Tuple;
import engine.util.formatting.ExtractableComponent;
import engine.util.formatting.Formatter;

import java.time.LocalTime;
import java.util.function.Function;

public class TimeFormatter extends Formatter<TimeFormatter.Component, Character, LocalTime, Tuple<LocalTime, Character>>
{
    public TimeFormatter(Object... components)
    {
        super(components);
    }
    @Override
    protected Tuple<Class<Component>, Class<Character>> getClassTypes()
    {
        return new Tuple<>(Component.class, Character.class);
    }
    @Override
    protected Character getDefaultData()
    {
        return ':';
    }
    @Override
    protected Tuple<LocalTime, Character> convertData(LocalTime input, Character data)
    {
        return new Tuple<>(input, data);
    }
    public enum Component implements ExtractableComponent<Tuple<LocalTime, Character>>
    {
        MILLISECOND(x -> String.valueOf(x.getNano() / 1000000d)),
        SECOND(x -> String.valueOf(x.getSecond())),
        MINUTE(x -> String.valueOf(x.getMinute())),
        HOUR(x -> String.valueOf(x.getHour()));
        private final Function<LocalTime, String> extractor;
        Component(Function<LocalTime, String> extractor)
        {
            this.extractor = extractor;
        }
        @Override
        public String extract(Tuple<LocalTime, Character> data)
        {
            return extractor.apply(data.first).toString() + data.second;
        }
    }
}
