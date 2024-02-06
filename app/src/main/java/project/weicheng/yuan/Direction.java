package project.weicheng.yuan;

/**
 * Represents possible movement directions on the game board. This includes vertical, horizontal,
 * and diagonal directions.
 */
public enum Direction {

    /** Represents upward movement on the game board. */
    UP,

    /** Represents downward movement on the game board. */
    DOWN,

    /** Represents movement to the left on the game board. */
    LEFT,

    /** Represents movement to the right on the game board. */
    RIGHT,

    /** Represents diagonal movement to the bottom left on the game board. */
    LEFTDOWN,

    /** Represents diagonal movement to the top left on the game board. */
    LEFTUP,

    /** Represents diagonal movement to the bottom right on the game board. */
    RIGHTDOWN,

    /** Represents diagonal movement to the top right on the game board. */
    RIGHTUP;
}
