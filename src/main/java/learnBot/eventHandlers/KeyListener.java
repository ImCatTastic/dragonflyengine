package learnBot.eventHandlers;

import javafx.scene.input.KeyCode;

public interface KeyListener
{
    void onKeyPressed(KeyCode keyCode);
    void onKeyReleased(KeyCode keyCode);
}
