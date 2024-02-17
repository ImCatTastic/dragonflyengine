package engine.javafx.event;

import engine.core.Key;

public class KeyEvent
{
    public final Key key;
    public final EventAction action;
    public KeyEvent(Key key, EventAction action)
    {
        this.key = key;
        this.action = action;
    }
}
