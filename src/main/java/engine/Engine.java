package engine;


import engine.util.Units;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import learnBot.eventHandlers.KeyListener;
import learnBot.eventHandlers.MouseListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Engine
{
    public final static double targetUPS = 120;
    public final static double frameTime = 1000d / targetUPS;
    public static boolean displayCollider = false;
    public static double width = 10;
    public static double height = 10;
    public static double unit = 40;
    private static boolean running = false;
    private static MainLoop loop = new MainLoop();
    private static Units units;
    public static Units getUnits()
    {
        return units;
    }
    public static void start(GameManager manager)
    {
        if(running)
            return;

        running = true;
        units = new Units(width, height, unit, new SimpleDoubleProperty(1));
        Renderer.onLoadComplete(() -> loop.start(manager));
        Application.launch(Renderer.class);
    }

    public static void stop()
    {
        loop.stop();
    }

    private final ArrayList<KeyListener> keyListeners = new ArrayList<>();
    private final ArrayList<MouseListener> mouseListeners = new ArrayList<>();
    private final Set<KeyCode> keysPressed = new HashSet<>();
    public void addMouseListener(MouseListener listener)
    {
        mouseListeners.add(listener);
    }
    public void addKeyListener(KeyListener listener)
    {
        keyListeners.add(listener);
    }
    void triggerMouseEvent()
    {
        for (MouseListener mouseListener : mouseListeners)
            System.out.println("Okay");
    }
    void triggerKeyEvent()
    {
        for (KeyListener keyListener : keyListeners)
            keyListener.onKeyPressed(null);
    }

    protected static void registerGameObject(GameObject gameObject, Node rootNode)
    {
        if(gameObject instanceof UpdateableGameObject ugo)
            loop.addObject(ugo);

        Renderer.getInstance().addGameObject(gameObject, rootNode);
    }
}
