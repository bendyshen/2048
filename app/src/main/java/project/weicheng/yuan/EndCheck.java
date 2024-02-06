package project.weicheng.yuan;

/**
 * Provides functionalities to check end-game conditions for the game board.
 */
public class EndCheck {
    /** The game board instance associated with this end check. */
    private Board board;

    /**
     * Constructs an EndCheck object for the provided game board.
     *
     * @param board The game board to check end-game conditions for.
     */
    public EndCheck(Board board) {
        this.board = board;
    }

    /**
     * Checks if a win condition (a tile with value 2048) has been met on the board.
     *
     * @return true if a winning condition is met, false otherwise.
     */
    public boolean hasWon() {
        for (int i = 0; i < board.getDimension(); i++) {
            for (int j = 0; j < board.getDimension(); j++) {
                if (board.getTiles()[i][j].getValue() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a loss condition (no moves available) has been met on the board.
     *
     * @return true if a losing condition is met, false otherwise.
     */
    public boolean hasLost() {
        int dimension = board.getDimension();
        Tile[][] tiles = board.getTiles();
    
        // Check for possible merges first
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Tile currentTile = tiles[i][j];
                if (currentTile.getValue() == 0) {
                    return false; // If there's an empty tile, a move is still possible
                }
    
                // Check for a possible merge to the right
                if (j < dimension - 1) {
                    Tile rightTile = tiles[i][j + 1];
                    if (currentTile.getValue() == rightTile.getValue()) {
                        return false;
                    }
                }
    
                // Check for a possible merge downwards
                if (i < dimension - 1) {
                    Tile bottomTile = tiles[i + 1][j];
                    if (currentTile.getValue() == bottomTile.getValue()) {
                        return false;
                    }
                }
    
                // Check for a possible merge to the left
                if (j > 0) {
                    Tile leftTile = tiles[i][j - 1];
                    if (currentTile.getValue() == leftTile.getValue()) {
                        return false;
                    }
                }
    
                // Check for a possible merge upwards (optional since you've already checked downwards for the previous row)
                if (i > 0) {
                    Tile topTile = tiles[i - 1][j];
                    if (currentTile.getValue() == topTile.getValue()) {
                        return false;
                    }
                }
            }
        }
    
        // No possible merges and no empty tiles found
        return true;
    }
}
    
