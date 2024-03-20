package engine.ui;

public enum LayoutType
{
    NONE,
    STACK,
    GRID,
    FIT_CONTENT;

    public static LayoutType parse(String input)
    {
        return switch (input)
        {
            case "none" -> NONE;
            case "stack" -> STACK;
            case "grid" -> GRID;
            case "fit" -> FIT_CONTENT;
            default -> throw new IllegalStateException("Failed to parse LayoutType. Unexpected value: " + input);
        };
    }
}
