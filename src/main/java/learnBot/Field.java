package learnBot;

import engine.GameObject;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Field extends GameObject
{
    private final HashMap<String, HashSet<Entity>> entityBuckets = new HashMap<>();
    private final ArrayList<String> obstacleRegisters = new ArrayList<>();
    private final LinkedList<Entity> collectables = new LinkedList<>();
    private final LinkedList<Entity> consumables = new LinkedList<>();
    private final Wall[] walls = new Wall[4];


    public Field(double x, double y)
    {
        super(x, y, 0);

        Rectangle body = addRectangle(0, 0);
        body.setFill(new Color(32 / 255d,32 / 255d,32 / 255d,1));
    }
}
