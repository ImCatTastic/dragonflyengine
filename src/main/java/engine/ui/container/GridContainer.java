package engine.ui.container;

import engine.ui.ScaleMode;
import engine.ui.UIComponent;

import java.util.ArrayList;

public class GridContainer
{
    private static class GridField
    {
        double value;
        ScaleMode scaleMode;
        public GridField(double value, ScaleMode scaleMode)
        {
            this.value = value;
            this.scaleMode = scaleMode;
        }
    }
    private final ArrayList<ArrayList<UIComponent>> components = new ArrayList<>();
    private final ArrayList<GridField> rows = new ArrayList<>();
    private final ArrayList<GridField> columns = new ArrayList<>();
    public void addRow(double value, ScaleMode scaleMode)
    {
        rows.add(new GridField(value, scaleMode));
    }
    public void addColumn(double value, ScaleMode scaleMode)
    {
        columns.add(new GridField(value, scaleMode));
    }
    public void addComponent(int row, int column, UIComponent component)
    {
        components.get(row).set(column, component);
    }
}
