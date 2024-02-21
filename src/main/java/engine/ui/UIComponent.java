package engine.ui;

import engine.util.PivotPoint;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static engine.ui.Anchor.Vertical.STRETCH;

public abstract class UIComponent
{
    private UICanvas canvas;
    public UICanvas getCanvas()
    {
        return canvas;
    }
    private UIComponent parent;
    private final ArrayList<Node> children = new ArrayList<>();
    protected SimpleDoubleProperty widthProperty = new SimpleDoubleProperty(0.0d);
    protected SimpleDoubleProperty heightProperty = new SimpleDoubleProperty(0.0d);
    protected SimpleDoubleProperty topProperty = new SimpleDoubleProperty(0.0d);
    protected SimpleDoubleProperty bottomProperty = new SimpleDoubleProperty(0.0d);
    protected SimpleDoubleProperty leftProperty = new SimpleDoubleProperty(0.0d);
    protected SimpleDoubleProperty rightProperty = new SimpleDoubleProperty(0.0d);

    protected PivotPoint pivotPoint = PivotPoint.CENTER;
    private Anchor.Horizontal horizontalAnchor = Anchor.Horizontal.STRETCH;
    private Anchor.Vertical verticalAnchor = STRETCH;

    public UIComponent()
    {
        adjustHorizontalTransform(horizontalAnchor);
        adjustVerticalTransform(verticalAnchor);
    }
    UIComponent(UICanvas canvas, Pane root)
    {
        this.canvas = canvas;
        widthProperty.bind(root.widthProperty().divide(canvas.getUnitProperty()));
        heightProperty.bind(root.heightProperty().divide(canvas.getUnitProperty()));
        container.prefWidthProperty().bind(root.widthProperty());
        container.prefHeightProperty().bind(root.heightProperty());
        //container.setStyle("-fx-background-color: #" + "f43838");
    }

    public final Pane container = new Pane();
    Pane getContainer()
    {
        return container;
    }
    private boolean added = false;
    protected final void addNodes(Node... nodes)
    {
        if(added)
            throw new IllegalStateException("Nodes already added");

        added = true;
        if(nodes != null)
            container.getChildren().addAll(nodes);
    }
    // Getters
    public final UIComponent getParent()
    {
        return parent;
    }
    public final double getWidth()
    {
        return widthProperty.get();
    }
    public final double getHeight()
    {
        return heightProperty.get();
    }
    public final double getTop()
    {
        return topProperty.get();
    }
    public final double getBottom()
    {
        return bottomProperty.get();
    }
    public final double getLeft()
    {
        return leftProperty.get();
    }
    public final double getRight()
    {
        return rightProperty.get();
    }
    public final PivotPoint getPivotPoint()
    {
        return pivotPoint;
    }
    public final Anchor.Horizontal getHorizontalAnchor()
    {
        return horizontalAnchor;
    }
    public final Anchor.Vertical getVerticalAnchor()
    {
        return verticalAnchor;
    }
    public SimpleDoubleProperty getWidthProperty()
    {
        return widthProperty;
    }
    public SimpleDoubleProperty getHeightProperty()
    {
        return heightProperty;
    }
    // Setters
    public void setParent(UIComponent parent)
    {
        if(this.parent != null)
        {
            this.parent.children.remove(this.container);
            this.parent.container.getChildren().remove(this.container);
        }

        this.parent = parent;
        this.parent.children.add(this.container);
        this.parent.container.getChildren().add(this.container);
        this.canvas = parent.getCanvas();
        adjustHorizontalTransform(horizontalAnchor);
        adjustVerticalTransform(verticalAnchor);
    }
    public void setWidth(double value)
    {
        this.widthProperty.set(value);
    }
    public void setHeight(double value)
    {
        this.heightProperty.set(value);
    }
    public void setTop(double value)
    {
        topProperty.set(value);
    }
    public void setBottom(double value)
    {
        bottomProperty.set(value);
    }
    public void setLeft(double value)
    {
        leftProperty.set(value);
    }
    public void setRight(double value)
    {
        rightProperty.set(value);
    }
    public void setPivotPoint(PivotPoint pivotPoint)
    {
        this.pivotPoint = pivotPoint;
    }
    public void setHorizontalAnchor(Anchor.Horizontal horizontalAnchor)
    {
        this.horizontalAnchor = horizontalAnchor;
        adjustHorizontalTransform(horizontalAnchor);
    }
    public void setVerticalAnchor(Anchor.Vertical verticalAnchor)
    {
        this.verticalAnchor = verticalAnchor;
        adjustHorizontalTransform(horizontalAnchor);
    }
    protected void adjustHorizontalTransform(Anchor.Horizontal horizontalAnchor)
    {
        if(getParent() == null || getCanvas() == null)
            return;

        switch (horizontalAnchor)
        {
            case LEFT ->
            {
                container.layoutXProperty().bind(leftProperty.multiply(canvas.getUnitProperty()));
                container.prefWidthProperty().bind(widthProperty.multiply(canvas.getUnitProperty()));
            }
            case CENTER ->
            {
                container.layoutXProperty().bind(parent.widthProperty
                                .divide(2)
                                .subtract(widthProperty.divide(2))
                                .add(leftProperty)
                                .subtract(rightProperty)
                                .multiply(canvas.getUnitProperty()));
                container.prefWidthProperty().bind(widthProperty.multiply(canvas.getUnitProperty()));
            }
            case RIGHT ->
            {
                container.layoutXProperty().bind(parent.widthProperty.subtract(rightProperty).multiply(canvas.getUnitProperty()));
                container.prefWidthProperty().bind(widthProperty.multiply(canvas.getUnitProperty()));
            }
            case STRETCH ->
            {
                var wProperty = getParent().getWidthProperty().subtract(leftProperty).subtract(rightProperty);
                widthProperty.bind(wProperty);
                container.layoutXProperty().bind(leftProperty.subtract(widthProperty).multiply(canvas.getUnitProperty()));
                container.prefWidthProperty().bind(widthProperty.multiply(canvas.getUnitProperty()));
            }
        }
    }
    protected void adjustVerticalTransform(Anchor.Vertical verticalAnchor)
    {
        if(getParent() == null || getCanvas() == null)
            return;

        switch (verticalAnchor)
        {
            case TOP ->
            {
                container.layoutYProperty().bind(topProperty.multiply(canvas.getUnitProperty()));
                container.prefHeightProperty().bind(heightProperty.multiply(canvas.getUnitProperty()));
            }
            case CENTER ->
            {
                container.layoutYProperty().bind(parent.heightProperty
                        .divide(2)
                        .subtract(heightProperty.divide(2))
                        .add(topProperty)
                        .subtract(bottomProperty)
                        .multiply(canvas.getUnitProperty()));
                container.prefHeightProperty().bind(heightProperty.multiply(canvas.getUnitProperty()));
            }
            case BOTTOM ->
            {
                container.layoutYProperty().bind(parent.heightProperty.subtract(bottomProperty).subtract(heightProperty).multiply(canvas.getUnitProperty()));
                container.prefHeightProperty().bind(heightProperty.multiply(canvas.getUnitProperty()));
            }
            case STRETCH ->
            {
                var hProperty = getParent().getHeightProperty().subtract(topProperty).subtract(bottomProperty);
                heightProperty.bind(hProperty);
                container.layoutYProperty().bind(topProperty.multiply(getCanvas().getUnitProperty()));
                container.prefHeightProperty().bind(hProperty.multiply(canvas.getUnitProperty()));
            }
        }
    }
}