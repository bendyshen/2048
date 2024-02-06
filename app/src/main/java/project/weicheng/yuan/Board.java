package project.weicheng.yuan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a game board for the 2048 game. This board maintains tiles, their positions, and
 * operations for merging and sliding tiles.
 */

public class Board {
    private int dimension;
    private Tile[][] tiles;
    private Position[] positions;
    private List<Position> emptyPositions;
    private int score = 0;
    private int bestScore = 0;

    /**
     * Constructs a Board with a specified dimension.
     *
     * @param dimension the size of the board (e.g., a dimension of 4 indicates a 4x4 board)
     */

    public Board(int dimension) {
        setDimension(dimension);
        tiles = new Tile[dimension][dimension];
        positions = new Position[dimension * dimension];
        emptyPositions = new ArrayList<Position>();
        initBoard();
    }

    /**
     * Initializes the board with default tile values, setting all tiles to zero (empty).
     */
    public void initBoard() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                positions[i * dimension + j] = new Position(i, j);
                tiles[i][j] = new Tile(0);
            }
        }
        checkEmptyPositions();
    }

    /**
     * Retrieves a list of positions on the board that are currently without a tile (value is zero).
     *
     * @return a List of {@link Position} objects indicating empty spots on the board.
     */
    public List<Position> getEmptyPositions() {
        return emptyPositions;
    }


    /**
     * Updates the list of positions that are currently empty.
     */
    public void checkEmptyPositions() {
        this.emptyPositions = new ArrayList<Position>();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tiles[i][j].getValue() == 0) {
                    emptyPositions.add(positions[i * dimension + j]);
                }
            }
        }
    }

    /**
     * @return a random positions on the board that are currently empty
     */

    public Position getRandomEmptyPosition() {
        Random rand = new Random();
        Position randomPos = emptyPositions.get(rand.nextInt(emptyPositions.size()));
        emptyPositions.remove(randomPos);
        positions[randomPos.getRow() * dimension + randomPos.getCol()].setEmpty(false);
        return randomPos;
    }

    /**
     * Provides a random tile value based on a predefined probability. This method gives a 90%
     * chance of returning a 2 and a 10% chance of returning a 4.
     *
     * @return a tile value, either 2 or 4.
     */
    private int getRandomTileValue() {
        return Math.random() < 0.9 ? 2 : 4;
    }

    /**
     * Generate two random tiles in empty postion
     */
    public void generateTwoRandomTiles() {
        Position randomPos1 = getRandomEmptyPosition();
        tiles[randomPos1.getRow()][randomPos1.getCol()].setValue(getRandomTileValue());
        if (emptyPositions.size() >= 1) {
            Position randomPos2 = getRandomEmptyPosition();
            tiles[randomPos2.getRow()][randomPos2.getCol()].setValue(getRandomTileValue());
        }
    }
    

    /**
     * Retrieves the tiles currently on the board.
     *
     * @return a 2D array of {@link Tile} objects representing the current board state.
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Retrieves board dimension
     * 
     * @return board dimension
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Retrieves positions on the board
     * 
     * @return - postitions on the board
     */
    public Position[] getPositions() {
        return positions;
    }

    /**
     * Merges the tile at the "from" position into the tile at the "to" position.
     *
     * @param fromPosition the original position of the tile
     * @param toPosition the target position of the tile after merging
     */
    @Generated
    public void mergeTiles(Position fromPosition, Position toPosition) {
        int fromRow = fromPosition.getRow();
        int fromCol = fromPosition.getCol();
        int toRow = toPosition.getRow();
        int toCol = toPosition.getCol();
        Tile from = tiles[fromRow][fromCol];
        Tile to = tiles[toRow][toCol];
        int fromVal = from.getValue();
        int toVal = to.getValue();

        int mergedValue = fromVal + toVal; // Calculate the merged value

        to.setValue(mergedValue); // Set the merged value to the 'to' tile
        tiles[fromRow][fromCol].setValue(0);
        positions[fromRow * dimension + fromCol].setEmpty(true);
        positions[toRow * dimension + toCol].setEmpty(false);
        positions[toRow * dimension + toCol].setMerged(true);

        incrementScore(mergedValue); // Update the score with the merged value
    }

    /**
     * Slides the tiles in the given direction.
     * <p>
     * The method slides the tiles in the board towards the direction provided while combining tiles
     * of the same value and updating the board state accordingly.
     * </p>
     *
     * @param dir The direction in which to slide the tiles. Can be UP, DOWN, LEFT, or RIGHT.
     */
    @Generated
    public void slideTile(Direction dir) {
        resetMerged();
        switch (dir) {
            case UP:
                for (int col = 0; col < dimension; col++) {
                    for (int row = 1; row < dimension; row++) { // Start from the second row
                        if (tiles[row][col].getValue() != 0) { // If there is a tile available for
                                                               // sliding
                            int newRow = row;
                            // Find the top-most position that a tile can be moved to
                            while (newRow - 1 >= 0 && tiles[newRow - 1][col].getValue() == 0) {
                                newRow--;
                            }
                            if (newRow >= 1
                                    && (tiles[newRow - 1][col].getValue() == tiles[row][col]
                                            .getValue())
                                    && (!positions[(newRow - 1) * dimension + col].isMerged())) {
                                mergeTiles(positions[row * dimension + col],
                                        positions[(newRow - 1) * dimension + col]);

                            } else {
                                // If the new position is different from the original one
                                if (newRow != row) {
                                    tiles[newRow][col].setValue(tiles[row][col].getValue());
                                    tiles[row][col].setValue(0);
                                    positions[newRow * dimension + col].setEmpty(false);
                                    positions[row * dimension + col].setEmpty(true);
                                }
                            }
                        }
                    }
                }
                break;

            case DOWN:
                for (int col = 0; col < dimension; col++) {
                    for (int row = dimension - 2; row >= 0; row--) {
                        if (tiles[row][col].getValue() != 0) {
                            int newRow = row;
                            while (newRow + 1 < dimension
                                    && tiles[newRow + 1][col].getValue() == 0) {
                                newRow++;
                            }
                            if (newRow + 2 <= dimension
                                    && (tiles[newRow + 1][col].getValue() == tiles[row][col]
                                            .getValue())
                                    && (!positions[(newRow + 1) * dimension + col].isMerged())) {
                                mergeTiles(positions[row * dimension + col],
                                        positions[(newRow + 1) * dimension + col]);

                            } else {
                                // If the new position is different from the original one
                                if (newRow != row) {
                                    tiles[newRow][col].setValue(tiles[row][col].getValue());
                                    tiles[row][col].setValue(0);
                                    positions[newRow * dimension + col].setEmpty(false);
                                    positions[row * dimension + col].setEmpty(true);
                                }
                            }
                        }
                    }
                }
                break;

            case LEFT:
                for (int row = 0; row < dimension; row++) {
                    for (int col = 1; col < dimension; col++) {
                        if (tiles[row][col].getValue() != 0) {
                            int newCol = col;
                            while (newCol - 1 >= 0 && tiles[row][newCol - 1].getValue() == 0) {
                                newCol--;
                            }
                            if (newCol >= 1
                                    && (tiles[row][newCol - 1].getValue() == tiles[row][col]
                                            .getValue())
                                    && (!positions[(row) * dimension + newCol - 1].isMerged())) {
                                mergeTiles(positions[row * dimension + col],
                                        positions[row * dimension + newCol - 1]);

                            } else {
                                // If the new position is different from the original one
                                if (newCol != col) {
                                    tiles[row][newCol].setValue(tiles[row][col].getValue());
                                    tiles[row][col].setValue(0);
                                    positions[row * dimension + newCol].setEmpty(false);
                                    positions[row * dimension + col].setEmpty(true);
                                }
                            }
                        }
                    }
                }
                break;

            case RIGHT:
                for (int row = 0; row < dimension; row++) {
                    for (int col = dimension - 2; col >= 0; col--) {
                        if (tiles[row][col].getValue() != 0) {
                            int newCol = col;
                            while (newCol + 1 < dimension
                                    && tiles[row][newCol + 1].getValue() == 0) {
                                newCol++;
                            }
                            if (newCol + 2 <= dimension
                                    && (tiles[row][newCol + 1].getValue() == tiles[row][col]
                                            .getValue())
                                    && (!positions[(row) * dimension + newCol + 1].isMerged())) {
                                mergeTiles(positions[row * dimension + col],
                                        positions[row * dimension + newCol + 1]);

                            } else {
                                // If the new position is different from the original one
                                if (newCol != col) {
                                    tiles[row][newCol].setValue(tiles[row][col].getValue());
                                    tiles[row][col].setValue(0);
                                    positions[row * dimension + newCol].setEmpty(false);
                                    positions[row * dimension + col].setEmpty(true);
                                }
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
        checkEmptyPositions();
    }

    /**
     * Sets the dimension of the board.
     * <p>
     * This method allows for setting the size of the game board. The minimum allowable dimension is
     * 4x4. If a value below 4 is provided, it throws an IllegalArgumentException.
     * </p>
     *
     * @param dimension The desired size of one side of the square game board.
     * @throws IllegalArgumentException if the provided dimension is less than 4.
     */

    public void setDimension(int dimension) {
        if (dimension < 4) {
            throw new IllegalArgumentException("Board size must be at least 4x4.");
        }
        this.dimension = dimension;
    }

    /**
     * Resets the merged status of all positions.
     */

    public void resetMerged() {
        for (Position p : positions) {
            p.setMerged(false);
        }
    }

    /**
     * @return the current score of the game
     */

    public int getScore() {
        return score;
    }

    /**
     * Sets the current score.
     *
     * @param score The value to set as the current score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Increments the current score by the specified value.
     * <p>
     * If the new score exceeds the best score, it updates the best score with the current score.
     * </p>
     *
     * @param value The value by which the current score should be increased.
     */
    public void incrementScore(int value) {
        this.score += value;
        if (this.score > this.bestScore) {
            this.bestScore = this.score;
        }
    }
}


