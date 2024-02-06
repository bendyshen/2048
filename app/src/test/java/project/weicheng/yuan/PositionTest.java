package project.weicheng.yuan;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {

    private Position position;

    @BeforeEach
    public void setup() {
        position = new Position(2, 3); // Initialize the position at (2, 3).
    }

    @Test
    public void testGetRow() {
        assertEquals(2, position.getRow());
    }

    @Test
    public void testSetRow() {
        position.setRow(4);
        assertEquals(4, position.getRow());
    }

    @Test
    public void testGetCol() {
        assertEquals(3, position.getCol());
    }

    @Test
    public void testSetCol() {
        position.setCol(5);
        assertEquals(5, position.getCol());
    }

    @Test
    public void testIsMerged() {
        assertFalse(position.isMerged());
    }

    @Test
    public void testSetMerged() {
        position.setMerged(true);
        assertTrue(position.isMerged());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(position.isEmpty());
    }

    @Test
    public void testSetEmpty() {
        position.setEmpty(false);
        assertFalse(position.isEmpty());
    }

    @Test
    public void testHashCode() {
        Position anotherPosition = new Position(2, 3);
        assertEquals(position.hashCode(), anotherPosition.hashCode());
    }
}
