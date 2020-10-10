package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator{
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int p) {
        period = p;
        state = -1;
    }

    public double next() {
        state = (state + 1);
        int weirdState = state & (state >>> 3) % period;
        return normalize(weirdState % period);
    }

    private double normalize(int i) {
        return (double) i / period * 2 - 1;
    }
}
