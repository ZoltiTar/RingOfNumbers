package hu.unideb.inf;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class RingTest {

    @Test
    void turnRight() {
        List<Integer> firstTestNumbers = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        List<Integer> firstTestResult = List.of(20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
        Ring testRing = new Ring(firstTestNumbers);
        testRing.turnRight();
        assertEquals(testRing.getNumbers(), firstTestResult);
    }

    @Test
    void turnLeft() {
        List<Integer> secondTestNumbers = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        List<Integer> secondTestResult = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1);
        Ring testRing = new Ring(secondTestNumbers);
        testRing.turnLeft();
        assertEquals(testRing.getNumbers(), secondTestResult);
    }

    @Test
    void reverse() {
        List<Integer> firstTestNumbers = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        List<Integer> firstTestResult = List.of(4, 3, 2, 1, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        Ring testRing = new Ring(firstTestNumbers);
        testRing.reverse();
        assertEquals(testRing.getNumbers(), firstTestResult);
    }
}