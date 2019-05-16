package hu.unideb.inf.model.state;

import hu.unideb.inf.model.state.Operation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OperationTest {
    private static List<String> leftTestCases;
    private static List<String> left3TestCases;
    private static List<String> rightTestCases;
    private static List<String> right3TestCases;
    private static List<String> reverseTestCases;
    private static List<String> resetTestCases;
    private static List<String> returnTestCases;
    private static List<String> unknownTestCases;

    @BeforeAll
    static void setup() {
        leftTestCases = List.of("left", "Le\nft", "l", "Le Ft", "L");
        left3TestCases = List.of("left3", "Left 3", "l 3", "LeFt\n3", "L 3");
        rightTestCases = List.of("right", "Rig ht", "r", "RiGh T", "R");
        right3TestCases = List.of("right 3", "Right\n3", "r    3", "RiGhT3", "R 3");
        reverseTestCases = List.of("reverse", "Reverse", "rev", "ReVeRSe", "ReV");
        resetTestCases = List.of("reset !", "RESET\n!", "reset!", "ReSET!");
        returnTestCases = List.of("return", "ReTuRN", "RETURN", "ReturN");
        unknownTestCases = List.of("pizza", "infinity stone", "infinite power");
    }

    @Test
    void testOfLeft() {
        Operation expected = Operation.LEFT;
        for (String input : leftTestCases) {
            assertEquals(expected, Operation.of(input));
        }
    }

    @Test
    void testOfLeft3() {
        Operation expected = Operation.LEFT3X;
        for (String input : left3TestCases) {
            assertEquals(expected, Operation.of(input));
        }
    }

    @Test
    void testOfRight() {
        Operation expected = Operation.RIGHT;
        for (String input : rightTestCases) {
            assertEquals(expected, Operation.of(input));
        }
    }

    @Test
    void testOfRight3() {
        Operation expected = Operation.RIGHT3X;
        for (String input : right3TestCases) {
            assertEquals(expected, Operation.of(input));
        }
    }

    @Test
    void testOfReverse() {
        Operation expected = Operation.REVERSE;
        for (String input : reverseTestCases) {
            assertEquals(expected, Operation.of(input));
        }
    }

    @Test
    void testOfReset() {
        Operation expected = Operation.RESET;
        for (String input : resetTestCases) {
            assertEquals(expected, Operation.of(input));
        }
    }

    @Test
    void testOfReturn() {
        Operation expected = Operation.RETURN;
        for (String input : returnTestCases) {
            assertEquals(expected, Operation.of(input));
        }
    }

    @Test
    void testOfUnknown() {
        Operation expected = Operation.UNKNOWN;
        for (String input : unknownTestCases) {
            assertEquals(expected, Operation.of(input));
        }
    }

}