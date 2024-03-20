package engine.ui.shape;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TextField2D extends Rectangle2D
{
    private final DoubleProperty paddingLeftProperty = new SimpleDoubleProperty(0);
    private final DoubleProperty paddingRightProperty = new SimpleDoubleProperty(0);
    private final DoubleProperty paddingTopProperty = new SimpleDoubleProperty(0);
    private final DoubleProperty paddingBottomProperty = new SimpleDoubleProperty(0);
    private final DoubleProperty fontSizeProperty = new SimpleDoubleProperty(0);
    private final TextField shape;
    public TextField2D()
    {
        shape = new TextField();
        shape.setAlignment(Pos.CENTER);
        shape.prefWidthProperty().bind(getWidthProperty());
        shape.prefHeightProperty().bind(getHeightProperty());

        shape.paddingProperty().bind(Bindings.createObjectBinding(() -> new Insets(paddingTopProperty.get(), paddingRightProperty.get(),
                                                                                   paddingBottomProperty.get(), paddingLeftProperty.get()),
                                                                  paddingTopProperty, paddingLeftProperty,
                                                                  paddingBottomProperty, paddingRightProperty));


        //shape.setAlignment(Pos.CENTER);

        shape.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-border-color: transparent; " +
                        "-fx-text-fill: rgb(255, 255, 255); " +
                        "-fx-padding: 0;" +
                        "-fx-min-height: 0;"
        );

        var actualFontSizeProperty = new SimpleDoubleProperty(0);
        actualFontSizeProperty.bind(fontSizeProperty);
        actualFontSizeProperty.addListener(x ->
                                           {
                                               var size = ((DoubleProperty) x).get();
                                               var font = Font.font("Boleh", FontWeight.MEDIUM ,size);
                                               shape.setFont(font);
                                           });

        shape.setFont(Font.font("Boleh", FontWeight.MEDIUM , 18));

        addNodesBackground(shape);
    }
    public String getContent()
    {
        return shape.getText();
    }
    public void setFontSize(double value)
    {
        fontSizeProperty.set(value);
    }
    public void setPaddingLeft(double value)
    {
        paddingLeftProperty.set(value);
    }
    public void setPaddingRight(double value)
    {
        paddingRightProperty.set(value);
    }
    public void setPaddingTop(double value)
    {
        paddingTopProperty.set(value);
    }
    public void setPaddingBottom(double value)
    {
        paddingBottomProperty.set(value);
    }
    public void setTextFormatter(TextFormatter<String> formatter)
    {
        shape.setTextFormatter(formatter);
    }
}