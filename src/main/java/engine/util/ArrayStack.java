package engine.util;

import engine.javafx.Transform2D;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ArrayStack<T> implements Iterable<T>
{
    private Object[] stack;
    private int ptr;
    public ArrayStack(int stackSize)
    {
        this.stack = new Object[stackSize];
    }
    public void push(T element)
    {
        if (ptr < stack.length)
            stack[ptr++] = element;
        else
            System.out.println("Stack overflow");
    }

    @SuppressWarnings("unchecked")
    public T pop()
    {
        if (ptr > 0)
            return (T) stack[--ptr];
        else
        {
            System.out.println("Stack underflow");
            return null;
        }
    }

    @NotNull
    public Iterator<T> iterator()
    {
        return new Iterator<>()
        {
            @Override
            public boolean hasNext()
            {
                return ptr > 0;
            }
            @Override
            public T next()
            {
                if (!hasNext()) throw new NoSuchElementException();
                return pop();
            }
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException("Remove operation is not supported.");
            }
        };
    }
}
