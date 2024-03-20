package engine.rendering;

import engine.core.BoundingBox;
import engine.core.FontFamily;
import engine.core.Transform2D;
import engine.util.PivotPoint;
import engine.util.RenderData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public final class TextRenderer extends RenderComponent
{
    private double fontSize = 0.5d;
    private String text = "-";
    private FontFamily fontFamily = FontFamily.ARIAL;
    private FontWeight fontWeight  = FontWeight.NORMAL;
    private boolean italic = false;
    public TextRenderer(Transform2D transform)
    {
        super(transform);
        font = Font.loadFont(getClass().getResourceAsStream("/fonts/Odin.otf"), 1);
        int i = 0;
    }
    public void setText(String text)
    {
        this.text = text;
    }
    public void setFontSize(double value)
    {
        fontSize = value;
    }
    public void setItalic(boolean value)
    {
        italic = value;
    }
    @Override
    public void setPivotPoint(PivotPoint pivotPoint)
    {
        super.setPivotPoint(pivotPoint);
        //calcBounds();
    }

    private void calcBounds(double unit)
    {
        font = Font.font("Odin Rounded", fontWeight, italic ? FontPosture.ITALIC : FontPosture.REGULAR, fontSize * unit);
        Text t1 = new Text("E");
        t1.setFont(font);
        t1.setBoundsType(TextBoundsType.VISUAL);

        Text t2 = new Text(text);
        t2.setFont(font);
        t2.setBoundsType(TextBoundsType.LOGICAL);

        var bounds1 = t1.getLayoutBounds();
        var bounds2 = t2.getLayoutBounds();

        var pp = getPivotPoint();

        boundingBox = new BoundingBox(
                -bounds2.getWidth() * pp.getX(),
                bounds1.getHeight() - bounds1.getHeight() * pp.getY(),
                bounds2.getWidth(),
                bounds1.getHeight()
        );
    }
    private Font font;
    @Override
    protected void render(GraphicsContext gc, RenderData data)
    {
        calcBounds(data.unit);

        //gc.setFill(Color.rgb(254, 218, 0));
        gc.setFill(Color.WHITE);
        gc.setFont(font);
        gc.fillText(text, boundingBox.minX, boundingBox.minY);
    }
}