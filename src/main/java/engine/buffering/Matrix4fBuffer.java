package engine.buffering;

import java.nio.FloatBuffer;

public class Matrix4fBuffer
{
    private int index = 0;
    private FloatBuffer buffer;

    public Matrix4fBuffer(int initialCapacity)
    {
        buffer = FloatBuffer.allocate(initialCapacity * 16);
    }
    public int getCount()
    {
        return index;
    }
    public FloatBuffer getBuffer()
    {
        return buffer;
    }
    public int getNewMatrixLocation()
    {
        index += 16;
        if(index > buffer.capacity())
            relocate();
        return index;
    }

    private void relocate()
    {
        FloatBuffer newBuffer = FloatBuffer.allocate(buffer.capacity() * 2);
        newBuffer.put(buffer);
        newBuffer.flip();
        buffer = newBuffer;
    }
}
