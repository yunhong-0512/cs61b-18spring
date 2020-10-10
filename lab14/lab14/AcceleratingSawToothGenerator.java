package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator{
    private int period;
    private int state;
    private double scale;
    private boolean flag;

    public AcceleratingSawToothGenerator(int p, double scale) {
        period = p;
        state = -1;
        this.scale = scale;
        flag = false;
    }

    public double next() {
        state = (state + 1);
        if (flag) {
            period = (int) Math.round(period * scale);
            flag = false;
        }
        if (state % period == 0) {
            flag = true;
            state = 0;
        }

        return normalize(state % period);
    }

    private double normalize(int i) {
        return (double) i / period * 2 - 1;
    }
}
