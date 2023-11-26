package engine.util;

import javafx.beans.property.SimpleDoubleProperty;

public record Units(double width, double height, double refUnit, SimpleDoubleProperty scale)
{

}
