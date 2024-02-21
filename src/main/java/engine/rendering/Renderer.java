package engine.rendering;

import engine.core.GameObject;
import engine.core.SceneManager;
import engine.core.View;
import engine.util.RenderData;
import engine.util.Tuple;
import javafx.beans.property.*;
import javafx.scene.transform.Affine;

import java.util.*;

public class Renderer
{
    private Renderer() {}
    private final static ReadOnlyDoubleWrapper unitProperty = new ReadOnlyDoubleWrapper(1);
    public static ReadOnlyDoubleProperty getUnitProperty()
    {
        return unitProperty.getReadOnlyProperty();
    }

    //TODO: implement scene management and culling for scenes, aswell as smarter updates, aka not recalculating the tree every time
    private final static Comparator<Tuple<Double, Integer>> comparator = (t1, t2) ->
    {
        int zPosCheck = t1.first.compareTo(t2.first);
        if (zPosCheck != 0)
            return zPosCheck;
        else
            return t1.second.compareTo(t2.second);
    };
    private static TreeMap<Tuple<Double, Integer>, GameObject> heap = new TreeMap<>(comparator);
    private static void load()
    {
        heap = new TreeMap<>(comparator);
        var scene = SceneManager.getActiveScene();
        scene.useBreadthFirst();
        var iterator = scene.iterator();

        int insertionIndex = 0;
        while (iterator.hasNext())
        {
            var t2d = iterator.next();

            var renderer = t2d.getGameObject().getCanvasRenderer();
            if(renderer != null)
            {
                heap.put(new Tuple<>(t2d.getPosition().z, insertionIndex++), t2d.getGameObject());
            }
        }
    }
    public static void draw()
    {
        var scene = SceneManager.getActiveScene();
        if(scene.requireOrderUpdate())
            load();

        var views = View.getUsableViews();
        for (View view : views)
        {
            var gc = view.getGraphicsContext();
            var cam = view.getActiveCamera();
            var unit = cam.convertToUnit(view.getWidth(), view.getHeight(), view.getZoom());

            scene.getUiCanvas().resize(unit);

            gc.save();
            gc.clearRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

            for (var gameObject : heap.values())
            {
                //TODO: optimize storing transform
                var renderer = gameObject.getCanvasRenderer();
                var modelMatrix = new Affine(gameObject.getTransform().getModelMatrix());
                var viewMatrix = cam.getTransform().getModelMatrix();
                modelMatrix.setTx(modelMatrix.getTx() * unit);
                modelMatrix.setTy(modelMatrix.getTy() * unit);
                Affine mvpMatrix = new Affine();
                mvpMatrix.appendTranslation(view.getWidth() * 0.5, view.getHeight() * 0.5);
                mvpMatrix.append(viewMatrix);
                mvpMatrix.append(modelMatrix);
                if(renderer.isFlipX() || renderer.isFlipY())
                    mvpMatrix.appendScale(renderer.isFlipX() ? -1 : 1, renderer.isFlipY() ? -1 : 1);

                gc.setTransform(mvpMatrix);
                var data = new RenderData(renderer.getDimensions(), renderer.getPivotPoint(), unit);
                renderer.render(gc, data);
            }
            gc.restore();
        }
    }
}