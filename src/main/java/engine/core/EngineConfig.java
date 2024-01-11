package engine.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class EngineConfig
{
    public double initialWidth = 11;
    public double initialWidthPX = -1;
    public double initialHeight = 11;
    public double initialHeightPX = -1;
    public boolean scaleMonitor = true;
    public boolean startFullscreen = false;
    public ScalingMode scalingMode = ScalingMode.HEIGHT;
    public double unit = 60;

    public EngineConfig() {}
    @Contract(pure = true)
    public EngineConfig(final @NotNull EngineConfig config)
    {
        this.initialWidth = config.initialWidth;
        this.initialHeight = config.initialHeight;
        this.initialWidthPX = config.initialWidthPX;
        this.initialHeightPX = config.initialHeightPX;
        this.scaleMonitor = config.scaleMonitor;
        this.startFullscreen = config.startFullscreen;
        this.scalingMode = config.scalingMode;
        this.unit = config.unit;
    }
}
