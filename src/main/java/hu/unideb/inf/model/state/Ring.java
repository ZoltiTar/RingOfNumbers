package hu.unideb.inf.model.state;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestLine;
import de.vandermeer.asciithemes.TA_GridThemes;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class representing the state of the puzzle.
 */
public class Ring {

    /**
     * The list of numbers representing the goal state.
     */
    public static List<Integer> GOAL = List.of(20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);

    /**
     * The list of numbers representing the start state.
     */
    private static List<Integer> START = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());

    /**
     * The list of numbers representing the current state. The first four numbers are reversible.
     */
    private List<Integer> numbers;

    /**
     * Creates a {@code Ring} object representing the starting configuration
     * of the puzzle.
     */
    public Ring() {
        this(START);
    }

    /**
     * Creates a {@code Ring} object configured with specified list.
     *
     * @param numbers a {@code List} of {@code Integer}s of the size 20, with integers
     *                of the [1, 20] interval.
     * @throws IllegalArgumentException if the given list of numbers does not represent a valid configuration.
     */
    public Ring(List<Integer> numbers) {
        if (!isValidRing(numbers)) {
            throw new IllegalArgumentException("The given list does not represent a valid ring of numbers.");
        }
        this.numbers = numbers;
    }

    /**
     * Checks if given list represents a valid configuration of a ring.
     *
     * @param numbers the list to be validated
     * @return {@code true} if the list is a valid configuration, {@code false} if it does not represent a ring
     */
    private boolean isValidRing(List<Integer> numbers) {
        if (numbers == null || numbers.size() != 20) {
            return false;
        }
        List<Integer> sorted = new ArrayList<>(numbers);
        sorted.sort(null);
        return sorted.equals(START);
    }

    /**
     * Checks whether the puzzle is solved.
     *
     * @return {@code true} if the puzzle is solved, {@code false} otherwise
     */
    public boolean isGoal() {
        return GOAL.equals(numbers);
    }

    /**
     * Turns the ring clockwise.
     */
    public void turnRight() {
        Integer last = numbers.remove(19);
        numbers.add(0, last);
    }

    /**
     * Turns the ring anti-clockwise.
     */
    public void turnLeft() {
        Integer first = numbers.remove(0);
        numbers.add(19, first);
    }

    /**
     * Reverses the first four number in the ring.
     */
    public void reverse() {
        Collections.swap(numbers, 0, 3);
        Collections.swap(numbers, 1, 2);
    }

    /**
     * Returns the list of numbers of the ring.
     *
     * @return {@code List} of Integers resembling the ring, with the number at {@code index 0} being the
     * first reversible number.
     */
    public List<Integer> getNumbers() {
        return numbers;
    }

    /**
     * String representation of the ring. Using de.vandermeer.asciitable package to print as text.
     *
     * @return the ring in the shape of a ring, as a printable string
     */
    @Override
    public String toString() {
        AsciiTable table = new AsciiTable();
        table.getContext().setGridTheme(TA_GridThemes.NONE);
        table.addRule();
        table.addRow("", "", numbers.get(19),
                String.format("[%d", numbers.get(0)), numbers.get(1), numbers.get(2), String.format("%d]", numbers.get(3)),
                numbers.get(4), "", "");
        table.addRow("", numbers.get(18), "", "", "", "", "", "", numbers.get(5), "");
        table.addRow(numbers.get(17), "", "", "", "", "", "", "", "", numbers.get(6));
        table.addRow(numbers.get(16), "", "", "", "", "", "", "", "", numbers.get(7));
        table.addRow("", numbers.get(15), "", "", "", "", "", "", numbers.get(8), "");
        table.addRow("", "", numbers.get(14), numbers.get(13), numbers.get(12),
                numbers.get(11), numbers.get(10), numbers.get(9), "", "");
        table.addRule();

        table.setTextAlignment(TextAlignment.JUSTIFIED);
        table.getRenderer().setCWC(new CWC_LongestLine());
        return table.render();
    }

}
