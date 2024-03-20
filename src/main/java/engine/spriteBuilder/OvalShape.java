package engine.spriteBuilder;

import engine.core.BoundingBox;
import engine.util.RenderData;
import engine.util.math.Vec2;
import javafx.scene.canvas.GraphicsContext;

public class OvalShape extends PaintableShape
{
    private final double x;
    private final double y;
    private final double width;
    private final double height;
    public OvalShape(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        boundingBox = new BoundingBox(x, y, width, height);
    }
    private PaintData fillData;
    private PaintData strokeData;
    private double tmpUnit = 0;
    @Override
    public void draw(GraphicsContext gc, RenderData data, Vec2 dimensions)
    {
        var unit = data.unit / SpriteBuilder.getModifier();
        if(dirty || unit != tmpUnit)
        {
            var scaledX = x * unit;
            var scaledY = y * unit;
            var scaledRadX = width * unit;
            var scaledRadY = height * unit;

            fillData = new PaintData(
                    scaledX - data.pivotOffset.x + data.dimensions.x * getAlignment().x - boundingBox.width * unit * getAlignment().x,
                    scaledY - data.pivotOffset.y + data.dimensions.y * getAlignment().y - boundingBox.height * unit * getAlignment().y,
                    scaledRadX, scaledRadY
            );

            var borderDisplacement = getStrokeThickness() * unit * getStrokeType().displacementFactor;
            strokeData = new PaintData(
                    fillData.x - borderDisplacement,
                    fillData.y - borderDisplacement,
                    fillData.width + borderDisplacement * 2,
                    fillData.height + borderDisplacement * 2
            );

            tmpUnit = unit;
            dirty = false;
        }

        if(getFill() != null)
        {
            gc.setFill(getFill());
            gc.fillOval(fillData.x, fillData.y, fillData.width, fillData.height);
        }

        if(getStroke() != null && getStrokeThickness() != 0)
        {
            gc.setStroke(getStroke());
            gc.setLineWidth(getStrokeThickness() * unit);
            gc.strokeOval(strokeData.x, strokeData.y, strokeData.width, strokeData.height);
        }
    }
    private record PaintData(double x, double y, double width, double height) { }
}
