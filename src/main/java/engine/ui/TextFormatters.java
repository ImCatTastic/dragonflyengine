package engine.ui;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class TextFormatters
{
    public final static TextFormatter<String> NUMERIC;

    static
    {
        NUMERIC = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*"))
                return change;

            return null;
        });
    }
}
