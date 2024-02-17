package engine.javafx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CameraManager
{
    private final static HashMap<GameScene, ArrayList<Camera>> cameras = new HashMap<>();
    static void rescale(double horizontalFactor, double verticalFactor)
    {
        /*
        for (Camera camera : cameras)
        {
            if(!camera.hasFixedSize())
                camera.setScale(horizontalFactor, verticalFactor);
        }
         */
    }
    static void addCamera(Camera camera)
    {
        var scn = camera.getTransform().getScene();
        if(!cameras.containsKey(scn))
            cameras.put(scn, new ArrayList<>());

        cameras.get(scn).add(camera);
    }
    static void removeCamera(Camera camera)
    {
        cameras.get(camera.getTransform().getScene()).remove(camera);
    }
    public static List<Camera> getAllCameras()
    {
        return cameras.values().stream()
            .flatMap(ArrayList::stream)
            .collect(Collectors.toList());
    }
    public static List<Camera> getCamerasFromScene(GameScene gameScene)
    {
        return cameras.get(gameScene);
    }
}