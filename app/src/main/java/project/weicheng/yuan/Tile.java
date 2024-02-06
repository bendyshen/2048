package project.weicheng.yuan;

/**
 * Represents a tile within the game board which has a specific value.
 */
public class Tile implements Comparable<Tile> {

    /** The numeric value of the tile. */
    protected int value;

    /**
     * Constructs a tile with a specified value.
     *
     * @param value the numeric value of the tile.
     */
    public Tile(int value) {
        setValue(value);
    }

    /**
     * Returns the numeric value of the tile.
     *
     * @return the tile's value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets a specific numeric value for the tile.
     *
     * @param value the numeric value to set for the tile.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Compares the current tile's value with another tile's value.
     *
     * @param other the other tile to compare with.
     * @return 1 if the current tile's value is greater, 0 if equal, and -1 if less.
     */
    @Override
    public int compareTo(Tile other) {
        if (this.value > other.value) {
            return 1;
        } else if (this.value == other.value) {
            return 0;
        }
        return -1;
    }
}
