package engine.javafx;

import engine.mathUtil.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Renderer
{
    //TODO: rework rendering, using camera based frustums and smart culling, aswell as improved sorting technique
    //TODO: aka, not emptying the list just to adjust one item


    private static class CameraFrustum
    {
        public final ArrayList<CanvasRenderer> visibleObjects = new ArrayList<>();
        public final HashMap<Integer, ArrayList<CanvasRenderer>> culledObjects = new HashMap<>();
        public CameraFrustum()
        {
            for (int i = 0; i < 4; i++)
                culledObjects.put(i, new ArrayList<>());
        }
    }

    private LinkedList<CanvasRenderer> canvasRenderers = new LinkedList<>();
    public Renderer()
    {
        load();
    }
    private void load()
    {
        canvasRenderers = new LinkedList<>();

        var scene = SceneManager.getActiveScene();
        scene.useBreadthFirst();
        var iterator = scene.iterator();

        while (iterator.hasNext())
        {
            var element = iterator.next();

            var renderer = element.getGameObject().getCanvasRenderer();
            if(renderer != null)
            {
                if(canvasRenderers.isEmpty())
                {
                    canvasRenderers.add(renderer);
                }

                else
                {
                    boolean inserted = false;
                    for (int i = 0; i < canvasRenderers.size(); i++)
                    {
                        if(element.getPosition().z < canvasRenderers.get(i).getObjectTransform().getPosition().z)
                        {
                            canvasRenderers.add(i, renderer);
                            inserted = true;
                            break;
                        }
                    }

                    if(!inserted)
                        canvasRenderers.addLast(renderer);
                }
            }
        }
    }

    public void draw()
    {
        var scene = SceneManager.getActiveScene();
        if(scene.requireOrderUpdate())
            load();

        GraphicsContext gc;
        var cameras = CameraManager.getCamerasFromScene(scene);
        for (Camera cam : cameras)
        {
            if(!cam.isEnabled())
                continue;

            gc = cam.getGraphicsContext();
            gc.save();
            gc.clearRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

            var viewMatrix = cam.getViewMatrix();
            for (CanvasRenderer renderer : canvasRenderers)
                renderer.doRender(gc, viewMatrix);

            gc.restore();
        }
    }
}
