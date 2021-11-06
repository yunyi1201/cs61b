package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import java.util.Random;

public class PercolationStats {

    private int[] res;
    private int T;
    private Random random = new Random();

    /** perform T independent experiments on an N-by-N grid */
    /** N, T must be larger than 0 */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        res = new int[T];
        this.T = T;
        for (int i = 0; i < T; i++) {
            Percolation tmpTest = pf.make(N);
            while (!tmpTest.percolates()) {
                tmpTest.open(random.nextInt(N), random.nextInt(N));
            }
            res[i] = tmpTest.numberOfOpenSites();
        }
    }

    /** sample mean of percolation threshold */
    public double mean() {
        return StdStats.mean(res);
    }

    /** sample standard deviation of percolation threshold */
    public double stddev() {
        /**
        int sum = 0;
        for (int i : res) {
            sum += (i - mean()) * (i - mean());
        }
         */
        return StdStats.stddev(res);
    }

    /** low endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev() / T);
    }

    /** high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + 1.96 * Math.sqrt(stddev() / T);
    }

}
