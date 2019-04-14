package hu.unideb.inf;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ring {

    private List<Integer> numbers;

    public Ring() {
        this.numbers = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
    }

    public Ring(List<Integer> numbers) {
        this.numbers = numbers;
    }

    List<Integer> getNumbers() {
        return numbers;
    }

    void turnRight() {
        Integer cache;
        Integer prev = numbers.get(0);
        numbers.set(0, numbers.get(19));
        for (int i = 1; i < 20; i++) {
            cache = numbers.get(i);
            numbers.set(i, prev);
            prev = cache;
        }
    }

    void turnLeft() {
        Integer cache;
        Integer prev = numbers.get(0);
        numbers.set(0, numbers.get(1));
        for (int i = 19; i > 0; i--) {
            cache = numbers.get(i);
            numbers.set(i, prev);
            prev = cache;
        }
    }

    void reverse() {
        Collections.swap(numbers, 0, 3);
        Collections.swap(numbers, 1, 2);
    }

}
