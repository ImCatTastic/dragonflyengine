package engine.javafx.shapePainter;

import engine.javafx.Camera;
import engine.javafx.CanvasRenderer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class TextShape extends PaintableShape
{
    private String text;
    private double fontSize;
    private double width;
    private double height;
    public TextShape(String text, double fontSize)
    {
        this.fontSize = fontSize;
        setText(text);
    }
    private Font font;
    public void setText(String text)
    {
        Text txt = new Text(text);
        //font = Font.font("Arial", FontWeight.BOLD, fontSize);

        font = Font.loadFont(getClass().getResourceAsStream("/fonts/Minecraft.ttf"), 24);

        txt.setFont(font);
        txt.setBoundsType(TextBoundsType.VISUAL);
        width = txt.getLayoutBounds().getWidth();
        height = txt.getBoundsInLocal().getHeight();
        this.text = text;
    }
    @Override
    public void draw(GraphicsContext gc, CanvasRenderer renderer)
    {
        gc.setFont(font);
        gc.setFill(Color.WHITE);

        var dimensions = renderer.getDimensions();
        var x = (alignment.getX() - renderer.getPivotOrientation().getX()) * dimensions.x;
        var y = (alignment.getY() - renderer.getPivotOrientation().getX()) * dimensions.y;
        var xOff = alignment.getX() * width;
        var yOff = alignment.getY() * height;

        var posX = position.x * Camera.getUnit();
        var posY = position.y * Camera.getUnit();

        gc.fillText(text, x - xOff + posX, height + y - yOff + posY);
    }
}
