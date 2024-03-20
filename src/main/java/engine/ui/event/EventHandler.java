package engine.ui.event;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class EventHandler
{
    public EventHandler(Pane receiver)
    {
        receiver.setOnMouseEntered(e -> onMouseEntered.handle(e));
        receiver.setOnMouseExited(e -> onMouseExited.handle(e));
        receiver.setOnMousePressed(e -> onMousePressed.handle(e));
        receiver.setOnMouseReleased(e -> onMouseReleased.handle(e));
        receiver.setOnMouseClicked(e -> onMouseClicked.handle(e));
        receiver.setOnMouseDragEntered(e -> onMouseDragEntered.handle(e));
        receiver.setOnMouseDragExited(e -> onMouseDragExited.handle(e));
        receiver.setOnMouseDragOver(e -> onMouseDragOver.handle(e));
        receiver.setOnMouseMoved(e -> onMouseMoved.handle(e));
        receiver.setOnMouseDragged(e -> onMouseDragged.handle(e));
        receiver.setOnMouseDragReleased(e -> onMouseDragReleased.handle(e));

        receiver.setOnDragDetected(e -> onDragStart.handle(e));
    }
    public javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> onMouseEntered = e -> {};
    public javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> onMouseMoved = e -> {};
    public javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> onMouseExited = e -> {};
    public javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> onMousePressed = e -> {};
    public javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> onMouseReleased = e -> {};
    public javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> onMouseClicked = e -> {};
    public javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> onMouseDragEntered = e -> {};
    public javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> onMouseDragExited = e -> {};
    public javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> onMouseDragOver = e -> {};
    public javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> onMouseDragged = e -> {};
    public javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> onMouseDragReleased = e -> {};

    public javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> onDragStart = e -> {};
    public javafx.event.EventHandler<? super javafx.scene.input.DragEvent> onDrag = e -> {};
}
