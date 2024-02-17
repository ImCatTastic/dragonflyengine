package temp.learnBot.eventHandlers;

import engine.core.Key;
import engine.core.KeyAction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public interface KeyListener
{
    void onKeyEvent(KeyEvent keyEvent);

    void onKeyPressed(KeyEvent keyEvent);
    void onKeyHold(KeyEvent keyEvent);
    void onKeyReleased(KeyEvent keyEvent);
}
