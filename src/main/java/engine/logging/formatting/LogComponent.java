package engine.logging.formatting;

import engine.logging.LogHandler;
import engine.logging.LogMessage;
import engine.logging.ansi.TextFormat;
import engine.util.formatter.ExtractableComponent;
import engine.util.Tuple;

import java.util.function.BiFunction;

import static engine.logging.ansi.LogTextColor.*;


public enum LogComponent implements ExtractableComponent<Tuple<LogMessage, LogComponentData>>
{
    MESSAGE(20, (message, format) -> new Tuple<>(message.content(), format)),
    PRIORITY(4, (message, format) ->
            switch (message.priority())
            {
                case LOW -> new Tuple<>("LOW", format.withTextColor(GREEN_BRIGHT));
                case MEDIUM -> new Tuple<>("MED", format.withTextColor(YELLOW_BRIGHT));
                case HIGH -> new Tuple<>("HIGH", format.withTextColor(RED_BRIGHT));
            }),
    IDENTIFIER(12, (message, format) -> new Tuple<>(getOrEmpty(message.identifier().getName(), LogHandler.namespaceEnabled), format)),
    NAMESPACE(12, (message, format) ->
    {
        var str = getOrEmpty(message.identifier().getNamespace().getName(), LogHandler.namespaceEnabled);
        return new Tuple<>(str, format.withTextColor(message.identifier().getNamespace().getColor()));
    }),
    STACKTRACE(24, (message, format) -> new Tuple<>(getOrEmpty(message.stackTraceElement().toString(), LogHandler.stackTraceEnabled), format)),
    DATE(9, (message, format) ->
    {
        var str = getOrEmpty(LogHandler.formatDate(message), LogHandler.dateEnabled);
        return new Tuple<>(str.substring(0, str.length() - 1), format);
    }),
    TIME(16, (message, format) ->
    {
        var str = getOrEmpty(LogHandler.formatTime(message), LogHandler.timeEnabled);
        return new Tuple<>(str.substring(0, str.length() - 1), format);
    }),
    CATEGORY(3, (message, format) -> new Tuple<>(getOrEmpty(message.category().toVisualString(), LogHandler.categoryEnabled), format));

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
