package engine.ui.container;

import engine.ui.UIComponent;

import java.util.ArrayList;

public class StackContainer
{
    private final boolean isVertical;
    private final ArrayList<UIComponent> components = new ArrayList<>();
    public StackContainer(boolean isVertical)
    {
        this.isVertical = isVertical;
    }
    public void addComponent(UIComponent component)
    {
        components.add(component);
    }
    public void removeComponent(UIComponent component)
    {
        components.remove(component);
    }
}