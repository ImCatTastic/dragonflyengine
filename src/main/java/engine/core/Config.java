package engine.core;

import engine.javafx.FitMode;
import javafx.scene.paint.Color;


public class Config
{
    public String windowTitle = "MyGame";
    public int unitSize = 10;
    public int windowWidth = 660;
    public int windowHeight = 660;
    public FitMode fitMode = FitMode.HEIGHT;
    public Color windowBackgroundColor = Color.BLACK;
    public boolean startFullscreen = false;
    public boolean enableResizing = true;

    public Config copy()
    {
        Config copy = new Config();
        copy.windowTitle = windowTitle;
        copy.unitSize = unitSize;
        copy.windowWidth = windowWidth;
        copy.windowHeight = windowHeight;
        copy.windowBackgroundColor = windowBackgroundColor;
        copy.startFullscreen = startFullscreen;
        return copy;
    }
}