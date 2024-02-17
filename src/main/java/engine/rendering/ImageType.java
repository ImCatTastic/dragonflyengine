package engine.rendering;

public enum ImageType
{
    JPG("jpg"),
    PNG("png"),
    SVG("svg");

    private final String string;
    ImageType(String string)
    {
        this.string = string;
    }
    @Override
    public String toString()
    {
        return string;
    }
}
