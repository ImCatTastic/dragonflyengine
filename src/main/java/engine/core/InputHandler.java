package engine.core;

import javafx.scene.input.KeyCode;
import temp.learnBot.eventHandlers.KeyListener;
import temp.learnBot.eventHandlers.MouseListener;
import org.lwjgl.glfw.GLFW;

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

    public void triggerMouseEvent(float x, float y)
    {
        for (MouseListener mouseListener : mouseListeners)
            System.out.println("Okay");
    }
    public void triggerKeyEvent(int keyId, int actionId)
    {
        /*
        Key key = Keys.getKey(keyId);

        if(actionId == GLFW.GLFW_PRESS)
            for (KeyListener keyListener : keyListeners)
                keyListener.onKeyPressed(key);

         else if(actionId == GLFW.GLFW_RELEASE)
            for (KeyListener keyListener : keyListeners)
                keyListener.onKeyReleased(key);

        else if(actionId == GLFW.GLFW_REPEAT)
            for (KeyListener keyListener : keyListeners)
                keyListener.onKeyHold(key);

         */
    }
}
