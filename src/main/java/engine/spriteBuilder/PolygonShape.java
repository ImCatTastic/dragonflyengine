package engine.spriteBuilder;

import engine.core.BoundingBox;
import engine.util.RGBColor;
import engine.util.RenderData;
import engine.util.math.Vec2;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.canvas.GraphicsContext;

public class PolygonShape extends PaintableShape
{
    private final double x;
    private final double y;
    private final int sides;
    private final double angleOffset;
    private final double radius;
    private final boolean centerShape;
    public PolygonShape(double x, double y, int sides, double angleOffset, double radius, boolean centerShape)
    {
        this.x = x;
        this.y = y;
        this.sides = sides;
        this.angleOffset = angleOffset;
        this.radius = radius;
        this.centerShape = centerShape;

        boundingBox = new BoundingBox(x, y, radius, radius);
    }

    @Override
    public void draw(GraphicsContext gc, RenderData data, Vec2 dimensions)
    {
        var mod = SpriteBuilder.getModifier();
        var verticesX = new double[sides];
        var verticesY = new double[sides];
        double circumRadius = radius * 0.5 / mod * data.unit; //TODO: conversion missing

        for (int i = 0; i < sides; i++)
        {
            double angle = Math.toRadians(i * (360d / sides) + angleOffset);

            verticesX[i] = circumRadius * Math.cos(angle);
            verticesY[i] = circumRadius * Math.sin(angle);
        }

        gc.beginPath();

        gc.moveTo(verticesX[0], verticesY[0]);
        for (int i = 0; i < sides; i++)
        {
            var neighbourIndex1 = i > 0 ? i - 1 : sides - 1;
            var neighbourIndex2 = i < sides - 1 ? i + 1 : 0;

            gc.lineTo(verticesX[i], verticesY[i]);
        }

        gc.closePath();
        gc.setStroke(RGBColor.CYAN.fx);
        gc.stroke();

        gc.setStroke(RGBColor.GREEN.fx);
        gc.strokeRect(
                -circumRadius,
                -circumRadius,
                circumRadius * 2,
                circumRadius * 2
        );
    }
}
