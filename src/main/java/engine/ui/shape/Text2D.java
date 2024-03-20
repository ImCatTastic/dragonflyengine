package engine.ui.shape;

import engine.ui.TextBoundType;
import engine.ui.UIComponent;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class Text2D extends UIComponent
{
    private final DoubleProperty fontSizeProperty = new SimpleDoubleProperty(0);
    private final DoubleProperty actualFontSizeProperty = new SimpleDoubleProperty(0);
    private StringProperty textProperty = new SimpleStringProperty("Hello world\n2d");
    private final Text shape;

    private final DoubleProperty boundsX = new SimpleDoubleProperty();
    private final DoubleProperty boundsY = new SimpleDoubleProperty();
    private TextBoundType textBoundType = TextBoundType.FIT_HORIZONTALLY;

    public Text2D()
    {
        //setHorizontalAnchor(Anchor.Horizontal.CENTER);
        //setVerticalAnchor(Anchor.Vertical.CENTER);

        Font.loadFont(getClass().getResourceAsStream("/fonts/Boleh.ttf"), 1);

        shape = new Text();
        shape.textProperty().bind(textProperty);
        shape.setFont(Font.font(18));
        shape.setBoundsType(TextBoundsType.LOGICAL);

        var refText = new Text("|");
        refText.setBoundsType(TextBoundsType.VISUAL);

        Bounds bounds = refText.getBoundsInLocal();
        shape.setY(bounds.getHeight());

        calcBounds();

        shape.setStyle("-fx-font-weight: bold;");

        shape.fillProperty().bind(getUserStyle().fillProperty);

        //pivotPoint = PivotPoint.CENTER;

        /*
        shape.xProperty().bind(Bindings.createDoubleBinding(
                () -> -boundsX.get() * pivotPoint.getX(),
                boundsX
        ));

         */
        //shape.yProperty().bind(Bindings.createDoubleBinding(() -> -boundsY.get() * pivotPoint.getY()));

        var clipRect = new Rectangle(0, 0, 0, 0);
        clipRect.widthProperty().bind(getWidthProperty());
        clipRect.heightProperty().bind(getHeightProperty());
        container_background.setClip(clipRect);

        getWidthProperty().bind(boundsX);
        getHeightProperty().bind(boundsY);

        actualFontSizeProperty.bind(fontSizeProperty);
        actualFontSizeProperty.addListener(x ->
        {
            var size = ((DoubleProperty) x).get();
            var font = Font.font("Boleh", FontWeight.MEDIUM ,size);
            refText.setFont(font);
            shape.setFont(font);
            Bounds b = refText.getBoundsInLocal();
            shape.setY(b.getHeight());
            calcBounds();
        });

        addNodesBackground(shape);
    }
    public void setText(String text)
    {
        textProperty.set(text);
        calcBounds();
    }

    public void setFontSize(double value)
    {
        fontSizeProperty.set(value);
    }

    /*
    @Override
    public void setHorizontalAnchor(Anchor.Horizontal horizontalAnchor)
    {
        if(horizontalAnchor == Anchor.Horizontal.STRETCH)
            throw new UnsupportedOperationException("Stretch anchor isn't supported for Text2D");

        super.setHorizontalAnchor(horizontalAnchor);
    }

    @Override
    public void setVerticalAnchor(Anchor.Vertical verticalAnchor)
    {
        if(verticalAnchor == Anchor.Vertical.STRETCH)
            throw new UnsupportedOperationException("Stretch anchor isn't supported for Text2D");

        super.setVerticalAnchor(verticalAnchor);
    }

     */

    private void calcBounds()
    {
        Bounds actualBounds = shape.getLayoutBounds();
        boundsX.set(actualBounds.getWidth());
        boundsY.set(actualBounds.getHeight());
    }
}