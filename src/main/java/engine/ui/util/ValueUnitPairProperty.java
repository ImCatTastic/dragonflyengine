package engine.ui.util;

import engine.ui.Unit;
import engine.util.Tuple;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ValueUnitPairProperty extends SimpleObjectProperty<ValueUnitPair>
{
    public ValueUnitPairProperty() {}
    public ValueUnitPairProperty(ValueUnitPair valueUnitPair)
    {
        super(valueUnitPair);
    }
    @Override
    public void setValue(ValueUnitPair valueUnitPair)
    {
        if(valueUnitPair == null)
            throw new UnsupportedOperationException("Null values not supported");

        super.setValue(valueUnitPair);
    }
    @Override
    public void set(ValueUnitPair valueUnitPair)
    {
        if(valueUnitPair == null)
            throw new UnsupportedOperationException("Null values not supported");

        super.set(valueUnitPair);
    }
}
