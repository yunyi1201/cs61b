package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean [][]grids;
    private int numberOfOpen;
    private WeightedQuickUnionUF uf, uuf;
    private int virtualTop, virtualBottom;
    /** create N-by-N grid with all sites initially blocked */
    /** N must be larger than 0 else, throw java.lang.IllegalArgumentException */
    /** grids[i] == false represent block site,  == true represent empty open site > 0 represent full open site */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        /** N * N represent virtual bottom site, N * N + 1 represent virtual top site*/
        uf = new WeightedQuickUnionUF(N * N + 2);
        uuf = new WeightedQuickUnionUF(N * N + 1);
        virtualBottom = N * N + 1;
        virtualTop = N * N;
        grids = new boolean[N][N];
        for(int row = 0; row < N; row += 1) {
            for(int col = 0; col < N; col += 1)
                grids[row][col] = false;
        }
        numberOfOpen = 0;
    }


    private boolean isValidIndex(int index) {
        int N = grids.length;
        if (index < 0 || index >= N) {
            return false;
        }
        return true;
    }

    private int xyTo1D(int row, int col) {
        int N = grids.length;
        return row * N + col;
    }

    private void tryConnectNeighbor(int row, int col, int current) {
        try {
            if (isOpen(row, col)) {
                uf.union(xyTo1D(row, col), current);
                uuf.union(xyTo1D(row, col), current);
            }
        }
        catch (Exception IndexOutOfBoundsException) {
            return;
        }
    }

    /** open the sites(row, clo) if it is not open already */
    /** row, col must be in [0, N-1] else, throw java.lang.IndexOutOfBoundsException */
    public void open(int row, int col) {
        if (!isValidIndex(row) || !isValidIndex(col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (grids[row][col] == true) {
            return ;
        }
        int N = grids.length;
        grids[row][col] = true;
        numberOfOpen += 1;

        if (row == 0) {
            uf.union(xyTo1D(row, col), virtualTop);
            uuf.union(xyTo1D(row, col), virtualTop);
        }

        if (row == N - 1) {
            uf.union(xyTo1D(row, col), virtualBottom);
        }

        int current = xyTo1D(row, col);
        tryConnectNeighbor(row-1, col, current);
        tryConnectNeighbor(row+1, col, current);
        tryConnectNeighbor(row, col+1, current);
        tryConnectNeighbor(row, col-1, current);
    }

    /** is the site open ? */
    public boolean isOpen(int row, int col) {
        if (!isValidIndex(row) || !isValidIndex(col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return grids[row][col];
    }

    /** is the site full? */
    public boolean isFull(int row, int col) {
        if (!isValidIndex(row) || !isValidIndex(col)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return uuf.connected(xyTo1D(row, col), virtualTop);
    }

    /** number of open sites */
    public int numberOfOpenSites() {
        return numberOfOpen;
    }

    /** does the system percolate? */
    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        p.open(1, 2);
        p.open(2,2);
        p.open(2, 3);
        p.open(0, 0);
        System.out.println(p.isFull(1, 2));
        p.open(0, 2);
        System.out.println(p.isFull(1, 2));
        System.out.println(p.percolates());
        p.open(3, 0);
        System.out.println(p.percolates());
    }
}
