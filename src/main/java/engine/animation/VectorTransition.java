package engine.animation;

import engine.mathUtil.Vec2;
import engine.mathUtil.Vec3;
import engine.mathUtil.Vec4;
import engine.mathUtil.Vector;
import engine.util.Time;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class VectorTransition<T extends Vector<T>> extends Animation
{
    public VectorTransition(T startValue, T endValue, double duration, BiConsumer<Double, T> animationUpdate, boolean reverse)
    {
        super(duration, reverse);
        this.startValue = startValue;
        this.endValue = endValue;
        this.animationUpdate = animationUpdate;

        if(startValue instanceof Vec2 start)
        {
            //final Vec2 val2 = new Vec2(0,0);
            //Vec2 end = (Vec2) endValue;
            //val2.x = start.x + (end.x - start.x) * iProgress;
            //val2.y = start.y + (end.y - start.y) * iProgress;

            //dx = end.x - start.x;
            //dy = end.y - start.y;
        }
    }
    public VectorTransition(T startValue, T endValue, double duration, BiConsumer<Double, T> animationUpdate)
    {
        this(startValue, endValue, duration, animationUpdate, false);
    }

    @Override
    protected void onUpdate() {

    }
    private final T startValue;
    private final T endValue;
    private final BiConsumer<Double, T> animationUpdate;

    private double dx;
    private double dy;
    private double dz;
    private double dw;

    /*
    @Override
    protected void onUpdate(double progress)
    {
        double normalizedProgress = progress * reciprocalDuration;

        if(onHalfComplete != null && !playedHalfComplete && normalizedProgress >= 1)
        {
            playedHalfComplete = true;
            onHalfComplete.run();
        }

        normalizedProgress = normalizedProgress > 1 ? 2 - normalizedProgress : normalizedProgress;

        final var iProgress = interpolator.apply(normalizedProgress);

        long startTime1 = System.nanoTime();
        //Vector<T> val = startValue + (endValue - startValue) * interpolator.apply(normalizedProgress);
        T val = startValue.add(endValue.sub(startValue).mult(iProgress));
        long endTime1 = System.nanoTime();

        long startTime2 = System.nanoTime();
        if(startValue instanceof Vec2 start)
        {
            final Vec2 val2 = new Vec2(0,0);
            Vec2 end = (Vec2) endValue;
            val2.x = start.x + (end.x - start.x) * iProgress;
            val2.y = start.y + (end.y - start.y) * iProgress;
        }
        long endTime2 = System.nanoTime();

        long executionTime1 = endTime1 - startTime1;
        long executionTime2 = endTime2 - startTime2;

        //System.out.println(executionTime1 + " " +  executionTime2);

        //System.out.println(Time.deltaTime);

        if(startValue instanceof Vec3 start)
        {
            final Vec3 val3 = new Vec3(0,0, 0);
            Vec3 end = (Vec3) endValue;
            val3.x = start.x + (end.x - start.x) * interpolator.apply(normalizedProgress);
            val3.y = start.y + (end.y - start.y) * interpolator.apply(normalizedProgress);
            val3.y = start.y + (end.y - start.y) * interpolator.apply(normalizedProgress);
        }

        else if(startValue instanceof Vec4 start)
        {
            final Vec4 val4 = new Vec4(0,0,0,0);
            Vec4 end = (Vec4) endValue;
            val4.x = start.x + (end.x - start.x) * interpolator.apply(normalizedProgress);
            val4.y = start.y + (end.y - start.y) * interpolator.apply(normalizedProgress);
            val4.z = start.z + (end.z - start.z) * interpolator.apply(normalizedProgress);
            val4.w = start.w + (end.w - start.w) * interpolator.apply(normalizedProgress);
        }

        animationUpdate.accept(normalizedProgress, val);
    }

     */
}