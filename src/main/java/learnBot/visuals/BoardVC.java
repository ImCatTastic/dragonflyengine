package learnBot.visuals;

import engine.core.GameObject;
import engine.mathUtil.Vec2;
import engine.rendering.CanvasRenderer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BoardVC extends GameObject
{
    public BoardVC(int width, int height, double borderSize)
    {
        super(Config.MARGIN, Config.MARGIN, 0);

        setVisualComponent(new CanvasRenderer(this, new Vec2(width + (width + 1) * borderSize, height + (height + 1) * borderSize))
        {
            @Override
            public void onRender(GraphicsContext gc, Vec2 position, Vec2 dimensions)
            {
                gc.setFill(new Color(64/255d,64/255d,64/255d,1));
                gc.fillRect(position.x, position.y, dimensions.x, dimensions.y);
            }
        });
    }
}
