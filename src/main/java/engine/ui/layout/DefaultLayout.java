package engine.ui.layout;

import engine.ui.UIBoundingBox;
import engine.ui.util.Padding;

public class DefaultLayout extends UILayout
{
    public DefaultLayout(UIBoundingBox boundingBox, Padding padding)
    {
        super(boundingBox, padding);
    }
    @Override
    public UIBoundingBox requestBounds()
    {
        return paddedBoundingBox;
    }
}