package hu.unideb.inf.controller;

import hu.unideb.inf.model.results.Result;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class LaunchController {

    private static LaunchController controller;
    private static GameController gameController;
    private static ScoreboardController scoreboardController;
    private static Scanner in;

    private LaunchController() {
    }

    public static LaunchController getController() {
        if (controller == null) {
            controller = new LaunchController();
            gameController = GameController.getController();
            scoreboardController = ScoreboardController.getController();
            in = new Scanner(System.in);
        }
        return controller;
    }

    private String player;
    private String action;

    public void start() {
        while (true) {
            printOptions();

            action = in.nextLine();

            if (action.compareToIgnoreCase("PLAY") == 0) this.newGame();
            else if (action.compareToIgnoreCase("SCORES") == 0) this.toScoreboard();
            else if (action.compareToIgnoreCase("EXIT") == 0) break;
            else System.out.println("\nUnknown operation. Please enter something from the below list.\n");
        }
    }

    private void toScoreboard() {
        log.info("Scoreboard requested.");
        scoreboardController.show();
    }

    private void printOptions() {
        System.out.println("To start a new game, enter \"PLAY\"");
        System.out.println("To proceed to the scoreboard, enter \"SCORES\"");
        System.out.println("To exit the game, enter \"EXIT\"");
    }

    private void newGame() {
        getPlayer();

        log.info("Requesting new game for {} ", player);
        Result result = gameController.launch(player);
        log.info("{} has finished their game.", player);

        scoreboardController.addResult(result);
    }

    private void getPlayer() {
        while(true) {
            System.out.print("Please enter your name: ");
            player = in.nextLine();
            if (!player.isEmpty()) {
                break;
            }
            System.out.println("Player name cannot be empty!");
        }
    }
}
