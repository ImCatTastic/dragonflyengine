package engine.core;

public class Config
{
    public int unitSize = 10;
    public int windowWidth = 660;
    public int windowHeight = 660;
    public boolean unitAsReference = false;
    public boolean scaleMonitor = true;
    public boolean startFullscreen = false;

    private Config copy(Config config)
    {
        Config copy = new Config();
        copy.windowWidth = config.windowWidth;
        copy.windowHeight = config.windowHeight;
        copy.unitAsReference = config.unitAsReference;
        copy.scaleMonitor = config.scaleMonitor;
        copy.startFullscreen = config.startFullscreen;
        return copy;
    }
}