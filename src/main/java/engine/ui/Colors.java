package engine.ui;

import engine.rendering.SpriteRenderer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;

public class Colors
{
    private final static HashMap<String, Paint> map = new HashMap<>();
    static
    {
        map.put("red", Color.rgb(255, 0, 0));
        map.put("green", Color.rgb(0, 255, 0));
        map.put("blue", Color.rgb(0, 0, 255));
        map.put("cyan", Color.rgb(0, 255, 255));
        map.put("yellow", Color.rgb(255, 255, 0));
        map.put("purple", Color.rgb(255, 0, 255));
        map.put("white", Color.rgb(255, 255, 255));
        map.put("black", Color.rgb(0, 0, 0));
    }
    public static Paint get(String value)
    {
        if(map.containsKey(value))
            return map.get(value);

        throw new IllegalStateException("Failed to read color while parsing: " + value);
    }
}
