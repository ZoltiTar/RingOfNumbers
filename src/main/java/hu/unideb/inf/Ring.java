package hu.unideb.inf;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ring {

    private static List<Integer> goal = List.of(20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);

    private List<Integer> numbers;

    public Ring() {
        this.numbers = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
    }

    public Ring(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void turnRight() {
        Integer cache;
        Integer prev = numbers.get(0);
        numbers.set(0, numbers.get(19));
        for (int i = 1; i < 20; i++) {
            cache = numbers.get(i);
            numbers.set(i, prev);
            prev = cache;
        }
    }

    public void turnLeft() {
        Integer cache;
        Integer prev = numbers.get(0);
        numbers.set(0, numbers.get(1));
        for (int i = 19; i > 0; i--) {
            cache = numbers.get(i);
            numbers.set(i, prev);
            prev = cache;
        }
    }

    public void reverse() {
        Collections.swap(numbers, 0, 3);
        Collections.swap(numbers, 1, 2);
    }

    public boolean complete() {
        return goal.equals(numbers);
    }
}
