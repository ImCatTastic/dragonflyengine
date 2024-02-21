package engine.ui;

import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class UICanvas
{
    private final Pane root;
    private final UIComponent rootComponent;
    private final ReadOnlyDoubleWrapper worldUnitProperty = new ReadOnlyDoubleWrapper(1);
    private final ReadOnlyDoubleWrapper referenceUnitProperty = new ReadOnlyDoubleWrapper(100);
    private final ReadOnlyDoubleWrapper unitProperty = new ReadOnlyDoubleWrapper(0);
    public UICanvas(final Pane root)
    {
        this.root = root;
        rootComponent = new UIComponent(this, root) {};
        root.getChildren().add(rootComponent.getContainer());
        unitProperty.bind(worldUnitProperty.divide(referenceUnitProperty));
    }
    public double getReferenceUnit()
    {
        return referenceUnitProperty.get();
    }
    public ReadOnlyDoubleProperty getUnitProperty()
    {
        return unitProperty.getReadOnlyProperty();
    }
    public void setReferenceUnit(double referenceUnit)
    {
        referenceUnitProperty.set(referenceUnit);
    }
    public void resize(double unit)
    {
        worldUnitProperty.set(unit);
    }
    public void add(UIComponent component)
    {
        component.setParent(rootComponent);
    }
    public void remove(UIComponent component)
    {
        //component.setParent(root);
    }
}
