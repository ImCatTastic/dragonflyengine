package engine.rendering;

import engine.core.Transform2D;
import engine.util.RenderData;
import javafx.scene.canvas.GraphicsContext;

import java.util.function.BiConsumer;

public final class CustomRenderer extends RenderComponent
{
    public CustomRenderer(Transform2D transform)
    {
        super(transform);
    }
    private BiConsumer<GraphicsContext, RenderData> drawMethod = (gc, data) -> {};
    public void setOnDraw(BiConsumer<GraphicsContext, RenderData> method)
    {
        drawMethod = method;
    }
    @Override
    protected void render(GraphicsContext gc, RenderData renderData)
    {
        drawMethod.accept(gc, renderData);
    }
}
