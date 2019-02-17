package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] result;

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

        result = new double[T];

        for (int i = 0; i < T; i += 1) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int rdmRow = StdRandom.uniform(N);
                int rdmCol = StdRandom.uniform(N);
                while (p.isOpen(rdmRow, rdmCol)){
                    rdmRow = StdRandom.uniform(N);
                    rdmCol = StdRandom.uniform(N);
                }
                p.open(rdmRow, rdmCol);
            }
            result[i] = p.numberOfOpenSites() / (N * N);
        }
    }

    /**
     * sample mean of percolation threshold
     * @return
     */
    public double mean() {
        return StdStats.mean(result);
    }

    /**
     * sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        return StdStats.stddev(result);
    }

    /**
     * low endpoint of 95% confidence interval
     * @return
     */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(result.length);
    }

    /**
     * high endpoint of 95% confidence interval
     * @return
     */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(result.length);
    }
}
