package engine.shapePainter;

import engine.util.RenderData;
import engine.util.math.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class TextShape extends PaintableShape
{
    private String text;
    private double fontSize;
    public TextShape(String text, double fontSize)
    {
        font = Font.loadFont(getClass().getResourceAsStream("/fonts/Minecraft.ttf"), 28);
        this.fontSize = fontSize;
        setText(text);
    }
    private Font font;
    public void setFontSize(double fontSize)
    {
        this.fontSize = fontSize;
    }
    public void setText(String text)
    {
        this.text = text;
    }
    private Vec2 getBounds(Font font)
    {
        Text txt = new Text(text);
        txt.setFont(font);
        txt.setBoundsType(TextBoundsType.VISUAL);
        return new Vec2(txt.getLayoutBounds().getWidth(), txt.getBoundsInLocal().getHeight());
    }
    @Override
    public void draw(GraphicsContext gc, RenderData data, Vec2 dimensions)
    {
        Font _font = new Font(font.getName(), fontSize * data.unit);

        gc.setFont(_font);
        gc.setFill(Color.WHITE);

        var bounds = getBounds(_font);
        var x = (pivotPoint.getX() - data.pivotPoint.getX()) * data.dimensions.x;
        var y = (pivotPoint.getY() - data.pivotPoint.getY()) * data.dimensions.y;
        var xOff = pivotPoint.getX() * bounds.x; //offset relative to it's own size and pivotPoint
        var yOff = pivotPoint.getY() * bounds.y; //offset relative to it's own size and pivotPoint
        var posX = position.x * data.unit; //position relative to the gameObject
        var posY = position.y * data.unit; //position relative to the gameObject

        gc.setFill(Color.WHITE);
        gc.fillText(text, x - xOff + posX, bounds.y + y - yOff + posY);
    }
}