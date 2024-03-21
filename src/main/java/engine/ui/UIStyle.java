package engine.ui;

import engine.core.Window;
import engine.ui.util.ValueUnitPairProperty;
import engine.util.PivotPoint;
import engine.util.Tuple;
import engine.util.math.IVec4;
import engine.util.math.Vec2;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UIStyle
{
    public final ObjectProperty<Double> rotationProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<PivotPoint> rotationPivotPointProperty = new SimpleObjectProperty<>(null);




    //private final HashMap<String, ObjectProperty<?>>







    public final ValueUnitPairProperty widthProperty = new ValueUnitPairProperty(null);
    public final ValueUnitPairProperty heightProperty = new ValueUnitPairProperty(null);

    public final ObjectProperty<Tuple<Double, Unit>> minWidthProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Tuple<Double, Unit>> minHeightProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Tuple<Double, Unit>> maxWidthProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Tuple<Double, Unit>> maxHeightProperty = new SimpleObjectProperty<>(null);

    public final ObjectProperty<Tuple<Double, Unit>> topProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Tuple<Double, Unit>> bottomProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Tuple<Double, Unit>> leftProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Tuple<Double, Unit>> rightProperty = new SimpleObjectProperty<>(null);

    public final ObjectProperty<Tuple<Double, Unit>> cornerRadiusProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Tuple<Double, Unit>> cornerRadiusTopLeftProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Tuple<Double, Unit>> cornerRadiusTopRightProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Tuple<Double, Unit>> cornerRadiusBottomLeftProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Tuple<Double, Unit>> cornerRadiusBottomRightProperty = new SimpleObjectProperty<>(null);



    public final ObjectProperty<ArrayList<Tuple<Double, Unit>>> gridColumnsProperty = new SimpleObjectProperty<>();
    public final ObjectProperty<ArrayList<Tuple<Double, Unit>>> gridRowsProperty = new SimpleObjectProperty<>();
    public final ObjectProperty<Tuple<Double, Unit>> gridGapProperty = new SimpleObjectProperty<>();

    public final ObjectProperty<IVec4> gridBoundsProperty = new SimpleObjectProperty<>();
    
    
    public final ObjectProperty<Anchor.Horizontal> horizontalAnchorProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Anchor.Vertical> verticalAnchorProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<OverflowBehaviour> overflowBehaviourProperty = new SimpleObjectProperty<>(null);



    public final ObjectProperty<LayoutType> layoutTypeProperty = new SimpleObjectProperty<>(null);


    public final ObjectProperty<Direction> stackFlowDirectionProperty = new SimpleObjectProperty<>(null);



    public final ObjectProperty<Paint> fillProperty = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Stroke> strokeProperty = new SimpleObjectProperty<>(null);


    public final ObjectProperty<String> textProperty = new SimpleObjectProperty<>(null);



    public final ObjectProperty<Direction> stackDirection = new SimpleObjectProperty<>(null);


    public UIStyle() {}
    public UIStyle(UIStyle uiStyle)
    {
        apply(uiStyle);
    }

    public void reset()
    {
        //TODO: reset all properties to null
    }


    public static UIStyle createDefault()
    {
        var style = new UIStyle();
        style.setDefault();
        return style;
    }

    public UIStyle setDefault()
    {
        widthProperty.set(new Tuple<>(100d, Unit.PIXEL));
        heightProperty.set(new Tuple<>(100d, Unit.PIXEL));

        horizontalAnchorProperty.set(Anchor.Horizontal.CENTER);
        verticalAnchorProperty.set(Anchor.Vertical.CENTER);

        topProperty.set(new Tuple<>(0d, Unit.PIXEL));
        leftProperty.set(new Tuple<>(0d, Unit.PIXEL));
        rightProperty.set(new Tuple<>(0d, Unit.PIXEL));
        bottomProperty.set(new Tuple<>(0d, Unit.PIXEL));

        fillProperty.set(Color.rgb(255,0,255));
        strokeProperty.set(new Stroke(Color.rgb(255,0,255), 0));

        layoutTypeProperty.set(LayoutType.NONE);
        stackDirection.set(Direction.UP);

        gridGapProperty.set(new Tuple<>(0d, Unit.PIXEL));

        return this;
    }



    public UIStyle apply(UIStyle uiStyle)
    {
        applyIfNonNull(horizontalAnchorProperty, uiStyle.horizontalAnchorProperty);
        applyIfNonNull(verticalAnchorProperty, uiStyle.verticalAnchorProperty);

        //TODO: copy all properties
        return this;
    }

    private <T> void applyIfNonNull(ObjectProperty<T> property, ObjectProperty<T> value)
    {
        var v = value.get();
        if(v != null)
            property.set(v);
    }


    public UIStyle setFill(Paint paint)
    {
        fillProperty.set(paint);
        return this;
    }

    public UIStyle setLayoutType(LayoutType type)
    {
        layoutTypeProperty.set(type);
        return this;
    }

    public UIStyle setHorizontalAnchor(Anchor.Horizontal anchor)
    {
        horizontalAnchorProperty.set(anchor);
        return this;
    }
    public UIStyle setVerticalAnchor(Anchor.Vertical anchor)
    {
        verticalAnchorProperty.set(anchor);
        return this;
    }
    public UIStyle setRotation(double angle)
    {
        rotationProperty.set(angle);
        return this;
    }
    public UIStyle setWidth(double width, Unit unit)
    {
        widthProperty.set(new Tuple<>(width, unit));
        return this;
    }
    public UIStyle setWidth(String value)
    {
        widthProperty.set(UIStyleParser.parseNumberWithUnit(value));
        return this;
    }
    public UIStyle setHeight(double height, Unit unit)
    {
        heightProperty.set(new Tuple<>(height, unit));
        return this;
    }
    public UIStyle setHeight(String value)
    {
        heightProperty.set(UIStyleParser.parseNumberWithUnit(value));
        return this;
    }


    public UIStyle define(String value)
    {
        var tokenChunks = UIStyleParser.tokenize(value);
        for (UIStyleParser.TokenChunk tokenChunk : tokenChunks)
            applyToken(tokenChunk);

        return this;
    }

    private static String value;
    private static String property;
    private void applyToken(UIStyleParser.TokenChunk chunk)
    {
        property = chunk.property();
        value = chunk.value();
        
        if(handleInsets() || handleCornerRadius())
            return;

        switch (chunk.property())
        {
            case "width" -> parseNVs(widthProperty);
            case "height" -> parseNVs(heightProperty);

            case "anchor-horizontal" -> horizontalAnchorProperty.set(Anchor.Horizontal.parse(value));
            case "anchor-vertical" -> verticalAnchorProperty.set(Anchor.Vertical.parse(value));

            case "fill" -> fillProperty.set(parsePaint());

            case "rotation" -> rotationProperty.set(Double.parseDouble(value));
            case "rotation-pivot-point" -> rotationPivotPointProperty.set(parsePivotPoint());

            case "layout" -> layoutTypeProperty.set(LayoutType.parse(value));

            case "stack-direction" -> stackDirection.set(Direction.parse(value));

            case "grid-template-columns" -> gridColumnsProperty.set(parseGridCells());
            case "grid-template-rows" -> gridRowsProperty.set(parseGridCells());

            case "grid-bounds" -> gridBoundsProperty.set(IVec4.parse(value)); //ivec4(0, 0, 1, 1)

            default -> throw new IllegalStateException("Found unknown token while parsing: " + chunk.property());
        };
    }

    private static ArrayList<Tuple<Double, Unit>> parseGridCells()
    {
        Pattern p = Pattern.compile("\\s*repeat\\s*\\(\\s*([0-9]+)\\s*,\\s*([0-9]+(?:\\.[0-9]+)?)\\s*([a-z]+|%)\\s*\\)\\s*|\\s*([0-9]+(?:\\.[0-9]+)?)\\s*([a-z]+|%)\\s*");
        Matcher matcher = p.matcher(value);

        var cell = new ArrayList<Tuple<Double, Unit>>();
        List<String> matches = new ArrayList<>();
        while (matcher.find())
        {
            int n = 1;
            double val;
            Unit unit;

            //1 repeats, 2 number, 3 unit
            if(matcher.group(1) != null)
            {
                n = Integer.parseInt(matcher.group(1));
                val = Double.parseDouble(matcher.group(2));
                unit = Unit.parse(matcher.group(3));
            }

            //4 number, 5 unit
            else
            {
                val = Double.parseDouble(matcher.group(4));
                unit = Unit.parse(matcher.group(5));
            }

            var out = new Tuple<>(val, unit);
            for (int i = 0; i < n; i++)
                cell.add(out);

            matches.add(matcher.group());
        }

        if (matches.stream().mapToInt(String::length).sum() != value.length())
            throw new IllegalArgumentException("Found invalid character while parsing value: " + value);

        return cell;
    }







    private static Paint parsePaint()
    {
        Pattern pattern = Pattern.compile("^(#(?:[0-9a-fA-F]{3}|[0-9a-fA-F]{6}))|(rgba?|hsla?)\\(([^)]*)\\)|var\\((--[a-z]+)\\)|([a-z]+)$");
        Matcher matcher = pattern.matcher(value);

        if(matcher.matches())
        {
            if(matcher.group(1) != null) //HexColor
                return Color.web(matcher.group(1));
            if(matcher.group(2) != null)
            {
                return switch (matcher.group(2))
                {
                    case "rgb" ->
                    {
                        var parts = matcher.group(3).split(",");
                        var r = Integer.parseInt(parts[0].replaceAll("\\s+", ""));
                        var g = Integer.parseInt(parts[1].replaceAll("\\s+", ""));
                        var b = Integer.parseInt(parts[2].replaceAll("\\s+", ""));
                        yield Color.rgb(r, g, b);
                    }
                    case "rgba" ->
                    {
                        var parts = matcher.group(3).split(",");
                        var r = Integer.parseInt(parts[0].replaceAll("\\s+", ""));
                        var g = Integer.parseInt(parts[1].replaceAll("\\s+", ""));
                        var b = Integer.parseInt(parts[2].replaceAll("\\s+", ""));
                        var a = Double.parseDouble(parts[3]);
                        yield Color.rgb(r, g, b, a);
                    }
                    case "hsl" -> throw new UnsupportedOperationException("HSL not supported yet.");
                    case "hsla" -> throw new UnsupportedOperationException("HSLA not supported yet.");
                    default -> throw new IllegalStateException("Unexpected value: " + matcher.group(2));
                };
            }
            if(matcher.group(4) != null) //variable
            {
                //TODO: implement variables
                throw new UnsupportedOperationException("Variables are not currently supported for colors.");
            }
            if(matcher.group(5) != null)
                return Colors.get(matcher.group(5));
        }







        int i = 0;
        //rgb(255, 255, 255)
        //rgba(255, 255, 255, 1.0)
        //hsl(0, 100%, 50%);
        //hsla(0, 100%, 50%, 0.5);
        //#FF00FF
        //red
        //var(--red)


        return null;
    }


    @SafeVarargs
    private static void parseNVs(ObjectProperty<Tuple<Double, Unit>>... properties)
    {
        var propValue = UIStyleParser.parseNumberWithUnit(value);
        for (ObjectProperty<Tuple<Double, Unit>> property : properties)
            property.set(propValue);
    }

    private static PivotPoint parsePivotPoint()
    {
        return switch (value)
        {
            case "top-left" -> PivotPoint.TOP_LEFT;
            case "top-center" -> PivotPoint.TOP_CENTER;
            case "top-right" -> PivotPoint.TOP_RIGHT;
            case "center-left" -> PivotPoint.CENTER_LEFT;
            case "center" -> PivotPoint.CENTER;
            case "center-right" -> PivotPoint.CENTER_RIGHT;
            case "bottom-left" -> PivotPoint.BOTTOM_LEFT;
            case "bottom-center" -> PivotPoint.BOTTOM_CENTER;
            case "bottom-right" -> PivotPoint.BOTTOM_RIGHT;
            default -> PivotPoint.deriveFromVec2(Vec2.parse(value));
        };
    }
    
    private boolean handleInsets()
    {
        switch (property)
        {
            case "insets" -> parseNVs(leftProperty, topProperty, rightProperty, bottomProperty);
            case "left" -> parseNVs(leftProperty);
            case "top" -> parseNVs(topProperty);
            case "right" -> parseNVs(rightProperty);
            case "bottom" -> parseNVs(bottomProperty);

            case "left-top" -> parseNVs(leftProperty, topProperty);
            case "left-bottom" -> parseNVs(leftProperty, bottomProperty);
            case "left-right" -> parseNVs(leftProperty, rightProperty);

            case "top-bottom" -> parseNVs(topProperty, bottomProperty);
            case "top-right" -> parseNVs(topProperty, rightProperty);
            case "top-left" -> parseNVs(topProperty, leftProperty);

            case "right-top" -> parseNVs(rightProperty, topProperty);
            case "right-bottom" -> parseNVs(rightProperty, bottomProperty);
            case "right-left" -> parseNVs(rightProperty, leftProperty);

            case "bottom-left" -> parseNVs(bottomProperty, leftProperty);
            case "bottom-top" -> parseNVs(bottomProperty, topProperty);
            case "bottom-right" -> parseNVs(bottomProperty, rightProperty);

            case "left-top-right" -> parseNVs(leftProperty, topProperty, rightProperty);
            case "left-top-bottom" -> parseNVs(leftProperty, topProperty, bottomProperty);
            case "left-right-bottom" -> parseNVs(leftProperty, rightProperty, bottomProperty);
            case "left-right-top" -> parseNVs(leftProperty, rightProperty, topProperty);
            case "left-bottom-top" -> parseNVs(leftProperty, bottomProperty, topProperty);
            case "left-bottom-right" -> parseNVs(leftProperty, bottomProperty, rightProperty);

            case "top-left-bottom" -> parseNVs(topProperty, leftProperty, bottomProperty);
            case "top-left-right" -> parseNVs(topProperty, leftProperty, rightProperty);
            case "top-bottom-right" -> parseNVs(topProperty, bottomProperty, rightProperty);
            case "top-bottom-left" -> parseNVs(topProperty, bottomProperty, leftProperty);
            case "top-right-left" -> parseNVs(topProperty, rightProperty, leftProperty);
            case "top-right-bottom" -> parseNVs(topProperty, rightProperty, bottomProperty);

            case "right-top-left" -> parseNVs(rightProperty, topProperty, leftProperty);
            case "right-top-bottom" -> parseNVs(rightProperty, topProperty, bottomProperty);
            case "right-left-bottom" -> parseNVs(rightProperty, leftProperty, bottomProperty);
            case "right-left-top" -> parseNVs(rightProperty, leftProperty, topProperty);
            case "right-bottom-top" -> parseNVs(rightProperty, bottomProperty, topProperty);
            case "right-bottom-left" -> parseNVs(rightProperty, bottomProperty, leftProperty);

            case "bottom-left-top" -> parseNVs(bottomProperty, leftProperty, topProperty);
            case "bottom-left-right" -> parseNVs(bottomProperty, leftProperty, rightProperty);
            case "bottom-top-right" -> parseNVs(bottomProperty, topProperty, rightProperty);
            case "bottom-top-left" -> parseNVs(bottomProperty, topProperty, leftProperty);
            case "bottom-right-left" -> parseNVs(bottomProperty, rightProperty, leftProperty);
            case "bottom-right-top" -> parseNVs(bottomProperty, rightProperty, topProperty);

            default ->
            {
                return false;
            }
        }
        
        return true;
    }
    private boolean handleCornerRadius()
    {
        switch (property)
        {
            case "corner-radius" -> parseNVs(cornerRadiusProperty);

            case "corner-radius-left" -> parseNVs(cornerRadiusTopLeftProperty, cornerRadiusBottomLeftProperty);
            case "corner-radius-top" -> parseNVs(cornerRadiusTopLeftProperty, cornerRadiusTopRightProperty);
            case "corner-radius-right" -> parseNVs(cornerRadiusTopRightProperty, cornerRadiusBottomRightProperty);
            case "corner-radius-bottom" -> parseNVs(cornerRadiusBottomLeftProperty, cornerRadiusBottomRightProperty);

            case "corner-radius-top-left" -> parseNVs(cornerRadiusTopLeftProperty);
            case "corner-radius-top-right" -> parseNVs(cornerRadiusTopRightProperty);

            case "corner-radius-bottom-left" -> parseNVs(cornerRadiusBottomLeftProperty);
            case "corner-radius-bottom-right" -> parseNVs(cornerRadiusBottomRightProperty);
            
            default -> {return false;}
        }

        return true;
    }









}
