package engine.logging.formatting;

public enum TextTruncationStyle
{
    NONE(null),
    TRIM(null),
    ELLIPSIS(TRIM),
    ABBREVIATE_MIN_THEN_TRIM(ELLIPSIS),
    ABBREVIATE_MIN_THEN_ELLIPSIS(ELLIPSIS),
    ABBREVIATE_ALL_THEN_TRIM(TRIM),
    ABBREVIATE_ALL_THEN_ELLIPSIS(TRIM)
    ;

    public final TextTruncationStyle fallback;
    TextTruncationStyle(TextTruncationStyle fallback)
    {
        this.fallback = fallback;
    }

    public String apply(String text, int overflowCap, TextTruncationPriority priority, TextTruncationPriority fallbackPriority)
    {
        if(text.length() <= overflowCap)
            return text;

        //TODO: Implement other styles

        String newString = "";

        int[] prioQueue;

        switch (priority)
        {
            case START_THEN_CENTER ->
            {
                prioQueue = new int[]{0, 1, 2};
            }
            case START_THEN_TAIL ->
            {
            }
            case CENTER_THEN_HEAD ->
            {
            }
            case CENTER_THEN_TAIL ->
            {
            }
            case TAIL_THEN_HEAD ->
            {
            }
            case TAIL_THEN_CENTER ->
            {
            }
        }






        return newString;
    }
}
