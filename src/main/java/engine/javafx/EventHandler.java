package engine.javafx;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import temp.learnBot.eventHandlers.KeyListener;
import temp.learnBot.eventHandlers.MouseListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EventHandler
{
    private final ArrayList<KeyListener> keyListeners = new ArrayList<>();
    private final ArrayList<MouseListener> mouseListeners = new ArrayList<>();
    private final Set<KeyCode> keysPressed = new HashSet<>();


    private int keyEventCount = 0;
    private int mouseEventCount = 0;
    private int windowEventCount = 0;
    private final KeyEvent[] keyEvents = new KeyEvent[10];
    private final MouseEvent[] mouseEvents = new MouseEvent[10];
    private final WindowEvent[] windowEvents = new WindowEvent[10];

    public void processKeyEvent(KeyEvent keyEvent)
    {
        
    }
    public void processMouseEvent(MouseEvent mouseEvent)
    {

    }
    public void processUpdateEvent(double deltaTime)
    {

    }
    public void processWindowEvent(WindowEvent windowEvent)
    {

    }

    public void update()
    {

    }
}