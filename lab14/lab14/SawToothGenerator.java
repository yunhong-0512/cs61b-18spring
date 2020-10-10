package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class SawToothGenerator implements Generator{
    private int period;
    private int state;

    public SawToothGenerator(int p) {
        period = p;
        state = -1;
    }

    public double next() {
        state = (state + 1);
        return normalize(state % period);
    }

    private double normalize(int i) {
        return (double) i / period * 2 - 1;
    }
}
