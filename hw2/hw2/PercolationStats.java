package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import java.util.Random;

public class PercolationStats {

    private int N;
    private double[] res;
    private int T;

    /** perform T independent experiments on an N-by-N grid */
    /** N, T must be larger than 0 */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        res = new double[T];
        this.N = N;
        this.T = T;
        for (int i = 0; i < T; i++) {
            Percolation tmpTest = pf.make(N);
            while (!tmpTest.percolates()) {
               int site = pickRandomSite(tmpTest);
               tmpTest.open(site / N, site % N);
            }
            res[i] = tmpTest.numberOfOpenSites() * 1.0 / (N * N);
        }
    }

    private int pickRandomSite(Percolation p) {
        int site = StdRandom.uniform(0, N*N);
        while (p.isOpen(site / N, site % N)) {
            site = StdRandom.uniform(0, N * N);
        }
        return site;
    }

    /** sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(res);
    }

    /** sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(res);
    }

    /** low endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

}
