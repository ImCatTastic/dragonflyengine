package temp.learnBot.eventHandlers;

public interface MouseListener
{
    default void onEntityClick(MouseEvent event) {}
    default void onEntityEnter(MouseEvent event) {}
    default void onEntityExit(MouseEvent event) {}
    default void onEntityDragStart(MouseEvent event) {}
    default void onEntityDrag(MouseEvent event) {}
    default void onEntityDragEnd(MouseEvent event) {}


    void onFieldClick(MouseEvent event);
    default void onFieldEnter(MouseEvent event) {}
    default void onFieldExit(MouseEvent event) {}
    default void onFieldDragStart(MouseEvent event) {}
    default void onFieldDrag(MouseEvent event) {}
    default void onFieldDragEnd(MouseEvent event) {}
    default void onFieldMove(MouseEvent event) {}
}
