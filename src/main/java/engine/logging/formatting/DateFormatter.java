package engine.logging.formatting;

import engine.util.Tuple;
import engine.util.formatting.ExtractableComponent;
import engine.util.formatting.Formatter;

import java.time.LocalDate;
import java.util.function.Function;

public class DateFormatter extends Formatter<DateFormatter.Component, Character, LocalDate, Tuple<LocalDate, Character>>
{
    public DateFormatter(Object... components)
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
        return '-';
    }
    @Override
    protected Function<Component, String> mappingFunction(Tuple<LocalDate, Character> data)
    {
        return super.mappingFunction(data);
    }
    @Override
    protected Tuple<LocalDate, Character> convertData(LocalDate input, Character data)
    {
        return new Tuple<>(input, data);
    }
    public enum Component implements ExtractableComponent<Tuple<LocalDate, Character>>
    {
        DAY(LocalDate::getDayOfMonth),
        MONTH(LocalDate::getMonthValue),
        YEAR(LocalDate::getYear);
        private final Function<LocalDate, Integer> extractor;
        Component(Function<LocalDate, Integer> extractor)
        {
            this.extractor = extractor;
        }
        @Override
        public String extract(Tuple<LocalDate, Character> data)
        {
            return extractor.apply(data.first).toString() + data.second;
        }
    }
}
