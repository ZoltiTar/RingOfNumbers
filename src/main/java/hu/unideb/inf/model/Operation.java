package hu.unideb.inf.model;

import hu.unideb.inf.controller.GameController;

import java.util.List;

/**
 * Enum class to help decide operation to be made.
 *
 * @see Ring
 * @see hu.unideb.inf.controller.GameController#performAction(Operation)
 */
public enum Operation {
    /** The operation should turn the ring left. */
    LEFT,
    /** The operation should turn the ring left three times. */
    LEFT3X,
    /** The operation should turn the ring right. */
    RIGHT,
    /** The operation should turn the ring right three times. */
    RIGHT3X,
    /** The operation should reverse the numbers in the brackets. */
    REVERSE,
    /** The operation should stop the game, and return to the main menu. */
    RETURN,
    /** The operation should be to print the controls again, and tell the user that the input was incorrect. */
    UNKNOWN;

    /**
     * List containing all commands which turn the ring left.
     */
    public static final List<String> LEFT_COMMANDS = List.of("LEFT", "L");

    /**
     * List containing all commands which turn the ring left three times.
     */
    public static final List<String> LEFT3X_COMMANDS = List.of("LEFT3", "L3");

    /**
     * List containing all commands which turn the ring right.
     */
    public static final List<String> RIGHT_COMMANDS = List.of("RIGHT",  "R");

    /**
     * List containing all commands which turn the ring right three times.
     */
    public static final List<String> RIGHT3X_COMMANDS = List.of("RIGHT3", "R3");

    /**
     * List containing all commands which reverse the reversible numbers in the middle.
     */
    public static final List<String> REVERSE_COMMANDS = List.of("REVERSE", "REV");

    /**
     * Returns the Operation type to be performed by the command given.
     *
     * @param command the string representing the action to be made
     * @return Operation to be performed, or {@code Operation.UNKNOWN}
     * if the given command is not a valid command.
     *
     * @see GameController#printControls()
     */
    public static Operation of(String command) {
        command = command.replaceAll("\\s", "").toUpperCase();
        if (LEFT_COMMANDS.contains(command)) return LEFT;
        if (LEFT3X_COMMANDS.contains(command)) return LEFT3X;
        if (RIGHT_COMMANDS.contains(command)) return RIGHT;
        if (RIGHT3X_COMMANDS.contains(command)) return RIGHT3X;
        if (REVERSE_COMMANDS.contains(command)) return REVERSE;
        if ("RETURN".equals(command)) return RETURN;

        return UNKNOWN;
    }

}
