package engine.util;

import javafx.scene.paint.Color;


public class Config
{
    public String windowTitle = "MyGame";
    public String version = "0.0.1";
    public int viewWidth = 16;
    public int viewHeight = 9;
    public int windowWidth = 660;
    public int windowHeight = 660;
    public Color windowBackgroundColor = Color.BLACK;
    public boolean startFullscreen = false;
    public boolean enableResizing = true;

    public Config copy()
    {
        Config copy = new Config();
        copy.windowTitle = windowTitle;
        copy.version = version;
        copy.viewWidth = viewWidth;
        copy.viewHeight = viewHeight;
        copy.windowWidth = windowWidth;
        copy.windowHeight = windowHeight;
        copy.windowBackgroundColor = windowBackgroundColor;
        copy.startFullscreen = startFullscreen;
        return copy;
    }
}