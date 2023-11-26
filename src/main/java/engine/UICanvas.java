package engine;

import javafx.scene.layout.Pane;

public class UICanvas extends GameObject
{
    public UICanvas(double x, double y, double width, double height)
    {
        super(x, y);

        Pane pane = new Pane();
        pane.setPrefWidth(width);
        pane.setPrefHeight(height);
        pane.setLayoutX(x);
        pane.setLayoutY(y);
        setUI(pane);
    }
}
