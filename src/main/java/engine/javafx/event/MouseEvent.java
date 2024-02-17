package engine.javafx.event;

public class MouseEvent
{
    public final MouseButton button;
    public final EventAction action;
    public final int x;
    public final int y;
    public MouseEvent(MouseButton button, EventAction action, int x, int y)
    {
        this.button = button;
        this.action = action;
        this.x = x;
        this.y = y;
    }
}