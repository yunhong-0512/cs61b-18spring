package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private Percolation grid;
    private int N;
    private double[] arr; //track every experiment result.

    /**
     * perform T independent experiments on an N-by-N grid
     * @param N
     * @param T
     * @param pf
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        arr = new double[T];
        for (int i = 0; i < T; i++) {
            grid = pf.make(N);
            arr[i] = experiment();
        }
    }

    /**
     * do experiment
     * @return
     */
    private double experiment() {
        double i = 0.0;
        while (!grid.percolates()) {
            i += 1;
            int col = StdRandom.uniform(N);
            int row = StdRandom.uniform(N);
            grid.open(row, col);
        }
        return i / (double) (N * N);
    }

    /**
     * return sample mean of percolation threshold
     * @return
     */
    public double mean() {
        return StdStats.mean(arr);
    }

    /**
     * return sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        return StdStats.stddev(arr);
    }

    /**
     * return low endpoint of 95% confidence interval
     * @return
     */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(arr.length);
    }

    /**
     * return high endpoint of 95% confidence interval
     * @return
     */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(arr.length);
    }
}


