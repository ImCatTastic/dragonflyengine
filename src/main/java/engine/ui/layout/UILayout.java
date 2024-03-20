package engine.ui.layout;

import engine.ui.UIBoundingBox;
import engine.ui.util.Padding;
import engine.util.math.Vec4;
import javafx.beans.property.ObjectProperty;

public abstract class UILayout
{
    protected final UIBoundingBox paddedBoundingBox;
    protected final UIBoundingBox boundingBox;
    protected final Padding padding;
    public UILayout(UIBoundingBox boundingBox, Padding padding)
    {
        this.boundingBox = boundingBox;
        this.padding = padding;

        this.paddedBoundingBox = new UIBoundingBox();
        this.paddedBoundingBox.minXProperty.bind(boundingBox.minXProperty.add(padding.left));
        this.paddedBoundingBox.minYProperty.bind(boundingBox.minYProperty.add(padding.top));
        this.paddedBoundingBox.widthProperty.bind(boundingBox.widthProperty.subtract(padding.left).subtract(padding.right));
        this.paddedBoundingBox.heightProperty.bind(boundingBox.heightProperty.subtract(padding.top).subtract(padding.bottom));
    }
    public abstract UIBoundingBox requestBounds();
}
