package game2048;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test Model class.
 *
 * @author wanfeng Huang
 */
public class TestModel2 {
    /**
     * Test all valid index of board.
     */
    @Test
    public void testOutOfBoard() {
        int[][] input = new int[][]{
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
        };

        Board b = new Board(input, 0);

        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                boolean isOutOfBoard = Model.outOfBoard(b, i, j);
                assertFalse(isOutOfBoard);
            }
        }
    }

    /**
     * Test some non-valid index of board.
     */
    @Test
    public void testOutOfBoard2() {
        int[][] input = new int[][]{
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
        };

        Board b = new Board(input, 0);

        int b_size = b.size();

        boolean isOutOfBoard = Model.outOfBoard(b, b_size, b_size);
        assertTrue(isOutOfBoard);

        isOutOfBoard = Model.outOfBoard(b, b_size, b_size - 1);
        assertTrue(isOutOfBoard);

        isOutOfBoard = Model.outOfBoard(b, b_size - 1, b_size);
        assertTrue(isOutOfBoard);

        isOutOfBoard = Model.outOfBoard(b, - 1, -1);
        assertTrue(isOutOfBoard);

        isOutOfBoard = Model.outOfBoard(b, - 1, 0);
        assertTrue(isOutOfBoard);

        isOutOfBoard = Model.outOfBoard(b, 0, -1);
        assertTrue(isOutOfBoard);
    }

    /**
     * Test getNearestTile.
     */
    @Test
    public void testGetNearestTile() {
        int[][] input = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 4, 0, 0},
        };

        Board b = new Board(input, 0);

        Tile tile = Model.getNearestTile(b, 1, 0, Side.SOUTH);
        assertEquals(b.tile(1, 0), tile);

        tile = Model.getNearestTile(b, 2, 2, Side.EAST);
        assertEquals(b.tile(1, 2), tile);

        tile = Model.getNearestTile(b, 1, 3, Side.NORTH);
        assertEquals(b.tile(1, 1), tile);

        tile = Model.getNearestTile(b, 3, 3, Side.NORTH);
        assertEquals(null, tile);

        tile = Model.getNearestTile(b, 0, 0, Side.WEST);
        assertEquals(b.tile(1, 0), tile);
    }
}
