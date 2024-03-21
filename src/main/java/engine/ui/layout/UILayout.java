package engine.ui.layout;

import engine.ui.UIBoundingBox;
import engine.ui.util.Insets;

public abstract class UILayout
{
    protected final UIBoundingBox paddedBoundingBox;
    protected final UIBoundingBox boundingBox;
    protected final Insets padding;
    public UILayout(UIBoundingBox boundingBox, Insets padding)
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
