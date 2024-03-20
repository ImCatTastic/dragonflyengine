package engine.ui;

import engine.util.PivotPoint;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;

class ReadonlyUIComponent extends UIComponent
{
    public ReadonlyUIComponent(UICanvas canvas, Pane root)
    {
        super(canvas, root);
    }
    @Override
    protected SimpleDoubleProperty getWidthProperty()
    {
        throw new UnsupportedOperationException("Cannot access widthProperty");
    }
    @Override
    protected SimpleDoubleProperty getHeightProperty()
    {
        throw new UnsupportedOperationException("Cannot access heightProperty");
    }
}
