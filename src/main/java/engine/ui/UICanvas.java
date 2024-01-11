package engine.ui;

import engine.core.Engine;
import engine.core.GameObject;
import javafx.scene.layout.Pane;

public class UICanvas extends GameObject
{
    public UICanvas(double x, double y, double width, double height)
    {
        super(x, y);

        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: blue");

        //bindH(width, pane.prefWidthProperty());
        //bindH(height, pane.prefHeightProperty());

        pane.setLayoutX(x);
        pane.setLayoutY(y);
        setUI(pane);
    }
}
