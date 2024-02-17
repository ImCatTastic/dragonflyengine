package temp.learnBot;

import org.jetbrains.annotations.NotNull;

public enum RobotFamily
{
    /**
     * A Blue Triangle robot.
     */
    TRIANGLE_BLUE,
    /**
     * A Teal Square robot.
     */
    SQUARE_AQUA,
    /**
     * A Black Square robot.
     */
    SQUARE_BLUE,
    /**
     *A Green Square robot.
     */
    SQUARE_GREEN,
    /**
     * An Orange Square robot.
     */
    SQUARE_ORANGE,
    /**
     * A Purple Square robot.
     */
    SQUARE_PURPLE,
    /**
     * A Red Square robot.
     */
    SQUARE_RED,
    /**
     * A Yellow Square robot.
     */
    SQUARE_YELLOW,
    /**
     * A Black Square robot.
     */
    SQUARE_BLACK,
    /**
     * A White Square robot.
     */
    SQUARE_WHITE;

    /**
     * Returns the identifier of this robot family.
     *
     * @return the identifier of this robot family
     */
    public @NotNull String getIdentifier()
    {
        return name().toLowerCase();
    }
}
