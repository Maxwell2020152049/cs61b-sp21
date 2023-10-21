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
}
