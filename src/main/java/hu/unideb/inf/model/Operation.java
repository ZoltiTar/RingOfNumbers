package hu.unideb.inf.model;

import java.util.List;

public enum Operation {
    LEFT,
    LEFT3X,
    RIGHT,
    RIGHT3X,
    REVERSE,
    RETURN,
    UNKNOWN;

    private static final List<String> LEFT_COMMANDS = List.of("LEFT, L");
    private static final List<String> LEFT3X_COMMANDS = List.of("LEFT3, L3");
    private static final List<String> RIGHT_COMMANDS = List.of("RIGHT, R");
    private static final List<String> RIGHT3X_COMMANDS = List.of("RIGHT3, R3");
    private static final List<String> REVERSE_COMMANDS = List.of("REVERSE, REV");

    public static Operation of(String action) {
        action = action.replaceAll("\\s", "").toUpperCase();
        if (LEFT_COMMANDS.contains(action)) return LEFT;
        if (LEFT3X_COMMANDS.contains(action)) return LEFT3X;
        if (RIGHT_COMMANDS.contains(action)) return RIGHT;
        if (RIGHT3X_COMMANDS.contains(action)) return RIGHT3X;
        if (REVERSE_COMMANDS.contains(action)) return REVERSE;
        if ("RETURN".equals(action)) return RETURN;

        return UNKNOWN;
    }

}
