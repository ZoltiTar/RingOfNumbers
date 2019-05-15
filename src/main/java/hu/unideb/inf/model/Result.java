package hu.unideb.inf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing information about the result of an attempt at the puzzle.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    /**
     * The name of the player.
     */
    private String name;

    /**
     * The number of steps the player made.
     */
    private int steps;

    /**
     * Indicates whether the player has solved the puzzle.
     */
    private boolean solved;

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
