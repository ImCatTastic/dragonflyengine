package engine.javafx.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNode<T>
{
    private final T value;
    private final ArrayList<TreeNode<T>> children = new ArrayList<>();
    private final List<TreeNode<T>> childView = Collections.unmodifiableList(children);
    public TreeNode(T value)
    {
        this.value = value;
    }
    public T getValue()
    {
        return value;
    }
    public List<TreeNode<T>> getChildren()
    {
        return childView;
    }
    public void addChild(TreeNode<T> node)
    {
        children.add(node);
    }
    public void addChild(T value)
    {
        addChild(new TreeNode<>(value));
    }
    public void removeChild(TreeNode<T> node)
    {
        children.remove(node);
    }
}
