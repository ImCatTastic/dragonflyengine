package engine.spriteBuilder;

public class SpriteBuilder
{
    /**
     * Defines the modifier relative to a world unit
     */
    private static double modifier = 100;
    public static double getModifier()
    {
        return modifier;
    }
    public static void setModifier(double modifier)
    {
        if (locked)
            throw new IllegalStateException("cannot modify modifier after initialization");

        SpriteBuilder.modifier = modifier;
    }
    private static boolean locked = false;
    public static void lock()
    {
        //TODO: call after init()
        locked = true;
    }
}