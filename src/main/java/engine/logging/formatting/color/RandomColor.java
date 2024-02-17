package engine.logging.formatting.color;

import java.util.Arrays;
import java.util.Random;

public class RandomColor 
{
    public static LogBackgroundColor backgroundWithExclusion(LogBackgroundColor... exclusionList)
    {
        return background(Arrays.stream(LogBackgroundColor.values())
                .filter(color -> !Arrays.asList(exclusionList).contains(color))
                .toArray(LogBackgroundColor[]::new));
    }
    public static LogBackgroundColor backgroundWithInclusion(LogBackgroundColor... inclusionList)
    {
        return background(inclusionList);
    }
    public static LogBackgroundColor background()
    {
        return background(LogBackgroundColor.values());
    }
    private static LogBackgroundColor background(LogBackgroundColor[] values)
    {
        if (values.length == 0)
            throw new IllegalArgumentException("No values provided");

        int randomIndex = new Random().nextInt(values.length);
        return values[randomIndex];
    }
    public static LogTextColor textWithExclusion(LogTextColor... exclusionList)
    {
        return text(Arrays.stream(LogTextColor.values())
                .filter(color -> !Arrays.asList(exclusionList).contains(color))
                .toArray(LogTextColor[]::new));
    }
    public static LogTextColor textWithInclusion(LogTextColor... inclusionList)
    {
        return text(inclusionList);
    }
    public static LogTextColor text()
    {
        return text(LogTextColor.values());
    }
    private static LogTextColor text(LogTextColor[] values)
    {
        if (values.length == 0)
            throw new IllegalArgumentException("No values provided");

        int randomIndex = new Random().nextInt(values.length);
        return values[randomIndex];
    }
}
