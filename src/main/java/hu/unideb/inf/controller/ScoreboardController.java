package hu.unideb.inf.controller;

import hu.unideb.inf.model.Result;
import hu.unideb.inf.model.Scoreboard;
import hu.unideb.inf.util.JsonService;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class ScoreboardController {

    private static ScoreboardController controller;
    private static Scanner in;

    private ScoreboardController() {
    }

    public static ScoreboardController getController() {
        if (controller == null) {
            controller = new ScoreboardController();
            controller.json = new JsonService();
            controller.readScoreboard();
            in = new Scanner(System.in);
        }
        return controller;
    }

    private JsonService json;
    private Scoreboard scoreboard;

    public void show() {
        readScoreboard();
        System.out.println(scoreboard);
        suspend();
    }

    private void suspend() {
        System.out.print("When you are done, press ENTER to return to main menu.");
        in.nextLine();
    }

    public void addResult(Result result) {
        log.info("Adding new result to scoreboard.");
        scoreboard.add(result);
        updateScoreboard();
    }

    private void readScoreboard() {
        scoreboard = json.readScoreboard();
    }

    private void updateScoreboard() {
        json.updateScoreboard(scoreboard);
    }
}
