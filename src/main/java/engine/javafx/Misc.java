package engine.javafx;

import java.util.stream.IntStream;

public class Misc
{
    private final static int fac = 1000;
    private final static double[] sinLookupTable = IntStream.range(0, 90 * fac + 1)
            .mapToDouble(i -> Math.sin(Math.toRadians((double) i / fac))).toArray();
    private static double sin(double deg)
    {
        deg %= deg + 360;
        if(deg <= 90)
            return sinLookupTable[(int)(deg * fac)];
        else if(deg > 90 && deg <= 180)
            return sinLookupTable[(int)((180 - deg) * fac)];
        else if(deg > 180 && deg <= 270)
            return -sinLookupTable[(int)((deg - 180) * fac)];

        return -sinLookupTable[(int)((360 - deg) * fac)];
    }
}
