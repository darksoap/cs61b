package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int size;
    private int[][] board;
    private int[][] goal;
    private final int BLANK = 0;

    /**
     * Constructs a board from an N-by-N array of board where
     * board[i][j] = tile at row i, column j
     * @param tiles
     */
    public Board(int[][] tiles) {
        size = tiles.length;
        board = tiles.clone();
        goal = new int[size][size];
        board = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0;j < size; j++) {
                goal[i][j] = i * size + j + 1;
                board[i][j] = tiles[i][j];
            }
        }
        goal[size - 1][size - 1] = BLANK;
    }

    /**
     * Returns value of tile at row i, column j (or 0 if blank)
     * @param i
     * @param j
     * @return
     */
    public int tileAt(int i, int j) {
        if (i < 0 || i > size - 1) {
            throw new java.lang.IndexOutOfBoundsException(i + "between 0 and size - 1" );
        }
        if (j < 0 || j > size - 1) {
            throw new java.lang.IndexOutOfBoundsException(j + "between 0 and size - 1" );
        }
        return board[i][j];
    }

    /**
     * Returns the board size N
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * Returns neighbors of this board.
     * SPOILERZ: This is the answer.
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    /**
     * Hamming estimate described below
     * @return
     */
    public int hamming() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0;j < size; j++) {
                if (board[i][j] != goal[i][j] && board[i][j] != BLANK) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Manhattan estimate described below
     * @return
     */
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != goal[i][j] && board[i][j] != BLANK) {
                    int num = board[i][j];
                    int x = (num - 1) / size;                //board的起始数是1 所以要减去1
                    int y = (num - 1) % size;
                    count += (Math.abs(x - i) + Math.abs(y - j));
                }
            }
        }
        return count;
    }

    /**
     * Estimated distance to goal. This method should
     * simply return the results of manhattan() when submitted to
     * Gradescope.
     * @return
     */
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /**
     * Returns true if this board's tile values are the same
     * position as y's
     * @param y
     * @return
     */
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }

        Board tmp = (Board) y;
        if (this.size() != tmp.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != tmp.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board.
     * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
