package engine.ui;

import engine.core.Window;
import javafx.beans.property.*;
import javafx.scene.layout.Pane;

public class UICanvas
{
    //TODO: allow to set dimensions of canvas itself
    private final UIComponent rootComponent;
    public UICanvas(final Pane root)
    {
        rootComponent = new ReadonlyUIComponent(this, root);
        root.getChildren().add(rootComponent.getContainer_background());
    }
    UIComponent getRootComponent()
    {
        return rootComponent;
    }
}