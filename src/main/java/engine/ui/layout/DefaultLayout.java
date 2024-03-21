package engine.ui.layout;

import engine.ui.UIBoundingBox;
import engine.ui.util.Insets;

public class DefaultLayout extends UILayout
{
    public DefaultLayout(UIBoundingBox boundingBox, Insets padding)
    {
        super(boundingBox, padding);
    }
    @Override
    public UIBoundingBox requestBounds()
    {
        return paddedBoundingBox;
    }
}