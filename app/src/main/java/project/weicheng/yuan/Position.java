package project.weicheng.yuan;

/**
 * Represents a position on the game board defined by its row and column indices. Each position can
 * be empty or occupied, and a merge operation can be performed at a position.
 */
public class Position {

    /** Row index of the position on the game board. */
    private int row;

    /** Column index of the position on the game board. */
    private int col;

    /** Indicates whether the position is empty or occupied. */
    private boolean isEmpty;

    /** Indicates if a merge operation has taken place at this position in the current move. */
    private boolean isMerged;

    /**
     * Initializes a position with specified row and column indices, defaulting to empty and not
     * merged.
     *
     * @param row the row index of the position.
     * @param col the column index of the position.
     */
    public Position(int row, int col) {
        setRow(row);
        setCol(col);
        isEmpty = true;
        isMerged = false;
    }

    /**
     * Sets the row index of this position.
     *
     * @param row the row index to set.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Sets the column index of this position.
     *
     * @param col the column index to set.
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Checks if a merge operation has taken place at this position.
     *
     * @return true if merged, false otherwise.
     */
    public boolean isMerged() {
        return isMerged;
    }

    /**
     * Sets the merge status for this position.
     *
     * @param isMerged true if a merge operation has taken place, false otherwise.
     */
    public void setMerged(boolean isMerged) {
        this.isMerged = isMerged;
    }

    /**
     * Returns the row index of this position.
     *
     * @return the row index.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column index of this position.
     *
     * @return the column index.
     */
    public int getCol() {
        return col;
    }

    /**
     * Checks if this position is empty.
     *
     * @return true if the position is empty, false otherwise.
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * Sets the occupancy status of this position.
     *
     * @param isEmpty true if the position is empty, false otherwise.
     */
    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    /**
     * Overrides the default hashCode method to generate a unique hash code for each position based
     * on its row and column indices.
     *
     * @return the computed hash code.
     */
    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
}
