package hu.unideb.inf.controller;

import com.github.lalyos.jfiglet.FigletFont;
import hu.unideb.inf.model.Operation;
import hu.unideb.inf.model.Result;
import hu.unideb.inf.model.Ring;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class GameController {

    private static GameController controller;
    private static Scanner in = new Scanner(System.in);

    private GameController() {
    }

    public static GameController getController() {
        if (controller == null) {
            controller = new GameController();
        }
        return controller;
    }

    /**
     * Ring of the current game, altering it by performing operations on user input.
     */
    private Ring ring;
    /**
     * Step count for the current game.
     */
    private int steps;
    /**
     * The operation to be done, generated form user input.
     */
    private Operation action;
    /**
     * Name of the player currently playing.
     */
    private String playerName;

    /**
     * The game loop. First prints all available controls, then waits for user input to start the game.
     *
     * @param name name of the player
     * @return the result entry of the game, containing the player's name, steps made,
     * and whether the puzzle has been solved
     * @see Result
     */
    public Result launch(String name) {
        initGame(name);
        printControls();
        suspend();
        log.info("Starting game for {}", playerName);
        while (true) {
            if (checkGoal()) {
                printCongrats();
                break;
            }
            printState();
            action = Operation.of(in.nextLine());
            if (action.equals(Operation.RETURN)) break;
            else performAction(action);
        }

        return Result.builder()
                .name(playerName)
                .steps(steps)
                .solved(ring.isGoal())
                .build();
    }

    private void printCongrats() {
        log.info("{} has solved the puzzle!", playerName);
        try {
            System.out.println(FigletFont.convertOneLine("Congratulations,"));
            System.out.println(FigletFont.convertOneLine("you solved the puzzle!"));
        } catch (IOException e) {
            System.out.println("Congratulations, you solved the puzzle!");
        }
    }

    private boolean checkGoal() {
        return ring.isGoal();
    }

    private void suspend() {
        System.out.println("When you are ready, press ENTER to start the game.");
        in.nextLine();
    }

    private void performAction(Operation op) {
        if (op.equals(Operation.LEFT)) turnLeft();
        if (op.equals(Operation.LEFT3X)) turnLeft3();
        if (op.equals(Operation.RIGHT)) turnRight();
        if (op.equals(Operation.RIGHT3X)) turnRight3();
        if (op.equals(Operation.REVERSE)) reverse();
        if (op.equals(Operation.UNKNOWN)) {
            log.info("Unknown operation, printing operation choices.");
            printControls();
        } else log.error("Passing all available operations.");
    }

    private void turnLeft() {
        log.info("{} is turning the ring anti-clockwise.", playerName);
        ring.turnLeft();
        ++steps;
    }

    private void turnLeft3() {
        log.info("{} is turning the ring clockwise, three times.", playerName);
        for (int i = 0; i < 3; i++) {
            ring.turnLeft();
        }
        ++steps;
    }

    private void turnRight() {
        log.info("{} is turning the ring clockwise.", playerName);
        ring.turnRight();
        ++steps;
    }

    private void turnRight3() {
        log.info("{} is turning the ring anti-clockwise, three times.", playerName);
        for (int i = 0; i < 3; i++) {
            ring.turnRight();
        }
        ++steps;
    }

    private void reverse() {
        log.info("{} is reversing the middle numbers.", playerName);
        ring.reverse();
        ++steps;
    }

    private void printState() {
        System.out.println(ring);
        System.out.print(String.format("Steps: %d; Your action: ", steps));
    }

    private void printControls() {
        log.info("Printing available operations.");
        System.out.println("To turn the ring anti-clockwise, enter LEFT or L");
        System.out.println("To turn the ring anti-clockwise three times, enter LEFT3 or L3");
        System.out.println("To turn the ring clockwise, enter RIGHT or R");
        System.out.println("To turn the ring clockwise three times, enter RIGHT3 or R3");
        System.out.println("To reverse the numbers in the middle, enter REVERSE or REV");
        System.out.println("To return to the starting screen, enter RETURN");
    }

    private void initGame(String playerName) {
        log.info("Initializing new game for player '{}'", playerName);
        this.ring = new Ring();
        this.steps = 0;
        this.playerName = playerName;
    }
}
