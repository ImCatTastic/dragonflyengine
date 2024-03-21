package engine.ui;

import engine.ui.event.EventHandler;
import engine.ui.layout.UILayout;
import engine.ui.util.Insets;
import engine.util.Tuple;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public abstract class UIComponent
{
    private final static UIStyle defaultStyle = UIStyle.createDefault();



    private UICanvas canvas;
    public UICanvas getCanvas()
    {
        return canvas;
    }
    private UIComponent parent;
    protected final ArrayList<UIComponent> children = new ArrayList<>();

    private UILayout layout;

    public final Pane container_background = new Pane();
    public final Pane container_foreground = new Pane(); //store foreground elements, remove them when new element is added, then add back
    private final EventHandler eventHandler = new EventHandler(container_background);

    protected final DoubleProperty minDimensionProperty = new SimpleDoubleProperty();
    protected final DoubleProperty maxDimensionProperty = new SimpleDoubleProperty();
    protected final Insets padding = new Insets();
    protected final Insets margin = new Insets();
    protected final UIBoundingBox layoutBounds = new UIBoundingBox();

    protected final DoubleProperty xProperty = new SimpleDoubleProperty();
    protected final DoubleProperty yProperty = new SimpleDoubleProperty();
    protected final DoubleProperty widthProperty = new SimpleDoubleProperty();
    protected final DoubleProperty heightProperty = new SimpleDoubleProperty();
    protected final DoubleProperty parentWidthProperty = new SimpleDoubleProperty();
    protected final DoubleProperty parentHeightProperty = new SimpleDoubleProperty();

    public UIComponent()
    {
        widthProperty.bind(UIUtils.createNUBinding(parentWidthProperty, null, userStyle.widthProperty));
        heightProperty.bind(UIUtils.createNUBinding(parentHeightProperty, null, userStyle.heightProperty));



        userStyle.horizontalAnchorProperty.addListener(x -> adjustVerticalTransform());
        userStyle.horizontalAnchorProperty.addListener(x -> adjustVerticalTransform());

        verticalAnchorProperty.addListener(x -> adjustVerticalTransform());
        horizontalAnchorProperty.addListener(x -> adjustHorizontalTransform());

        userStyle.layoutTypeProperty.addListener(value -> updateLayout());

        userStyle.stackDirection.addListener(x ->
        {
            if(userStyle.layoutTypeProperty.get() != LayoutType.STACK)
                return;

            updateStackLayout();
        });
    }




    private final ObjectProperty<UIComponent> parentProperty = new SimpleObjectProperty<>();

    private <T> ObjectExpression<T> getIfParentOrElse(ObjectProperty<T> property, T defaultValue)
    {
        return Bindings.createObjectBinding(() ->
        {
           if(parentProperty.get() != null)
               return property.get();

           return defaultValue;
        }, parentProperty, property);
    }
    private DoubleExpression getIfParentOrDefaultD(DoubleProperty property, double defaultValue)
    {
        return Bindings.createDoubleBinding(() ->
        {
            if(parentProperty.get() != null)
                return property.get();

            return defaultValue;
        }, parentProperty, property);
    }

    protected void load()
    {
        var l_w = layoutBounds.widthProperty;
        var l_h = layoutBounds.heightProperty;
        var u_w = UIUtils.createNUBinding(parentBounds.widthProperty, null, userStyle.widthProperty);
        var u_h = UIUtils.createNUBinding(parentBounds.heightProperty, null, userStyle.heightProperty);

        bounds.bind(
                new SimpleDoubleProperty(0.0d),
                new SimpleDoubleProperty(0.0d),
                UIUtils.createNUCMBinding(parentBounds.widthProperty, null, l_w, u_w),
                UIUtils.createNUCMBinding(parentBounds.heightProperty, null, l_h, u_h)
        );

        minDimensionProperty.bind(UIUtils.createMinBinding(bounds.widthProperty, bounds.heightProperty));
        maxDimensionProperty.bind(UIUtils.createMaxBinding(bounds.widthProperty, bounds.heightProperty));

        topProperty.bind(UIUtils.createNUBinding(null, p_h, userStyle.topProperty));
        leftProperty.bind(UIUtils.createNUBinding(null, p_w, userStyle.leftProperty));
        bottomProperty.bind(UIUtils.createNUBinding(null, p_h, userStyle.bottomProperty));
        rightProperty.bind(UIUtils.createNUBinding(null, p_w, userStyle.rightProperty));

        horizontalAnchorProperty.bind(UIUtils.createCompetingObjectBinding(containmentStyle.horizontalAnchorProperty, userStyle.horizontalAnchorProperty));
        verticalAnchorProperty.bind(UIUtils.createCompetingObjectBinding(containmentStyle.verticalAnchorProperty, userStyle.verticalAnchorProperty));

        userStyle.gridColumnsProperty.addListener(x ->
        {
            if(parent != null)
                parent.layout.requestBounds();
        });
        userStyle.gridRowsProperty.addListener(x ->
        {
            if(parent != null)
                parent.layout.requestBounds();
        });

        container_background.prefWidthProperty().bind(bounds.widthProperty);
        container_background.prefHeightProperty().bind(bounds.heightProperty);
        container_background.layoutXProperty().bind(bounds.minXProperty);
        container_background.layoutYProperty().bind(bounds.minYProperty);

        container_foreground.prefWidthProperty().bind(bounds.widthProperty);
        container_foreground.prefHeightProperty().bind(bounds.heightProperty);
        container_foreground.layoutXProperty().bind(bounds.minXProperty);
        container_foreground.layoutYProperty().bind(bounds.minYProperty);

        adjustHorizontalTransform();
        adjustVerticalTransform();
    }

    UIComponent(UICanvas canvas, Pane root)
    {
        this.canvas = canvas;
        container_background.prefWidthProperty().bind(root.widthProperty());
        container_background.prefHeightProperty().bind(root.heightProperty());

        bounds.bind(
                new SimpleDoubleProperty(0),
                new SimpleDoubleProperty(0),
                root.widthProperty(),
                root.heightProperty()
        );
    }

    private void updateLayout()
    {
        var layoutType = userStyle.layoutTypeProperty.get();
        switch (layoutType)
        {
            case NONE ->
            {
                for (UIComponent child : children)
                {
                    child.containmentStyle.reset();
                    child.offsetX.set(0);
                    child.offsetY.set(0);
                }
            }
            case STACK -> updateStackLayout();
            case GRID -> updateGridLayout();
            case FIT_CONTENT ->
            {

            }
        }
    }

    private void updateStackLayout()
    {
        //TODO: rework

        UIComponent prevChild = null;
        for (UIComponent child : children)
        {
            var style = new UIStyle();

            var flowDir = userStyle.stackDirection.get();
            switch (flowDir)
            {
                case LEFT ->
                {
                    style.horizontalAnchorProperty.set(Anchor.Horizontal.LEFT);

                    if(prevChild != null)
                        child.layoutBounds.minXProperty.bind(prevChild.bounds.maxXProperty);
                    else
                        child.offsetX.set(0);
                }
                case UP ->
                {
                    style.verticalAnchorProperty.set(Anchor.Vertical.TOP);

                    if(prevChild != null)
                        child.offsetY.bind(prevChild.bounds.heightProperty.add(prevChild.topProperty).add(prevChild.bottomProperty).add(prevChild.offsetY));
                    else
                        child.offsetY.set(0);
                }
                case RIGHT ->
                {
                    style.horizontalAnchorProperty.set(Anchor.Horizontal.RIGHT);

                    if(prevChild != null)
                        child.offsetX.bind(prevChild.bounds.widthProperty.negate().subtract(prevChild.offsetX));
                    else
                        child.offsetX.set(0);
                }
                case DOWN ->
                {
                    style.verticalAnchorProperty.set(Anchor.Vertical.BOTTOM);

                    if(prevChild != null)
                        child.offsetY.bind(prevChild.bounds.heightProperty.negate().subtract(prevChild.offsetY));
                    else
                        child.offsetY.set(0);
                }
            }
            child.containmentStyle.apply(style);
            prevChild = child;
        }
    }





























    public EventHandler getEventHandler()
    {
        return eventHandler;
    }
    Pane getContainer_background()
    {
        return container_background;
    }
    protected final void addNodesBackground(Node... nodes)
    {
        if(nodes != null)
            container_background.getChildren().addAll(nodes);
    }
    protected final void addNodesForeground(Node... nodes)
    {
        if(nodes != null)
            container_foreground.getChildren().addAll(nodes);
    }
    // Getters
    public final UIComponent getParent()
    {
        return parent;
    }
    public final double getX()
    {
        return bounds.minXProperty.get();
    }
    public final double getY()
    {
        return bounds.minYProperty.get();
    }
    public final double getWidth()
    {
        return bounds.widthProperty.get();
    }
    public final double getHeight()
    {
        return bounds.heightProperty.get();
    }
    protected DoubleProperty getWidthProperty()
    {
        return bounds.widthProperty;
    }
    protected DoubleProperty getHeightProperty()
    {
        return bounds.heightProperty;
    }



    protected final UIStyle userStyle = new UIStyle();





    public void applyStyle(UIStyle style)
    {
        this.userStyle.apply(style);
    }
    public UIStyle getUserStyle()
    {
        return userStyle;
    }

    // Setters
    public void setParent(UICanvas parent)
    {
        setParent(parent.getRootComponent());
    }
    public void setParent(UIComponent parent)
    {
        if(this.parent != null)
            this.parent.remove(this);

        this.parent = parent;
        this.parent.add(this);
        layoutBounds.bind(getLayoutBounds());
    }
    private void add(UIComponent component)
    {
        children.add(component);
        container_background.getChildren().addAll(component.container_background, component.container_foreground);

        if(getCanvas() != null)
            component.setCanvas(getCanvas());
    }
    private void remove(UIComponent component)
    {
        if(component.parent != null)
        {
            children.remove(component);
            container_background.getChildren().removeAll(component.container_background, component.container_foreground);
        }
    }

    private UIBoundingBox getLayoutBounds()
    {
        return parent.layout.requestBounds();
    }
    //Others
    private void setCanvas(UICanvas canvas)
    {
        this.canvas = canvas;
        for (UIComponent child : children)
            child.setCanvas(canvas);
    }
    protected void adjustHorizontalTransform()
    {
        if(parent == null)
            return;

        if(horizontalAnchorProperty.get() != Anchor.Horizontal.STRETCH && horizontalAnchorProperty.isBound())
        {
            horizontalAnchorProperty.unbind();
            stretchWidthProperty.set(null);
        }

        var wProp = layoutBounds.widthProperty;
        var binding = switch (horizontalAnchorProperty.get())
        {
            case LEFT -> leftProperty;
            case RIGHT -> wProp.subtract(bounds.widthProperty).subtract(rightProperty);
            case CENTER -> wProp.divide(2).subtract(bounds.widthProperty.divide(2)).add(leftProperty).subtract(rightProperty);
            case STRETCH ->
            {
                stretchWidthProperty.bind(Bindings.createObjectBinding(() ->
                    new Tuple<>(Math.max(wProp.get() - leftProperty.get() - rightProperty.get(), 0), Unit.LITERAL),
                    wProp, leftProperty, rightProperty)
                );
                yield leftProperty;
            }
        };

        xProperty.bind(binding.add(offsetX));
    }

    protected void adjustVerticalTransform()
    {
        if(parent == null)
            return;

        if(verticalAnchorProperty.get() != Anchor.Vertical.STRETCH && stretchHeightProperty.isBound())
        {
            stretchHeightProperty.unbind();
            stretchHeightProperty.set(null);
        }

        var hProp = layoutBounds.heightProperty;
        var binding = switch (verticalAnchorProperty.get())
        {
            case TOP -> topProperty;
            case BOTTOM -> hProp.subtract(bounds.heightProperty).subtract(bottomProperty);
            case CENTER -> hProp.divide(2).subtract(bounds.heightProperty.divide(2)).add(topProperty).subtract(bottomProperty);
            case STRETCH ->
            {
                stretchHeightProperty.bind(Bindings.createObjectBinding(() ->
                    new Tuple<>(Math.max(hProp.get() - topProperty.get() - bottomProperty.get(), 0), Unit.LITERAL),
                    hProp, topProperty, bottomProperty)
                );
                yield topProperty;
            }
        };


        yProperty.bind(binding.add(offsetY));
    }

}