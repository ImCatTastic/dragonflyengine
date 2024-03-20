package engine.ui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Defines an enum allowing to customize the unit to be used for a {@code UIComponent} property
 */
public enum Unit
{
    /**
     * Used internally to not modify it, DO NOT USE!
     */
    LITERAL,
    /**
     * Uses a unit that scales in accordance to the width of the window
     */
    VIEW_WIDTH,
    /**
     * Uses a unit that scales in accordance to the height of the window
     */
    VIEW_HEIGHT,
    /**
     * Uses a unit that scales in accordance to the smaller dimension, based on the defined optimal aspect ratio
     */
    VIEW_MIN,
    /**
     * Uses a unit that scales in accordance to the greater dimension, based on the defined optimal aspect ratio
     */
    VIEW_MAX,
    /**
     * Uses a percentage of the parent container
     */
    PERCENT,
    /**
     * Uses absolute values independent of the window sizing, these don't correlate to literal pixels,
     * but rather assume a size of 1920x1080 and the real resolution is transformed to this resolution
     * system.
     */
    PIXEL,
    /**
     * Used for defining cells in a grid layout, will throw an exception else
     */
    FRACTIONAL,
    /**
     * Uses modified world coordinates for creating UI elements that are directly tied to the game world.
     * Modify {@code WORLD_TRANSLATION} to adjust the modifier.
     */
    WORLD;
    /**
     * Defines the translation modifier from world coordinates, default is 100 meaning 100 WORLD units are equal to 1 actual World unit in the game world
     */
    public final static DoubleProperty WORLD_TRANSLATION = new SimpleDoubleProperty(100.0d);
    public static Unit parse(String value)
    {
        return switch (value)
        {
            case "px" -> PIXEL;
            case "%" -> PERCENT;
            case "vw" -> VIEW_WIDTH;
            case "vh" -> VIEW_HEIGHT;
            case "vmin" -> VIEW_MIN;
            case "vmax" -> VIEW_MAX;
            case "fr" -> FRACTIONAL;
            case "w" -> WORLD;
            default -> throw new IllegalStateException("Failed to parse unit: " + value);
        };
    }
}
