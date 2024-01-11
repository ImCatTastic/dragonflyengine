package engine.logging.ansi;

import engine.logging.formatting.truncation.Truncator;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TextFormat
{
    private Truncator truncator = new Truncator();
    private LogTextColor textColor = LogTextColor.WHITE;
    private LogBackgroundColor backgroundColor = LogBackgroundColor.NONE;
    private LogTextAttribute[] attributes = null;

    public TextFormat()
    {

    }
    public TextFormat(TextFormat format)
    {
        truncator = format.truncator;
        textColor = format.textColor;
        backgroundColor = format.backgroundColor;
        attributes = format.attributes;
    }

    public TextFormat withTruncator(Truncator truncator)
    {
        this.truncator = truncator;
        return this;
    }
    public TextFormat withTextColor(LogTextColor textColor)
    {
        this.textColor = textColor;
        return this;
    }
    public TextFormat withBackgroundColor(LogBackgroundColor backgroundColor)
    {
        this.backgroundColor = backgroundColor;
        return this;
    }
    public TextFormat withAttributes(LogTextAttribute... attributes)
    {
        this.attributes = attributes;
        return this;
    }
    public String apply(String text, int charLimit)
    {
        String attribs = attributes != null ? Arrays.stream(attributes).map(ANSIComponent::toString).collect(Collectors.joining()) : "";
        return attribs + textColor + backgroundColor + truncator.format(text, charLimit) + LogTextAttribute.CLEAR;
    }
    public Truncator getTruncator()
    {
        return truncator;
    }
    public LogTextColor getTextColor()
    {
        return textColor;
    }
    public LogBackgroundColor getBackgroundColor()
    {
        return backgroundColor;
    }
    public LogTextAttribute[] getAttributes()
    {
        return attributes;
    }

    public TextFormat copy()
    {
        return new TextFormat(this);
    }
}