package hu.unideb.inf.model;

import java.util.Collections;
import java.util.Comparator;
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
    private static List<Integer> GOAL = List.of(20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);

    /**
     * The list of numbers representing the start state.
     */
    private static List<Integer> START = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());

    /**
     * The list of numbers representing the current state.
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
        numbers.sort(null);
        return numbers.equals(START);
    }

    /**
     * Checks whether the puzzle is solved.
     *
     * @return {@code true} if the puzzle is solved, {@code false} otherwise
     */
    public boolean isGoal() {
        return GOAL.equals(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
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
        Integer last = numbers.remove(19);
        numbers.add(0, last);
    }

    /**
     * Reverses the first four number in the ring.
     */
    public void reverse() {
        Collections.swap(numbers, 0, 3);
        Collections.swap(numbers, 1, 2);
    }

    @Override
    public String toString() {
        List<Integer> firstFour = numbers.subList(0, 4);
        List<Integer> remaining = numbers.subList(4, numbers.size());
        return String.format("[ %s ] %s", firstFour.toString(), remaining.toString());
    }
}
