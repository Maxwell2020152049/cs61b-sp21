package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /**
     * Return nearest tile in col/row, from row/col.
     *
     * Example：
     * b likes:
     * |    |    |    |    |
     * |    |   2|    |    |
     * |    |    |    |    |
     * |    |   4|    |    |
     * getNearestTile(b, 1, 0, Side.SOUTH) return b.tile(1, 0)
     * getNearestTile(b, 2, 2, Side.EAST) return b.tile(1, 2)
     * getNearestTile(b, 1, 3, Side.NORTH) return b.tile(1, 2)
     */
    public static Tile getNearestTile(Board b, int col, int row, Side side) {
        int dcol = 0, drow = 0;

        if (side == Side.EAST) {
            dcol = -1;
            drow = 0;
        } else if (side == Side.WEST) {
            dcol = 1;
            drow = 0;
        } else if (side == Side.SOUTH) {
            dcol = 0;
            drow = 1;
        } else if (side == Side.NORTH) {
            dcol = 0;
            drow = -1;
        }

        while (true) {
            if (Model.outOfBoard(b, col, row)) {
                break;
            }
            if (b.tile(col, row) != null) {
                return b.tile(col, row);
            }

            col += dcol;
            row += drow;
        }

        return null;
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.

        int b_size = board.size();
        switch (side) {
            case EAST:
                for (int row = 0; row < b_size; row ++) {
                    for (int col = b_size - 1; col >= 0; col --) {
                        Tile t = getNearestTile(board, col ,row, side);
                        if (t == null) {
                            break;
                        }
                        if (t != board.tile(col, row)) {
                            board.move(col, row, t);
                            changed = true;
                        }

                        Tile t2 = getNearestTile(board, col - 1, row, side);
                        if (t2 == null) {
                            break;
                        }
                        if (t.value() == t2.value()) {
                            board.move(col, row, t2);
                            score += 2 * t.value();
                            changed = true;
                        }
                    }
                }
                break;
            case WEST:
                for (int row = 0; row < b_size; row ++) {
                    for (int col = 0; col < b_size; col ++) {
                        Tile t = getNearestTile(board, col ,row, side);
                        if (t == null) {
                            break;
                        }
                        if (t != board.tile(col, row)) {
                            board.move(col, row, t);
                            changed = true;
                        }

                        Tile t2 = getNearestTile(board, col + 1, row, side);
                        if (t2 == null) {
                            break;
                        }
                        if (t.value() == t2.value()) {
                            board.move(col, row, t2);
                            score += 2 * t.value();
                            changed = true;
                        }
                    }
                }
                break;
            case SOUTH:
                for (int col = 0; col < b_size; col ++) {
                    for (int row = 0; row < b_size; row ++) {
                        Tile t = getNearestTile(board, col ,row, side);
                        if (t == null) {
                            break;
                        }
                        if (t != board.tile(col, row)) {
                            board.move(col, row, t);
                            changed = true;
                        }

                        Tile t2 = getNearestTile(board, col, row + 1, side);
                        if (t2 == null) {
                            break;
                        }
                        if (t.value() == t2.value()) {
                            board.move(col, row, t2);
                            score += 2 * t.value();
                            changed = true;
                        }
                    }
                }
                break;
            case NORTH:
                for (int col = 0; col < b_size; col ++) {
                    for (int row = b_size - 1; row >= 0; row --) {
                        Tile t = getNearestTile(board, col ,row, side);
                        if (t == null) {
                            break;
                        }
                        if (t != board.tile(col, row)) {
                            board.move(col, row, t);
                            changed = true;
                        }

                        Tile t2 = getNearestTile(board, col, row - 1, side);
                        if (t2 == null) {
                            break;
                        }
                        if (t.value() == t2.value()) {
                            board.move(col, row, t2);
                            score += 2 * t.value();
                            changed = true;
                        }
                    }
                }
                break;
        }

        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (b.tile(i, j) == null) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                Tile t = b.tile(i, j);
                if (t != null && t.value() == MAX_PIECE) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Return true if (i, j) is a non-valid index of board b.
     */
    public static boolean outOfBoard(Board b, int i, int j) {
        return i < 0 || i >= b.size() || j < 0 || j >= b.size();
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        if (emptySpaceExists(b)) {
            return true;
        }

        // delta of x, y
        // UP, DOWN, LEFT, RIGHT
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1,-1, 0, 0};

        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                Tile t = b.tile(i, j);
                if (t == null) {
                    continue;
                }

                for (int k = 0; k < dx.length; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (outOfBoard(b, x, y)) {
                        continue;
                    }

                    Tile t2 = b.tile(x, y);
                    if (t.value() == t2.value()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}