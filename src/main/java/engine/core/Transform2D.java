package engine.core;

import engine.util.math.Vec2;
import engine.util.math.Vec3;
import javafx.scene.transform.Affine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public final class Transform2D implements Iterable<Transform2D>
{
    private GameScene scene;
    private final GameObject gameObject;
    private final ArrayList<Transform2D> children = new ArrayList<>();
    public ArrayList<Transform2D> getChildren()
    {
        return children;
    }
    private Transform2D parent;
    private boolean dirty = true;
    private boolean wasDirty = true;
    private Vec3 position = new Vec3(0, 0, 0);
    private Vec2 scale = new Vec2(1, 1);
    private double rotation = 0;
    private final Affine modelMatrix = new Affine();
    Transform2D(GameObject gameObject)
    {
        this.gameObject = gameObject;
    }
    Transform2D(GameObject gameObject, GameScene gameScene)
    {
        this.gameObject = gameObject;
        this.scene = gameScene;
    }
    public int size()
    {
        int out = 1;
        for (Transform2D child : children)
            out += child.size();

        return out;
    }
    public GameScene getScene()
    {
        return scene;
    }
    public Vec3 getPosition()
    {
        return position;
    }
    public double getWorldZ()
    {
        if(parent != null)
            return parent.getWorldZ() + position.z;

        return position.z;
    }
    public double getLocalZ()
    {
        return position.z;
    }
    public Vec2 getScale()
    {
        return scale;
    }
    public double getRotation()
    {
        return rotation;
    }
    public Affine getModelMatrix()
    {
        return modelMatrix;
    }
    public GameObject getGameObject()
    {
        return gameObject;
    }
    public Transform2D getParent()
    {
        return parent;
    }
    public boolean wasDirty()
    {
        return wasDirty && !(wasDirty = false);
    }
    public void setParent(@Nullable Transform2D parent)
    {
        if(this.parent != null)
        {
            this.parent.children.remove(this);
            if(gameObject instanceof Camera camera)
                CameraManager.removeCamera(camera);

            if(scene != null)
                scene.requestROrderUpdate();
        }

        if(parent != null)
        {
            this.parent = parent;
            setScene(parent.scene);
            parent.children.add(this);

            if(gameObject instanceof Camera camera)
                CameraManager.addCamera(camera);

            markDirty();

            if(scene != null)
                scene.requestROrderUpdate();
        }

        else
        {
            this.parent = null;
            setScene(null);
        }
    }
    private void setScene(GameScene scene)
    {
        this.scene = scene;
        for (Transform2D child : children)
            child.setScene(scene);

        if(scene != null)
            scene.requestROrderUpdate();
    }
    public void setX(double x)
    {
        position = new Vec3(x, position.y, position.z);
        markDirty();
    }
    public void setY(double y)
    {
        position = new Vec3(position.x, y, position.z);
        markDirty();
    }
    public void setZ(double z)
    {
        position = new Vec3(position.x, position.y, z);
        markDirty();

        if(scene != null)
            scene.requestROrderUpdate();
    }
    public void setPosition(double x, double y)
    {
        position = new Vec3(x, y, 0);
        markDirty();
    }
    public void setPosition(double x, double y, double z)
    {
        if(position.z != z && scene != null)
            scene.requestROrderUpdate();

        position = new Vec3(x, y, z);
        markDirty();
    }
    public void setPosition(Vec2 position)
    {
        setPosition(position.x, position.y, 0);
    }
    public void setPosition(Vec3 position)
    {
        setPosition(position.x, position.y, position.z);
    }
    public void setScaleX(double x)
    {
        scale = new Vec2(x, scale.y);
        markDirty();
    }
    public void setScaleY(double y)
    {
        scale = new Vec2(scale.x, y);
        markDirty();
    }
    public void setScale(double v)
    {
        scale = new Vec2(v, v);
        markDirty();
    }
    public void setScale(double x, double y)
    {
        scale = new Vec2(x, y);
        markDirty();
    }
    public void setScale(Vec2 position)
    {
        setScale(position.x, position.y);
    }
    public void setRotation(double rotation)
    {
        this.rotation = rotation;
        markDirty();
    }
    public void modRotation(double value, ModOperation operation)
    {
        switch (operation)
        {
            case ADD -> this.rotation += value;
            case SUBTRACT -> this.rotation -= value;
            case MULTIPLY -> this.rotation *= value;
            case DIVIDE -> this.rotation /= value;
            case MODULO -> this.rotation %= value;
        }

        markDirty();
    }
    public void update()
    {
        if(dirty)
        {
            wasDirty = true;
            calculateModelMatrix();
            dirty = false;
        }
    }
    private void markDirty()
    {
        dirty = true;
        children.forEach(Transform2D::markDirty);
    }
    private void calculateModelMatrix()
    {
        if(parent != null)
            modelMatrix.setToTransform(parent.modelMatrix);
        else
            modelMatrix.setToIdentity();

        modelMatrix.appendTranslation(position.x, -position.y);
        modelMatrix.appendRotation(rotation);
        modelMatrix.appendScale(scale.x, scale.y);
    }
    private boolean breadthFirstEnabled = false;
    public void useBreadthFirst()
    {
        breadthFirstEnabled = true;
    }
    @NotNull
    @Override
    public Iterator<Transform2D> iterator()
    {
        final Transform2D root = this;

        if(breadthFirstEnabled)
        {
            breadthFirstEnabled = false;
            return breadthFirstIterator(root);
        }
        return new Iterator<>()
        {
            private final ArrayDeque<Transform2D> stack = new ArrayDeque<>(64);
            {
                stack.push(root);
            }
            @Override
            public boolean hasNext()
            {
                return !stack.isEmpty();
            }
            @Override
            public Transform2D next()
            {
                if (!hasNext()) throw new NoSuchElementException();

                Transform2D currentTransform = stack.pop();
                List<Transform2D> children = currentTransform.getChildren();

                for (int i = children.size() - 1; i >= 0; i--)
                    stack.push(children.get(i));

                return currentTransform;
            }
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException("Remove operation is not supported.");
            }
        };
    }

    @NotNull
    private Iterator<Transform2D> breadthFirstIterator(final Transform2D root)
    {
        return new Iterator<>()
        {
            private final ArrayDeque<Transform2D> deque = new ArrayDeque<>(64);
            {
                deque.add(root);
            }
            @Override
            public boolean hasNext()
            {
                return !deque.isEmpty();
            }
            @Override
            public Transform2D next()
            {
                if (!hasNext()) throw new NoSuchElementException();

                Transform2D element = deque.pop();
                for (Transform2D child : element.getChildren())
                    deque.addLast(child);

                return element;
            }
            @Override
            public void remove()
            {
                throw new UnsupportedOperationException("Remove operation is not supported.");
            }
        };
    }
}