package engine.geometry;

import engine.buffering.Matrix4fBuffer;
import engine.mathUtil.Vec2;
import org.jetbrains.annotations.NotNull;
import org.joml.*;

import java.nio.FloatBuffer;

public class Transform2D
{
    public final int zIndex;
    private final Vector2f position = new Vector2f();
    private float rotation;
    private final Vector2f scale = new Vector2f();
    private int bufferIndex = -1;
    private FloatBuffer buffer;
    private boolean dirty = true;
    public Transform2D(int zIndex)
    {
        this.zIndex = zIndex;
    }
    public Transform2D()
    {
        this.zIndex = 0;
    }
    public int setBuffer(Matrix4fBuffer buffer)
    {
        if(bufferIndex == -1)
        {
            this.bufferIndex = buffer.getNewMatrixLocation();
            this.buffer = buffer.getBuffer();
        }
        return bufferIndex;
    }
    public Vector2f getPosition()
    {
        return position;
    }

    public double getRotation() {
        return rotation;
    }

    public Vector2f getScale() {
        return scale;
    }

    public int getzIndex() {
        return zIndex;
    }

    public void setRotation(float rotation)
    {
        this.rotation = rotation;
        dirty = true;
    }
    public void setPosition(@NotNull Vector2f position)
    {
        this.position.x = position.x;
        this.position.y = position.y;
        dirty = true;
    }
    public void setPosition(float x, float y)
    {
        this.position.x = x;
        this.position.y = y;
        position.set(x, y);
        dirty = true;
    }
    public void setScale(float x, float y)
    {
        scale.x = x;
        scale.y = y;
        dirty = true;
    }
    public void setScale(@NotNull Vector2f scale)
    {
        scale.set(scale);
        dirty = true;
    }
    public void getTransformationMatrix()
    {
        if(dirty)
        {
            dirty = false;
        }
    }
    public void setBufferIndex(int bufferIndex)
    {
        this.bufferIndex = bufferIndex;
    }
    private void updateMatrix()
    {
        var mat = new Matrix4f().identity();
        mat.translate(position.x, position.y, 0.0f);
        mat.rotate(rotation, 0, 0, 0);
        mat.scale(scale.x, scale.y, 1);
        mat.get(bufferIndex, buffer);
    }
}
