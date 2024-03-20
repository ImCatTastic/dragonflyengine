package engine.ui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class GridContainer extends UIComponent
{
    private record CellComponent(Double value, UnitType type, DoubleProperty min, DoubleProperty max) {}
    private final ArrayList<CellComponent> columns = new ArrayList<>();
    private final ArrayList<CellComponent> rows = new ArrayList<>();
    private final DoubleProperty totalRUnits_column = new SimpleDoubleProperty();
    private final DoubleProperty totalRUnits_row = new SimpleDoubleProperty();
    private final DoubleProperty gapProperty = new SimpleDoubleProperty();
    private final CellStats colStats = new CellStats(widthProperty, gapProperty);
    private final CellStats rowStats = new CellStats(heightProperty, gapProperty);
    public void setGap(double value)
    {
        gapProperty.set(value);
    }
    public void addColumns(double value, UnitType unitType, int n)
    {
        if(value < 1)
            throw new IllegalArgumentException("n cannot be lower than 1");

        for (int i = 0; i < n; i++)
            addColumn(value, unitType);
    }
    public void addRows(double value, UnitType unitType, int n)
    {
        if(value < 1)
            throw new IllegalArgumentException("n cannot be lower than 1");

        for (int i = 0; i < n; i++)
            addRow(value, unitType);
    }
    private void addCell(double value, UnitType unitType, List<CellComponent> collection,
                         DoubleProperty unitsProperty, CellStats stats)
    {
        stats.inc();
        var min = new SimpleDoubleProperty(0.0d);
        var max = new SimpleDoubleProperty(0.0d);

        if(!collection.isEmpty())
            min.bind(collection.get(collection.size() - 1).max.add(gapProperty));

        if(unitType == UnitType.RELATIVE)
        {
            unitsProperty.set(unitsProperty.get() + value);
            var binding = Bindings.createDoubleBinding(() ->
            {
                var ratio = value / unitsProperty.get();
                return stats.availableSpace.get() * ratio;
            }, stats.availableSpace, unitsProperty);
            max.bind(min.add(binding));
        }

        else
        {
            stats.addAbsolute(value);
            max.bind(min.add(value));
        }

        collection.add(new CellComponent(value, unitType, min, max));
    }
    public void addColumn(double value, UnitType unitType)
    {
        addCell(value, unitType, columns, totalRUnits_column, colStats);
    }
    public void addRow(double value, UnitType unitType)
    {
        addCell(value, unitType, rows, totalRUnits_row, rowStats);
    }
    public void add(UIComponent component, int minX, int minY, int maxX, int maxY)
    {
        component.layoutBounds.bind(generateBounds(minX, minY, maxX, maxY));
        component.offsetX.bind(component.layoutBounds.minXProperty);
        component.offsetY.bind(component.layoutBounds.minYProperty);
        component.adjustHorizontalTransform();
        component.adjustVerticalTransform();
        component.setParent(this);
    }
    private UIBoundingBox generateBounds(int minX, int minY, int maxX, int maxY)
    {
        var box = new UIBoundingBox();
        box.bind(
            columns.get(minX).min,
            rows.get(minY).min,
            columns.get(maxX).max.subtract(columns.get(maxX).min),
            rows.get(maxY).max.subtract(rows.get(maxY).min)
        );

        return box;
    }
    private static class CellStats
    {
        final DoubleProperty count = new SimpleDoubleProperty();
        final DoubleProperty occupiedSpace = new SimpleDoubleProperty();
        final DoubleExpression availableSpace;
        private CellStats(DoubleProperty maxSpace, DoubleProperty gap)
        {
            Callable<Double> binding = () -> maxSpace.get() - occupiedSpace.get() - gap.get() * (count.get() - 1);
            availableSpace = Bindings.createDoubleBinding(binding, maxSpace, occupiedSpace, gap, count);
        }
        private void addAbsolute(double value)
        {
            occupiedSpace.set(occupiedSpace.get() + value);
        }
        private void inc()
        {
            count.set(count.get() + 1);
        }
    }
}
