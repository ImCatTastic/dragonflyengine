package engine.core;

public class FontFamily
{
    public final String name;
    private FontFamily(String name)
    {
        this.name = name;
    }
    public final static FontFamily ARIAL = new FontFamily("arial");
}
