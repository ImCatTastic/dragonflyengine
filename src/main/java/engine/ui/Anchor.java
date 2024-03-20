package engine.ui;

public class Anchor
{
    public enum Horizontal
    {
        LEFT,
        CENTER,
        RIGHT,
        STRETCH;
        public static Horizontal parse(String input)
        {
            return switch (input)
            {
                case "left" -> LEFT;
                case "center" -> CENTER;
                case "right" -> RIGHT;
                case "stretch" -> STRETCH;
                default -> throw new IllegalStateException("Failed to parse horizontal anchor. Unexpected value: " + input);
            };
        }
    }
    public enum Vertical
    {
        TOP,
        CENTER,
        BOTTOM,
        STRETCH;
        public static Vertical parse(String input)
        {
            return switch (input)
            {
                case "top" -> TOP;
                case "center" -> CENTER;
                case "bottom" -> BOTTOM;
                case "stretch" -> STRETCH;
                default -> throw new IllegalStateException("Failed to parse vertical anchor. Unexpected value: " + input);
            };
        }
    }
}
