package engine.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class NodeTree<T> implements Iterable<T>
{
    private final ArrayList<Node<T>> nodeCatalog = new ArrayList<>();
    private final Node<T> root;
    public NodeTree(Node<T> root)
    {
        this.root = root;
        nodeCatalog.add(root);
    }
    public NodeTree(T rootElement)
    {
        this.root = new Node<>(rootElement);
        nodeCatalog.add(root);
    }
    public Node<T> getByIndex(int i)
    {
        return nodeCatalog.get(i);
    }

    public Node<T> getByElement(T element)
    {
        return nodeCatalog.stream().filter(node -> node.equals(element)).findFirst().orElse(null);
    }


    @Override
    public Iterator<T> iterator()
    {
        return null;
    }
    @Override
    public void forEach(Consumer<? super T> action)
    {
        Iterable.super.forEach(action);
    }

    // Inner class representing the iterator
    private class IteratorDef implements Iterator<T>
    {
        private int index;
        Node<T> head = root;

        @Override
        public boolean hasNext()
        {
            var children = head.getChildren();
            return children != null && children.size() > index;
        }

        @Override
        public T next()
        {
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            return head.getChildren().get(index++);
        }
    }
}
