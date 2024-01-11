package engine.logging.formatting;

import engine.logging.LogMessage;
import engine.logging.ansi.TextFormat;
import engine.logging.formatting.truncation.Truncator;

public class LogComponentData
{
    public final int maxLength;
    public final TextFormat textFormat;
    public final Truncator truncator;
    public LogComponentData(TextFormat textFormat, int maxLength, Truncator truncator)
    {
        this.textFormat = textFormat;
        this.maxLength = maxLength;
        this.truncator = truncator;
    }
    public LogComponentData(Truncator truncator)
    {
        this(new TextFormat(), 0, truncator);
    }
    public LogComponentData(boolean truncate)
    {
        this(new TextFormat(), truncate ? 0 : -1, new Truncator());
    }
    public LogComponentData(TextFormat textFormat)
    {
        this(textFormat, 0, new Truncator());
    }
    public LogComponentData(TextFormat textFormat, boolean truncate)
    {
        this(textFormat, truncate ? 0 : -1, new Truncator());
    }
    public LogComponentData(Truncator truncator, int maxLength)
    {
        this(new TextFormat(), maxLength, truncator);
    }
    public LogComponentData()
    {
        this(new TextFormat());
    }
}
