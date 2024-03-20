package engine.ui.shape;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.skin.TextFieldSkin;

public class TextInput2D extends TextInputControl
{
    public TextInput2D()
    {
        super(new TextContent());
    }

    private static class TextContent implements Content
    {
        @Override
        public String get(int i, int i1)
        {
            return null;
        }

        @Override
        public void insert(int i, String s, boolean b)
        {

        }

        @Override
        public void delete(int i, int i1, boolean b)
        {

        }

        @Override
        public int length()
        {
            return 0;
        }
        @Override
        public String get()
        {
            return null;
        }
        @Override
        public void addListener(ChangeListener<? super String> changeListener)
        {

        }
        @Override
        public void removeListener(ChangeListener<? super String> changeListener)
        {

        }
        @Override
        public String getValue()
        {
            return "Hello";
        }

        @Override
        public void addListener(InvalidationListener invalidationListener)
        {

        }

        @Override
        public void removeListener(InvalidationListener invalidationListener)
        {

        }
    }
}
