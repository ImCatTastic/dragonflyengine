package engine.ui;

import engine.core.Window;
import engine.ui.Unit;
import engine.ui.Units;
import engine.util.Tuple;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;

public class UIUtils
{
    public static DoubleExpression getUnitProperty(Unit unit, DoubleExpression percentProperty, DoubleExpression fractionalProperty)
    {
        return switch (unit)
        {
            case LITERAL -> Units.getLiteralUnitProperty();
            case VIEW_WIDTH -> Units.getViewWidthUnitProperty();
            case VIEW_HEIGHT -> Units.getViewHeightUnitProperty();
            case VIEW_MIN -> Units.getViewMinUnitProperty();
            case VIEW_MAX -> Units.getViewMaxUnitProperty();
            case PERCENT -> percentProperty.divide(100);
            case PIXEL -> Window.getPixelConversionFactorProperty();
            case WORLD -> Units.getWorldUnitProperty();
            case FRACTIONAL ->
            {
                if(fractionalProperty == null)
                    throw new UnsupportedOperationException("Fractional units can only be used for defining grid cells.");

                else
                    yield fractionalProperty;
            }
        };
    }

    public static DoubleExpression createMinBinding(DoubleProperty property1, DoubleProperty property2)
    {
        return Bindings.createDoubleBinding(() -> Math.min(property1.get(), property2.get()), property1, property2);
    }
    public static DoubleExpression createMaxBinding(DoubleProperty property1, DoubleProperty property2)
    {
        return Bindings.createDoubleBinding(() -> Math.max(property1.get(), property2.get()), property1, property2);
    }
    public static <T> ObjectExpression<T> createCompetingBinding(final ObjectProperty<T> holder1, final ObjectProperty<T> holder2)
    {
        return Bindings.createObjectBinding(() ->
        {
            var primary = holder1.get();
            var secondary = holder2.get();
            return primary != null ? primary : secondary;
        }, holder1, holder2, Units.getTriggerProperty());
    }
    public static DoubleExpression createNUBinding(
            DoubleExpression percentProperty,
            DoubleExpression fractionProperty,
            ObjectProperty<Tuple<Double, Unit>> holder)
    {
        return Bindings.createDoubleBinding(() ->
        {
            if(holder.get() == null)
                return 0d;

            var value = holder.get().first;
            var unit = holder.get().second;

            return value * getUnitProperty(unit, percentProperty, fractionProperty).get();
        }, holder, Units.getTriggerProperty());
    }
    public static DoubleExpression createNUCBinding(
            DoubleExpression percentProperty,
            DoubleExpression fractionProperty,
            ObjectProperty<Tuple<Double, Unit>> holder1,
            ObjectProperty<Tuple<Double, Unit>> holder2)
    {
        return Bindings.createDoubleBinding(() ->
        {
            var primary = holder1.get();
            var secondary = holder2.get();

            double value;
            Unit unit;

            if(primary != null && primary.first != null && primary.second != null)
            {
                value = primary.first;
                unit = primary.second;
            }
            else
            {
                if(secondary == null)
                    return 0d;

                value = secondary.first;
                unit = secondary.second;
            }

            return value * getUnitProperty(unit, percentProperty, fractionProperty).get();
        }, holder1, holder2, Units.getTriggerProperty());
    }
    @SafeVarargs
    public static DoubleExpression createNUCMBinding(
            DoubleExpression percentProperty,
            DoubleExpression fractionProperty,
            ObjectProperty<Tuple<Double, Unit>>... holders
    )
    {
        Observable[] all = new Observable[holders.length + 1];
        System.arraycopy(holders, 0, all, 0, holders.length);
        all[all.length - 1] = Units.getTriggerProperty();

        return Bindings.createDoubleBinding(() ->
        {
            for (var holder : holders)
            {
                var tuple = holder.get();
                if(tuple != null && tuple.first != null && tuple.second != null)
                {
                    var value = tuple.first;
                    var unit = tuple.second;

                    return value * getUnitProperty(unit, percentProperty, fractionProperty).get();
                }
            }

            return 0d;

        }, all);
    }
}