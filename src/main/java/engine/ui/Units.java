package engine.ui;

import engine.core.Window;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;

public class Units
{
    private final static ReadOnlyDoubleWrapper literalUnitProperty = new ReadOnlyDoubleWrapper(1);
    private final static ReadOnlyDoubleWrapper actualWorldUnitProperty = new ReadOnlyDoubleWrapper(1);
    private final static ReadOnlyDoubleWrapper worldUnitProperty = new ReadOnlyDoubleWrapper(0);
    private final static ReadOnlyDoubleWrapper viewWidthUnitProperty = new ReadOnlyDoubleWrapper(0);
    private final static ReadOnlyDoubleWrapper viewHeightUnitProperty = new ReadOnlyDoubleWrapper(0);
    private final static ReadOnlyDoubleWrapper viewMinUnitProperty = new ReadOnlyDoubleWrapper(0);
    private final static ReadOnlyDoubleWrapper viewMaxUnitProperty = new ReadOnlyDoubleWrapper(0);
    private final static ReadOnlyDoubleWrapper pixelUnitProperty = new ReadOnlyDoubleWrapper(0);
    private final static ReadOnlyBooleanWrapper resizeTrigger = new ReadOnlyBooleanWrapper(false);
    static
    {
        worldUnitProperty.bind(actualWorldUnitProperty.divide(Unit.WORLD_TRANSLATION));
        Window.getDimensionProperty().addListener(x ->
        {
            updateDimensions();
            resizeTrigger.set(!resizeTrigger.get()); //invert boolean to force a chance
        });
    }
    private static void updateDimensions()
    {
        viewWidthUnitProperty.set(Window.getDimensions().x / 100d);
        viewHeightUnitProperty.set(Window.getDimensions().y / 100d);

        viewMinUnitProperty.set(Window.getDimensions().y / 100d); //Todo: fix
        viewMaxUnitProperty.set(Window.getDimensions().x / 100d); //Todo: fix
    }
    public static void setWorldUnit(double worldUnit)
    {
        //TODO: proper alignment to camera, for multiple cameras
        actualWorldUnitProperty.set(worldUnit);
    }
    public static ReadOnlyBooleanProperty getTriggerProperty()
    {
        return resizeTrigger.getReadOnlyProperty();
    }
    public static ReadOnlyDoubleProperty getLiteralUnitProperty()
    {
        return literalUnitProperty.getReadOnlyProperty();
    }
    public static ReadOnlyDoubleProperty getWorldUnitProperty()
    {
        return worldUnitProperty.getReadOnlyProperty();
    }
    public static ReadOnlyDoubleProperty getViewHeightUnitProperty()
    {
        return viewHeightUnitProperty.getReadOnlyProperty();
    }
    public static ReadOnlyDoubleProperty getViewWidthUnitProperty()
    {
        return viewWidthUnitProperty.getReadOnlyProperty();
    }
    public static ReadOnlyDoubleProperty getViewMinUnitProperty()
    {
        return viewMinUnitProperty.getReadOnlyProperty();
    }
    public static ReadOnlyDoubleProperty getViewMaxUnitProperty()
    {
        return viewMaxUnitProperty.getReadOnlyProperty();
    }
    public static ReadOnlyDoubleProperty getPixelUnitProperty()
    {
        return pixelUnitProperty.getReadOnlyProperty();
    }
}
