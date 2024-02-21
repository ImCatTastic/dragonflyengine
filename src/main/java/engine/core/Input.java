package engine.core;

import engine.util.math.Vec2;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.util.HashSet;

public class Input
{
    private final static HashSet<KeyCode> pressedKeys = new HashSet<>();
    private final static HashSet<KeyCode> activeKeys = new HashSet<>();
    private final static HashSet<KeyCode> liftedKeys = new HashSet<>();
    private final static HashSet<MouseButton> pressedMouseButtons = new HashSet<>();
    private final static HashSet<MouseButton> activeMouseButtons = new HashSet<>();
    private final static HashSet<MouseButton> liftedMouseButtons = new HashSet<>();
    private static double mouseScrollDelta = 0;
    private static Vec2 mousePosition = new Vec2(0, 0); //in world coordinates
    private static Vec2 mouseMoveDelta = new Vec2(0, 0);
    static void activateKey(KeyCode keyCode)
    {
        if(activeKeys.contains(keyCode))
            return;

        pressedKeys.add(keyCode);
        activeKeys.add(keyCode);
    }
    static void deactivateKey(KeyCode keyCode)
    {
        activeKeys.remove(keyCode);
        liftedKeys.add(keyCode);
    }
    static void activateMouseButton(MouseButton mouseButton)
    {
        if(activeMouseButtons.contains(mouseButton))
            return;

        pressedMouseButtons.add(mouseButton);
        activeMouseButtons.add(mouseButton);
    }
    static void deactivateMouseButton(MouseButton mouseButton)
    {
        activeMouseButtons.remove(mouseButton);
        liftedMouseButtons.add(mouseButton);
    }
    static void increaseScrollCounter(double delta)
    {
        mouseScrollDelta += delta;
    }
    static void moveMouse(double deltaX, double deltaY)
    {
        mouseMoveDelta = mouseMoveDelta.add(deltaX, deltaY);
    }
    static void update(double x, double y)
    {
        mousePosition = new Vec2(x, y);
    }
    static void clear()
    {
        mouseMoveDelta = new Vec2(0, 0);
        mouseScrollDelta = 0;
        pressedKeys.clear();
        liftedKeys.clear();
        pressedMouseButtons.clear();
        liftedMouseButtons.clear();
    }
    public static boolean isKeyDown(KeyCode keyCode)
    {
        return pressedKeys.contains(keyCode);
    }
    public static boolean isKeyHeld(KeyCode keyCode)
    {
        return activeKeys.contains(keyCode);
    }
    public static boolean isKeyUp(KeyCode keyCode)
    {
        return liftedKeys.contains(keyCode);
    }
    public static Vec2 getMousePosition()
    {
        return mousePosition;
    }
    public static double isMouseScrollDelta()
    {
        return mouseScrollDelta;
    }
    public static boolean isMouseButtonDown(MouseButton button)
    {
        return pressedMouseButtons.contains(button);
    }
    public static boolean isMouseButtonHeld(MouseButton button)
    {
        return activeMouseButtons.contains(button);
    }
    public static boolean isMouseButtonUp(MouseButton button)
    {
        return liftedMouseButtons.contains(button);
    }
}