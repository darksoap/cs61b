package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int size;
    private WeightedQuickUnionUF site;

    private boolean[][] open;
    private int openNums;
    private int top;
    private int bottom;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException(N + " is not smaller or equal to 0! ");
        }

        site = new WeightedQuickUnionUF(N * N + 2);
        size = N;
        open = new boolean[N][N];
        top = N * N;
        bottom = N * N + 1;
    }

    /**
     * Change (row,col) to site num.
     * @param row
     * @param col
     * @return
     */
    private int xyTo1D(int row, int col) {
        int i = row * size + col;
        if (i >= size * size || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return i;
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        open[row][col] = true;
        openNums += 1;
        unionAfterOpen(row, col);
    }

    /**
     * union the open site
     * @param row
     * @param col
     */
    private void unionAfterOpen(int row, int col) {
        //top row check
        if (row == 0) {
            site.union(xyTo1D(row, col), top);
        }
        //bottom
        if (row == size - 1) {
            site.union(xyTo1D(row, col), bottom);
        }

        //up
        if (row > 0 && isOpen(row - 1, col)) {
            site.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        //down
        if (row < size - 1 && isOpen(row + 1, col)) {
            site.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        //left
        if (col > 0 && isOpen(row, col - 1)) {
            site.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
        //right
        if (col < size - 1 && isOpen(row, col + 1)) {
            site.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        return open[row][col];
    }

    public boolean isFull(int row, int col) {
        int n = xyTo1D(row, col);
        return  (isOpen(row, col) && site.connected(n, top));
    }

    public int numberOfOpenSites() {
        return openNums;
    }

    public boolean percolates() {
        return site.connected(top, bottom);
    }

    public static void main(String[] args) {
    }
}
