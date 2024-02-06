package project.weicheng.yuan;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;
    private final int DIMENSION = 4;

    @BeforeEach
    public void setUp() {
        board = new Board(DIMENSION);
    }

    @Test
    public void testBoardInitialization() {
        assertEquals(DIMENSION, board.getDimension(),
                "Board dimension should match the initialized value.");

        Tile[][] tiles = board.getTiles();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                assertEquals(0, tiles[i][j].getValue(),
                        "All tiles should be initialized with value 0.");
            }
        }
    }

    @Test
    public void testEmptyPositionsAfterInitialization() {
        assertEquals(DIMENSION * DIMENSION, board.getEmptyPositions().size(),
                "All positions should be empty after initialization.");
    }

    @Test
    public void testSlideTile() {
        board.generateTwoRandomTiles();
        int initialEmptyPositions = board.getEmptyPositions().size();
        board.slideTile(Direction.UP);
        assertEquals(initialEmptyPositions, board.getEmptyPositions().size(),
                "Sliding up with only two tiles shouldn't change the number of empty positions");
    }

    @Test
    public void testGenerateTwoRandomTiles() {
        board.generateTwoRandomTiles();
        int nonZeroCount = 0;
        Tile[][] tiles = board.getTiles();

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (tiles[i][j].getValue() != 0) {
                    nonZeroCount++;
                }
            }
        }

        assertTrue(nonZeroCount <= 2 && nonZeroCount > 0,
                "There should be 1 or 2 tiles with non-zero value after generating two random tiles.");
    }

    @Test
    public void testScoreIncrement() {
        int initialScore = board.getScore();
        board.incrementScore(10);
        assertEquals(initialScore + 10, board.getScore(),
                "Score should be incremented by the specified value.");
    }

    @Test
    public void testBoardDimensionSetting() {
        assertThrows(IllegalArgumentException.class, () -> {
            board.setDimension(2);
        }, "Setting dimension less than 4 should throw an IllegalArgumentException.");
    }

    @Test
    public void testScore() {
        board.incrementScore(10);
        assertEquals(10, board.getScore(), "Score should be incremented correctly");

        board.incrementScore(20);
        assertEquals(30, board.getScore(), "Score should be incremented again correctly");

        board.setScore(0);
        assertEquals(0, board.getScore(), "Score should be reset to zero");
    }

    @Test
    public void testIllegalDimension() {
        assertThrows(IllegalArgumentException.class, () -> board.setDimension(2),
                "Setting dimension less than 4 should throw IllegalArgumentException");
    }


}
