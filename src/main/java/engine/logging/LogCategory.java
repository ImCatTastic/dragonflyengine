package engine.logging;

import engine.logging.formatting.color.LogTextAttribute;
import engine.logging.formatting.color.LogBackgroundColor;

public enum LogCategory
{
    DEBUG('D', LogBackgroundColor.GREEN),
    WARN('W', LogBackgroundColor.YELLOW),
    INFO('I', LogBackgroundColor.BLUE),
    ERROR('E', LogBackgroundColor.RED),
    FATAL('F', LogBackgroundColor.BLACK);

    private final char letter;
    private final LogBackgroundColor backgroundColor;
    LogCategory(char letter, LogBackgroundColor backgroundColor)
    {
        this.letter = letter;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public String toString()
    {
        return "" + backgroundColor + letter + LogTextAttribute.CLEAR;
    }
    public String toVisualString()
    {
    return backgroundColor.apply(" " + LogTextAttribute.BOLD + letter + " ");
    }
}
