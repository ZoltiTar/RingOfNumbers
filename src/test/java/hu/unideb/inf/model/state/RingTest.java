package hu.unideb.inf.model.state;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class RingTest {

    @Test
    void noArgsConstTest() {
        Ring ring = new Ring();
        assertEquals(ring.getNumbers(), IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList()));
    }

    @Test
    void singleArgsConstTest() {
        assertThrows(IllegalArgumentException.class, () -> new Ring(null));
        assertThrows(IllegalArgumentException.class, () -> new Ring(List.of(1, 2)));
        assertThrows(IllegalArgumentException.class,
                () -> new Ring(IntStream.rangeClosed(0, 19).boxed().collect(Collectors.toList())));
    }

    @Test
    void turnRightTest() {
        List<Integer> firstTestNumbers = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        List<Integer> firstTestResult = List.of(20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
        Ring testRing = new Ring(firstTestNumbers);
        testRing.turnRight();
        assertEquals(testRing.getNumbers(), firstTestResult);
    }

    @Test
    void turnLeftTest() {
        List<Integer> secondTestNumbers = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        List<Integer> secondTestResult = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 1);
        Ring testRing = new Ring(secondTestNumbers);
        testRing.turnLeft();
        assertEquals(testRing.getNumbers(), secondTestResult);
    }

    @Test
    void reverseTest() {
        List<Integer> firstTestNumbers = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        List<Integer> firstTestResult = List.of(4, 3, 2, 1, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        Ring testRing = new Ring(firstTestNumbers);
        testRing.reverse();
        assertEquals(testRing.getNumbers(), firstTestResult);
    }

    @Test
    void isGoalTest() {
        assertFalse(new Ring().isGoal());
        List<Integer> listOfGoalState = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        Collections.reverse(listOfGoalState);
        Ring goalRing = new Ring(listOfGoalState);
        assertTrue(goalRing.isGoal());
        goalRing.turnRight();
        assertFalse(goalRing.isGoal());
        goalRing.turnLeft();
        assertTrue(goalRing.isGoal());
        goalRing.reverse();
        assertFalse(goalRing.isGoal());
        goalRing.reverse();
        assertTrue(goalRing.isGoal());
    }
}