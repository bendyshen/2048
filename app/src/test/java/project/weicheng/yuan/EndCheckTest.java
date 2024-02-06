package project.weicheng.yuan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EndCheckTest {
    
    private Board board;
    private EndCheck endCheck;

    @BeforeEach
    public void setup() {
        // For simplicity, let's assume the board is 4x4.
        board = new Board(4);
        endCheck = new EndCheck(board);
    }

    @Test
    public void testHasWon() {
        // Simulate a board where one tile has the value 2048.
        board.getTiles()[1][2].setValue(2048);
        assertTrue(endCheck.hasWon());

        // Reset the tile value and check again.
        board.getTiles()[1][2].setValue(0);
        assertFalse(endCheck.hasWon());
    }


    @Test
    public void testHasLostWithPossibleMoves() {
        // Simulate a board with a possible horizontal merge.
        board.getTiles()[2][1].setValue(4);
        board.getTiles()[2][2].setValue(4);
        assertFalse(endCheck.hasLost());

        // Simulate a board with a possible vertical merge.
        board.getTiles()[2][2].setValue(8);
        board.getTiles()[3][2].setValue(8);
        assertFalse(endCheck.hasLost());
    }

    @Test
    public void testHasLostWithEmptyTiles() {
        // Simulate a board with an empty tile.
        board.getTiles()[3][3].setValue(0);
        assertFalse(endCheck.hasLost());
    }
}
