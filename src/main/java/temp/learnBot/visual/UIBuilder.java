package temp.learnBot.visual;

import engine.ui.Anchor;
import engine.ui.UICanvas;
import engine.ui.UIComponent;
import engine.ui.shape.Rectangle2D;
import engine.ui.shape.Text2D;
import engine.util.PivotPoint;
import javafx.scene.paint.Color;

public class UIBuilder
{
    private final UICanvas canvas;
    private final double offset = 25;
    private final double fullSize = 1000;
    public UIBuilder(UICanvas canvas)
    {
        this.canvas = canvas;
        buildTop();
    }

    private void buildTop()
    {
        /*
        var paneTop = new Rectangle2D(fullSize, 120);
        paneTop.setPivotPoint(PivotPoint.BOTTOM_CENTER);
        paneTop.setBottom(500 + offset);
        paneTop.setCornersRadius(25);
        paneTop.setFill(Color.rgb(41, 45, 65));
        canvas.add(paneTop);


        buildScoreBoard(paneTop);

         */
    }

    private void buildScoreBoard(UIComponent parent)
    {
        /*
        Rectangle2D left = new Rectangle2D();
        Rectangle2D right = new Rectangle2D();

        left.setFill(Color.rgb(0, 176, 255));
        left.setRight(fullSize * 0.5);
        left.setHeight(70);
        left.setWidth(400);
        left.setHorizontalAnchor(Anchor.Horizontal.RIGHT);
        left.setVerticalAnchor(Anchor.Vertical.CENTER);
        left.setTopLeftCornerRadius(15);
        left.setBottomLeftCornerRadius(15);
        parent.add(left);

        var counter1 = new Text2D();
        counter1.setHorizontalAnchor(Anchor.Horizontal.LEFT);
        counter1.setLeft(25);
        counter1.setTop(5);
        counter1.setFontSize(42);
        counter1.setFill(Color.WHITE);
        counter1.setText("75.0%");
        left.add(counter1);


        right.setFill(Color.rgb(255, 74, 74));
        right.setLeft(fullSize * 0.5);
        right.setHeight(70);
        right.setWidth(300);
        right.setHorizontalAnchor(Anchor.Horizontal.LEFT);
        right.setVerticalAnchor(Anchor.Vertical.CENTER);
        right.setTopRightCornerRadius(15);
        right.setBottomRightCornerRadius(15);
        parent.add(right);

        var counter2 = new Text2D();
        counter2.setHorizontalAnchor(Anchor.Horizontal.RIGHT);
        counter2.setRight(25);
        counter2.setFontSize(42);
        counter2.setFill(Color.WHITE);
        counter2.setText("50.0%");
        right.add(counter2);

        var bar = new Rectangle2D();
        bar.setFill(Color.WHITE);
        bar.setHeight(80);
        bar.setWidth(10);
        bar.setCornersRadius(75);
        parent.add(bar);

         */
    }
}