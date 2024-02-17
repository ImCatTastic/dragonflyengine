package engine.logging.formatting.color;

public enum LogTextAttribute implements ANSIComponent
{
    CLEAR(0),
    BOLD(1),
    DIM(2),
    ITALIC(3),
    UNDERLINE(4),
    BLINK_SLOW(5),
    BLINK_FAST(6),
    INVERSE_VIDEO(7),
    CONCEAL(8),
    STRIKETHROUGH(9),
    UNDERLINE_DOUBLE(21);

    public final String code;
    LogTextAttribute(int code)
    {
        this.code = "\u001B[" + code + "m";
    }
    @Override
    public String toString()
    {
        return code;
    }
    public String apply(String str)
    {
        return code + str + LogTextAttribute.CLEAR;
    }
}
