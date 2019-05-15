package hu.unideb.inf.controller;

import hu.unideb.inf.model.Result;
import hu.unideb.inf.model.Ring;
import lombok.extern.slf4j.Slf4j;

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

    private Ring ring;
    private int steps;
    private String action;
    private String playerName;

    public Result launch(String name) {
        initGame(name);
        printControls();
        log.info("Starting game for {}", playerName);
        while (true) {
            printState();
            action = in.nextLine();
            if (action.compareToIgnoreCase("RETURN") == 0) break;
            else performAction(action);
        }

        return Result.builder()
                .name(playerName)
                .steps(steps)
                .solved(ring.isGoal())
                .build();
    }

    private void performAction(String action) {
        switch (action.toUpperCase()) {
            case "RIGHT":
                turnRight();
                break;
            case "LEFT":
                turnLeft();
                break;
            case "REVERSE":
                reverse();
                break;
            default:
                System.out.println("Unknown operation.");
        }
    }

    private void turnRight() {
        log.info("{} is turning the ring clockwise.", playerName);
        ring.turnRight();
        ++steps;
    }

    private void turnLeft() {
        log.info("{} is turning the ring anti-clockwise.", playerName);
        ring.turnLeft();
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
        System.out.println("To turn the ring clockwise, enter \"RIGHT\"");
        System.out.println("To turn the ring anti-clockwise, enter \"LEFT\"");
        System.out.println("To reverse the numbers in the middle, enter \"REVERSE\"");
        System.out.println("To return to the starting screen, enter \"RETURN\"");
        System.out.println("When you are ready, press ENTER to start the game.");
        in.nextLine();
    }

    private void initGame(String playerName) {
        log.info("Initializing new game for player '{}'", playerName);
        this.ring = new Ring();
        this.steps = 0;
        this.playerName = playerName;
    }
}
