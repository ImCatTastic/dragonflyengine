package engine.core;

import javafx.scene.input.KeyCode;
import learnBot.eventHandlers.KeyListener;
import learnBot.eventHandlers.MouseListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class InputHandler
{
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
}
