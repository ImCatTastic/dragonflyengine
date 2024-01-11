package engine.core;

import java.util.ArrayList;

public class Node<T>
{
    private final T element;
    private final ArrayList<T> children = new ArrayList<>();
    public Node(T element)
    {
        this.element = element;
    }
    public T get()
    {
        return element;
    }
    public ArrayList<T> getChildren()
    {
        return children;
    }

    public boolean isEmpty()
    {
        return children.isEmpty();
    }

    public void addChild(T childElement)
    {

    }

    public void removeChild(T childElement)
    {

    }
}
