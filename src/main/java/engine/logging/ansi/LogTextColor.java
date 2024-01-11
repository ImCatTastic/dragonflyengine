package engine.logging.ansi;

public enum LogTextColor implements ANSIComponent
{
    WHITE(0),
    BLACK(30),
    RED(31),
    GREEN(32),
    YELLOW(33),
    BLUE(34),
    PURPLE(35),
    CYAN(36),
    GRAY(37),

    BLACK_BRIGHT(90),
    RED_BRIGHT(91),
    GREEN_BRIGHT(92),
    YELLOW_BRIGHT(93),
    BLUE_BRIGHT(94),
    PURPLE_BRIGHT(95),
    CYAN_BRIGHT(96),
    GRAY_BRIGHT(97);

    private final int codeID;
    private final String code;
    LogTextColor(int id)
    {
        this.codeID = id;
        this.code = "\u001B[" + id + "m";
    }
    public String getCode()
    {
        return codeID == 0 ? "" : code;
    }
    @Override
    public String toString()
    {
        return getCode();
    }
    public String apply(String str)
    {
        return codeID == 0 ? str : code + str + LogTextAttribute.CLEAR;
    }
}
