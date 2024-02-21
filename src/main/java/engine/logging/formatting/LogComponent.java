package engine.logging.formatting;

import engine.logging.Log;
import engine.logging.LogMessage;
import engine.logging.formatting.color.TextFormat;
import engine.util.Tuple;
import engine.util.formatting.ExtractableComponent;

import java.util.function.BiFunction;

import static engine.logging.formatting.color.LogTextColor.*;


public enum LogComponent implements ExtractableComponent<Tuple<LogMessage, LogComponentData>>
{
    MESSAGE(20, (message, format) -> new Tuple<>(message.content(), format)),
    PRIORITY(4, (message, format) ->
    {
        if(!Log.priorityEnabled && message.priority().ordinal() < Log.minPriority.ordinal())
            return new Tuple<>("", format);

        return switch (message.priority())
        {
            case LOW -> new Tuple<>("LOW", format.withTextColor(GREEN_BRIGHT));
            case MEDIUM -> new Tuple<>("MED", format.withTextColor(YELLOW_BRIGHT));
            case HIGH -> new Tuple<>("HIGH", format.withTextColor(RED_BRIGHT));
        };
    }),
    IDENTIFIER(12, (message, format) -> new Tuple<>(getOrEmpty(message.identifier().getName(), Log.namespaceEnabled), format)),
    STACKTRACE(24, (message, format) -> new Tuple<>(getOrEmpty(message.stackTraceElement().getClassName(), Log.stackTraceEnabled), format)),
    DATE(9, (message, format) ->
    {
        var str = getOrEmpty(Log.formatDate(message), Log.dateEnabled);
        return new Tuple<>(str.substring(0, str.length() - 1), format);
    }),
    TIME(16, (message, format) ->
    {
        var str = getOrEmpty(Log.formatTime(message), Log.timeEnabled);
        return new Tuple<>(str.substring(0, str.length() - 1), format);
    }),
    CATEGORY(3, (message, format) -> new Tuple<>(getOrEmpty(message.category().toVisualString(), Log.categoryEnabled), format));

    private final int defaultLength;
    private final BiFunction<LogMessage, TextFormat, Tuple<String, TextFormat>> extractor;
    LogComponent(int defaultLength, BiFunction<LogMessage, TextFormat, Tuple<String, TextFormat>> extractor)
    {
        this.defaultLength = defaultLength;
        this.extractor = extractor;
    }
    private static String getOrEmpty(String str, boolean condition)
    {
        if(condition)
            return str;
        return "";
    }
    @Override
    public String extract(Tuple<LogMessage, LogComponentData> data)
    {
        LogMessage msg = data.first;
        LogComponentData cData = data.second;
        var extractedData = extractor.apply(msg, cData.textFormat.copy());
        var content = extractedData.first;
        var maxLength = cData.maxLength == 0 ? this.defaultLength : cData.maxLength;
        var charDelta = maxLength == -1 ? 0 : maxLength - content.length();
        if(charDelta > 0)
            content += " ".repeat(charDelta);
        else if(charDelta < 0)
            content = cData.truncator.format(extractedData.first, maxLength);

        return extractedData.second.apply(content, maxLength);
    }
}