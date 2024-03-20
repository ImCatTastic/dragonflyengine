package temp.learnBot;

import engine.ui.*;
import engine.ui.shape.Line2D;
import engine.ui.shape.Rectangle2D;
import engine.ui.shape.Text2D;
import javafx.scene.paint.Color;

public class Visuals
{
    public static class GridSelector
    {
        //public final Text2D title;
        //public final Line2D line;
        //public final GridContainer grid;

        private static boolean first = true;

                    /*
        public GridSelector(StackContainer container, int columns, int rows)
        {
            var borderColor = Color.rgb(0, 176, 255);

            title = new Text2D();
            title.setText("TEXT");
            title.setFill(borderColor);
            title.setHorizontalAnchor(Anchor.Horizontal.LEFT);
            title.setTop(first ? 0 : 35);
            title.setFontSize(36);
            container.add(title);

            first = false;

            line = new Line2D();
            line.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
            line.setStrokeColor(borderColor);
            line.setHeight(5);
            container.add(line);

            var gap = 25;

            grid = new GridContainer();
            grid.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
            grid.setVerticalAnchor(Anchor.Vertical.TOP);
            grid.setTop(20);
            grid.setHeightBinding(grid.widthProperty().subtract(gap * (columns - 1)).multiply((double) rows / columns).add(gap * (rows - 1)));
            grid.addColumns(1, UnitType.RELATIVE, columns);
            grid.addRows(rows, UnitType.RELATIVE, rows);
            grid.setGap(25);
            container.add(grid);

            for (int r = 0; r < rows; r++)
            {
                for (int c = 0; c < columns; c++)
                {
                    var rect = new Rectangle2D();
                    rect.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
                    rect.setVerticalAnchor(Anchor.Vertical.STRETCH);
                    rect.setFill(null);
                    rect.setStrokeColor(borderColor);
                    rect.setStrokeThickness(2);
                    rect.setCornersRadius(5);
                    grid.add(rect, c, r, c, r);
                }
            }
        }*/
    }
}