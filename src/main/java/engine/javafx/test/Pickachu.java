package engine.javafx.test;

import engine.javafx.*;
import engine.javafx.event.Updatable;

public class Pickachu extends GameObject implements Updatable
{
    public Pickachu(double x, double y, int z)
    {
        transform.setPosition(x, y);
        //setCanvasRenderer(new SpriteRenderer(Main.PICKACHU_SPRITE_ID));
    }

    @Override
    public void update()
    {

    }
}