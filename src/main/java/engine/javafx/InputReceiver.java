package engine.javafx;

import engine.javafx.event.KeyEvent;
import engine.javafx.event.MouseEvent;

public interface InputReceiver
{
    void onMouseEvent(MouseEvent event);
    void onKeyEvent(KeyEvent event);
}
