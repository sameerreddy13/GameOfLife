package life;
/**
 * Created by Sameer on 2/27/17.
 */
public class NaiveGame {

    /*
     Implements n * n size grid for Life. Can specify grid size with argument n.
     Cells outside grid are dead cells.
      */
    private class Grid {
        boolean matrix[][];
        int n;

        public Grid(int n) {
            this.matrix = new boolean[n][n];
            this.n = n;
        }

        private boolean inBounds(int x, int y) {
            return (x >= 0 && x < n) && (y >= 0 && y < n);
        }

        void checkBounds(int x, int y) {
            if (!inBounds(x, y)) {
                throw new IllegalArgumentException("Coordinates out of bounds");
            }
        }

        boolean getCell(int x, int y) {
            return inBounds(x, y) && matrix[x][y];
        }

        void updateCell(int x, int y, boolean alive) {
            checkBounds(x, y);
            matrix[x][y] = alive;
        }
    }

    private Grid cells;

    public NaiveGame(int n) {
        this.cells = new Grid(n);
    }

    private boolean[] getNeighbors(int x, int y) {
        boolean[] neighbors = new boolean[8];
        int ctr = 0;
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (i == j) {
                    continue;
                }
                neighbors[ctr] = cells.getCell(i, j);
                ctr++;
            }
        }
        return neighbors;
    }

    /*
    Implements Game Of Life's rules to determine a cell's new value after a tick
     */
    private boolean getNextTick(int x, int y) {
        boolean[] neighbors = getNeighbors(x, y);
        boolean c = getCell(x, y);
        int alive = 0;
        for (boolean b : neighbors) {
            if (b) {
                alive++;
            }
        }

        // Return true if c is alive and has exactly 2 or exactly 3 alive neighbors
        if (c) {
            return (alive == 2 || alive == 3);
        }
        // Return true if c is dead and has exactly 3 alive neighbors
        return alive == 3;
    }

    boolean getCell(int x, int y) {
        return cells.getCell(x, y);
    }

    /*
    Return n for grid of size n * n.
     */
    int getGridSize() {
        return cells.n;
    }

    void updateOnTick() {
        int n = getGridSize();
        Grid new_cells = new Grid(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean new_val = getNextTick(i, j);
                new_cells.updateCell(i, j, new_val);
            }
        }
        cells = new_cells;
    }


}
