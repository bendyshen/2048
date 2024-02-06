package project.weicheng.yuan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    private Tile tile;

    @BeforeEach
    public void setup() {
        tile = new Tile(4); // Initialize the tile with a value of 4.
    }

    @Test
    public void testGetValue() {
        assertEquals(4, tile.getValue());
    }

    @Test
    public void testSetValue() {
        tile.setValue(8);
        assertEquals(8, tile.getValue());
    }

    @Test
    public void testCompareToGreater() {
        Tile anotherTile = new Tile(2);
        assertEquals(1, tile.compareTo(anotherTile)); // Since 4 > 2
    }

    @Test
    public void testCompareToEqual() {
        Tile anotherTile = new Tile(4);
        assertEquals(0, tile.compareTo(anotherTile)); // Since 4 == 4
    }

    @Test
    public void testCompareToLess() {
        Tile anotherTile = new Tile(16);
        assertEquals(-1, tile.compareTo(anotherTile)); // Since 4 < 16
    }
}
