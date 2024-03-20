package engine.ui.layout;

import engine.ui.*;
import engine.util.Tuple;
import engine.util.math.IVec2;
import engine.util.math.IVec4;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class GridLayout extends UILayout
{
    private final ArrayList<DoubleExpression> gridRows = new ArrayList<>();
    private final ArrayList<DoubleExpression> gridColumns = new ArrayList<>();

    private final DoubleProperty horizontalFractionalUnits = new SimpleDoubleProperty();
    private final DoubleProperty verticalFractionalUnits = new SimpleDoubleProperty();
    private final DoubleProperty horizontalUsedSpace = new SimpleDoubleProperty();
    private final DoubleProperty verticalUsedSpace = new SimpleDoubleProperty();


    private final ObjectProperty<ArrayList<Tuple<Double, Unit>>> columnProperty;
    private final ObjectProperty<ArrayList<Tuple<Double, Unit>>> rowProperty;


    public void clear()
    {
        columnProperty.addListener(x ->
        {
            if(parent != null && parent.userStyle.layoutTypeProperty.get() == LayoutType.GRID)
                parent.getGridBounds(this);
        });
        rowProperty.addListener(x ->
        {
            if(parent != null && parent.userStyle.layoutTypeProperty.get() == LayoutType.GRID)
                parent.getGridBounds(this);
        });
    }


    public GridLayout(ObjectProperty<ArrayList<Tuple<Double, Unit>>> columnProperty,
                      ObjectProperty<ArrayList<Tuple<Double, Unit>>> rowProperty
    )
    {
        this.columnProperty = columnProperty;
        this.rowProperty = rowProperty;

        horizontalFractionalUnits.bind(Bindings.createDoubleBinding(() ->
        {
            var cols = columnProperty.get();
            if(cols == null)
                return 1d;

            double out = 0;
            for (var col : cols)
                if(col.second == Unit.FRACTIONAL)
                    out += col.first;

            return out;
        }, columnProperty));

        verticalFractionalUnits.bind(Bindings.createDoubleBinding(() ->
        {
            var rows = rowProperty.get();
            if(rows == null)
                return 1d;

            double out = 0;
            for (var row : rows)
                if(row.second == Unit.FRACTIONAL)
                    out += row.first;

            return out;
        }, rowProperty));

        horizontalUsedSpace.bind(Bindings.createDoubleBinding(() ->
        {
            var cols = columnProperty.get();
            if(cols == null)
                return 1d;

            double out = 0;
            for (var col : cols)
            {
                if(col.second == Unit.FRACTIONAL)
                    continue;

                out += col.first * UIUtils.getUnitProperty(col.second, widthProperty, null).get();
            }

            return out;
        }, columnProperty, widthProperty, Units.getTriggerProperty()));

        verticalUsedSpace.bind(Bindings.createDoubleBinding(() ->
        {
            var rows = rowProperty.get();
            if(rows == null)
                return 1d;

            double out = 0;
            for (var row : rows)
            {
                if(row.second == Unit.FRACTIONAL)
                    continue;

                out += row.first * UIUtils.getUnitProperty(row.second, heightProperty, null).get();
            }

            var gp = userStyle.gridGapProperty.get();
            var gap = UIUtils.getUnitProperty(gp.second, heightProperty, null).get() * (rows.size() - 1);
            return out + gap;
        }, rowProperty, heightProperty, Units.getTriggerProperty()));
    }

    private void requestBounds(UIComponent child)
    {
        var bounds = child.userStyle.gridBoundsProperty.get();

        child.layoutBounds.reset();
        child.layoutBounds.minXProperty.bind(gridColumns.get(bounds.x));
        child.layoutBounds.minYProperty.bind(gridColumns.get(bounds.z));
        child.layoutBounds.maxXProperty.bind(gridRows.get(bounds.y));
        child.layoutBounds.maxYProperty.bind(gridRows.get(bounds.w));
    }

    private void update()
    {
        var col = columnProperty.get();
        var row = rowProperty.get();

        gridRows.clear();
        gridColumns.clear();

        if(col == null)
        {
            gridRows.add(bounds.minYProperty);
            gridRows.add(bounds.maxYProperty);
            gridColumns.add(bounds.minXProperty);
            gridColumns.add(bounds.maxXProperty);
        }

        else
        {
            DoubleExpression prev = new SimpleDoubleProperty(0);
            gridColumns.add(prev);
            for (Tuple<Double, Unit> column : col)
            {
                var oProp = new SimpleObjectProperty<>(column);
                var binding = UIUtils.createNUBinding(widthProperty, horizontalFractionalUnits, oProp);

                gridColumns.add(prev.add(binding));
                prev = binding;
            }

            DoubleExpression prev2 = new SimpleDoubleProperty(0);
            gridRows.add(prev2);
            for (Tuple<Double, Unit> column : col)
            {
                var oProp = new SimpleObjectProperty<>(column);
                var binding = UIUtils.createNUBinding(heightProperty, verticalFractionalUnits, oProp);

                gridRows.add(prev2.add(binding));
                prev2 = binding;
            }
        }

        LinkedHashMap<IVec2, Boolean> availableCells = new LinkedHashMap<>();

        var width = col == null ? 1 : col.size();
        var height = row == null ? 1 : row.size();

        var lastItem = new IVec2(width - 1, height - 1);

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                availableCells.put(new IVec2(x, y), true);

        for (UIComponent child : children)
        {
            var bounds = child.userStyle.gridBoundsProperty.get();
            if(bounds != null)
            {
                var w = bounds.z - 1 - bounds.x;
                var h = bounds.w - 1 - bounds.y;

                for (int x = 0; x < w; x++)
                {
                    for (int y = 0; y < h; y++)
                    {
                        availableCells.put(bounds.xy().add(new IVec2(x, y)), false);
                    }
                }
            }
        }

        var list = new ArrayList<IVec2>();
        for (Map.Entry<IVec2, Boolean> entry : availableCells.entrySet())
        {
            if(entry.getValue())
                list.add(entry.getKey());
        }

        for (UIComponent child : children)
        {
            if(child.userStyle.gridBoundsProperty.get() == null)
            {
                IVec2 item;
                if(list.isEmpty())
                    item = lastItem;
                else
                    item = list.remove(0);

                child.userStyle.gridBoundsProperty.set(new IVec4(item, item.x + 1, item.y + 1));
            }

            getGridBounds(child);
        }
    }

    @Override
    public void requestBounds()
    {

    }
}