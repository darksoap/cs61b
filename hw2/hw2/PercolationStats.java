package hw2;

import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    private int t;
    private Percolation per;

    /**
     * perform T independent experiments on an N-by-N grid
     * @param N
     * @param T
     * @param pf
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        per = pf.make(N);
        int[] random = new int[N];
        for (int i = 0; i < N; i += 1) {
            random[i] = i;
        }
        for (int i = 0; i < T; i += 1) {
            int row = StdRandom.discrete(random);
            int col = StdRandom.discrete(random);
            while (per.isOpen(row, col)) {
                row = StdRandom.discrete(random);
                col = StdRandom.discrete(random);
            }
            per.open(row, col);
            if (per.percolates()) {
                t = i;
                break;
            }
        }
    }

    /**
     * sample mean of percolation threshold
     * @return
     */
    public double mean() {
        int fraction = 0;
        for (int i = 1; i <= per.numberOfOpenSites(); i += 1 ) {
            fraction += i;
        }
        return fraction / t;
    }

    /**
     * sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        double fraction = 0;
        for (int i = 1; i <= per.numberOfOpenSites(); i += 1) {
            fraction += (i - mean()) * (i - mean());
        }

        return Math.pow(fraction / t - 1, 1 / 2);
    }

    /**
     * low endpoint of 95% confidence interval
     * @return
     */
    public double confidenceLow() {
        double result = mean() - (1.96 * stddev()) / Math.pow(t, 1 / 2);
        return result;
    }

    /**
     * high endpoint of 95% confidence interval
     * @return
     */
    public double confidenceHigh() {
        double result = mean() + (1.96 * stddev()) / Math.pow(t, 1 / 2);
        return result;
    }
}
