package engine.ui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Paint;

public class Stroke
{
    public final ObjectProperty<Paint> paintProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Double> thicknessProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<StrokeType> typeProperty = new SimpleObjectProperty<>(StrokeType.CENTERED);
    public Stroke(Paint paint, double thickness)
    {
        paintProperty.set(paint);
        thicknessProperty.set(thickness);
    }
}
