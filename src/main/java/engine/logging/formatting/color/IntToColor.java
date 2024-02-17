package engine.logging.formatting.color;

import java.util.Arrays;

public class IntToColor
{
    public static LogBackgroundColor backgroundWithExclusion(int index, LogBackgroundColor... exclusionList)
    {
        return background(index, Arrays.stream(LogBackgroundColor.values())
                .filter(color -> !Arrays.asList(exclusionList).contains(color))
                .toArray(LogBackgroundColor[]::new));
    }
    public static LogBackgroundColor backgroundWithInclusion(int index, LogBackgroundColor... inclusionList)
    {
        return background(index, inclusionList);
    }
    public static LogBackgroundColor background(int index)
    {
        return background(index, LogBackgroundColor.values());
    }
    private static LogBackgroundColor background(int index, LogBackgroundColor[] values)
    {
        if (values.length == 0)
            throw new IllegalArgumentException("No values provided");
        return values[index];
    }

    public static LogTextColor textWithExclusion(int index, LogTextColor... exclusionList)
    {
        return text(index, Arrays.stream(LogTextColor.values())
                .filter(color -> !Arrays.asList(exclusionList).contains(color))
                .toArray(LogTextColor[]::new));
    }
    public static LogTextColor textWithInclusion(int index, LogTextColor... inclusionList)
    {
        return text(index, inclusionList);
    }
    public static LogTextColor text(int index)
    {
        return text(index, LogTextColor.values());
    }
    private static LogTextColor text(int index, LogTextColor[] values)
    {
        if (values.length == 0)
            throw new IllegalArgumentException("No values provided");
        return values[index];
    }
}
