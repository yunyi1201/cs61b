package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{

    private final int BLANK = 0;
    private int[][] boards;

    public Board(int[][] tiles) {
        int length = tiles.length;
        boards = new int[length][length];
        for (int i = 0; i < length; i += 1) {
            for (int j = 0; j < length; j += 1)
                boards[i][j] = tiles[i][j];
        }
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

    private void checkIndex(int i, int j) {
        if (i < 0 || i >= boards.length || j < 0 || j >= boards.length)
            throw new java.lang.IndexOutOfBoundsException();
    }

    public int tileAt(int i, int j) {
        checkIndex(i,j);
        return boards[i][j];
    }

    public int size() {
        return boards.length;
    }

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

    public int hamming() {
        int count = 0;

        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                int position = i * size() + j + 1;
                if (tileAt(i, j) != position && tileAt(i, j) != BLANK) {
                    count++;
                }

            }
        }

        return count;
    }

    public int manhattan() {
        int count = 0;

        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                int currentNumInThePosition = this.boards[i][j];
                if (currentNumInThePosition != 0) {
                    int expectedRow = (currentNumInThePosition - 1) / boards.length;
                    int expectedCol = (currentNumInThePosition - 1) % boards.length;
                    count += (Math.abs(i - expectedRow) + Math.abs(j - expectedCol));
                }
            }
        }
        return count;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        } else if (y.getClass() == this.getClass()) {
            Board comp = (Board) y;
            if (comp.size() == this.size()) {
                for (int i = 0; i < this.size(); i++) {
                    for (int j = 0; j < this.size(); j++) {
                        if (comp.tileAt(i, j) != this.tileAt(i, j)) {
                            return false;
                        }
                    }
                }

                return true;
            }
        }
        return false;
    }
}
