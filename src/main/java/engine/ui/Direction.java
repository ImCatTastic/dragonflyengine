package engine.ui;

public enum Direction
{
    UP,
    RIGHT,
    DOWN,
    LEFT;
    public boolean isHorizontal()
    {
        return this == RIGHT || this == LEFT;
    }
    public boolean isVertical()
    {
        return this == UP || this == DOWN;
    }
    public static Direction parse(String value)
    {
        return switch (value)
        {
            case "left" -> LEFT;
            case "up" -> UP;
            case "right" -> RIGHT;
            case "down" -> DOWN;


            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }
}