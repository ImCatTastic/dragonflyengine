package engine.logging.ansi;

public enum LogBackgroundColor implements ANSIComponent
{
    NONE(0),
    BLACK(40),
    RED(41),
    GREEN(42),
    YELLOW(43),
    BLUE(44),
    PURPLE(45),
    CYAN(46),
    GRAY(47),

    BLACK_BRIGHT(100),
    RED_BRIGHT(101),
    GREEN_BRIGHT(102),
    YELLOW_BRIGHT(103),
    BLUE_BRIGHT(104),
    PURPLE_BRIGHT(105),
    CYAN_BRIGHT(106),
    GRAY_BRIGHT(107);

    private final int codeID;
    public final String code;
    LogBackgroundColor(int id)
    {
        codeID = id;
        code = "\u001B[" + id + "m";
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
