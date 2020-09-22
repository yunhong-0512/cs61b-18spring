package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF grid; //N-by-N grid
    private WeightedQuickUnionUF check; //to avoid backwash
    private int[] arr;  //  n^2 array to represent site status 0 - blocked 1 - open
    private int N;
    private int openNum; // represent the number of open sites

    /**
     * constructor, create N-by-N grid, with all sites initially blocked
     * @param N
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new WeightedQuickUnionUF(N * N + 2);
        check = new WeightedQuickUnionUF(N * N + 1);
        arr = new int[N * N + 2];
        this.N = N;
        openNum = 0;
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = 0;
        }
        arr[N * N + 1] = 1;
    }

    /**
     * calculate the index of the site in the arr
     * @param row
     * @param col
     * @return
     */
    private int calcIndex(int row, int col) {
        return row * N + col + 1;
    }

    /**
     * open the site (row, col) if it is not open already.
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= N || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            arr[calcIndex(row, col)] = 1;
            openNum += 1;
            if (col <= N - 2 && isOpen(row, col + 1)) {
                grid.union(calcIndex(row, col + 1), calcIndex(row, col));
                check.union(calcIndex(row, col + 1), calcIndex(row, col));
            }
            if (col >= 1 && isOpen(row, col - 1)) {
                grid.union(calcIndex(row, col - 1), calcIndex(row, col));
                check.union(calcIndex(row, col - 1), calcIndex(row, col));
            }
            if (row <= N - 2 && isOpen(row + 1, col)) {
                grid.union(calcIndex(row + 1, col), calcIndex(row, col));
                check.union(calcIndex(row + 1, col), calcIndex(row, col));
            } else if (row == N - 1) {
                grid.union(N * N + 1, calcIndex(row, col));
            }
            if (row >= 1 && isOpen(row - 1, col)) {
                grid.union(calcIndex(row - 1, col), calcIndex(row, col));
                check.union(calcIndex(row - 1, col), calcIndex(row, col));
            } else if (row == 0) {
                grid.union(0, calcIndex(row, col));
                check.union(0, calcIndex(row, col));
            }
        }
    }

    /**
     * is the site (row, col) open?
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= N || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        return arr[calcIndex(row, col)] == 1;
    }

    /**
     * is the site (row, col) full?
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= N || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        return grid.find(0) == grid.find(calcIndex(row, col))
                && check.find(0) == check.find(calcIndex(row, col));
    }

    /**
     * return number of open sites
     * @return
     */
    public int numberOfOpenSites() {
        return openNum;
    }

    /**
     * return true if the system percolates.
     * @return
     */
    public boolean percolates() {
        return grid.find(0) == grid.find(N * N + 1);
    }

    public static void main(String[] args) {

    }

}
