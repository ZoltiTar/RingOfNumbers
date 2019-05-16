package hu.unideb.inf.model;

import lombok.*;

/**
 * Class representing information about the result of an attempt at the puzzle.
 *
 * The class has a builder, no argument constructor, and all arguments constructor provided by lombok.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    /**
     * The name of the player.
     */
    @Getter @Setter private String name;

    /**
     * The number of steps the player made.
     */
    @Getter @Setter private int steps;

    /**
     * Indicates whether the player has solved the puzzle.
     */
    @Getter @Setter private boolean solved;

    /**
     * String representation of the result.
     *
     * @return {@code String} representation of the result in the form of:
     * <em>name</em> - <em>steps</em> (<em>solved/attempt</em>)
     */
    @Override
    public String toString() {
        return String.format("%s - %d (%s)", name, steps, solved ? "solved" : "attempt");
    }
}
